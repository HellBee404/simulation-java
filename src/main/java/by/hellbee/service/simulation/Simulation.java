package by.hellbee.service.simulation;

import by.hellbee.config.ConfigLoader;
import by.hellbee.map.Map;
import by.hellbee.map.render.Render;
import by.hellbee.map.render.RendererService;
import by.hellbee.map.util.Action;
import by.hellbee.service.SimulationService;

import java.util.Scanner;

public class Simulation implements SimulationService {

    private final Scanner scanner = new Scanner(System.in);

    private final int moveDelayMs = ConfigLoader.getInt("simulation.moveDelayMs", 1000);

    private final Action action = new Action();
    private final Map map = action.getMap();
    private final RendererService renderer = new Render();

    private boolean isRunning = false;


    public void controlSimulation() {
        action.initActions();
        renderer.render(map);

        boolean userWantsToExit = false;

        while (!userWantsToExit) {
            System.out.println("\n=—= Управление симуляцией =—=");
            System.out.println("1 - Запустить бесконечную симуляцию");
            System.out.println("2 - Сделать ровно 1 ход");
            System.out.println("0 - Выйти из программы");
            System.out.print("Выберите действие: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> this.startSimulation();
                case "2" -> this.nextTurn();
                case "0" -> {
                    this.stopSimulation();
                    userWantsToExit = true;
                    System.out.println("Симуляция завершена! Количество ходов, сделанных симуляцией: " + action.getTurnCounter());
                }
                default -> System.out.println("Неверный ввод. Попробуйте еще раз.");
            }
        }
    }

    @Override
    public void startSimulation() {
        isRunning = true;

        Thread stopListener = new Thread(() -> {
            scanner.nextLine();
            stopSimulation();
            System.out.println("Остановка симуляции...");
        });
        stopListener.setDaemon(true);
        stopListener.start();

        while (isRunning) {
            this.nextTurn();
            try {
                Thread.sleep(moveDelayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                this.stopSimulation();
            }
        }

    }

    @Override
    public void stopSimulation() {
        this.isRunning = false;
    }

    @Override
    public void nextTurn() {
        this.action.turnActions();
        this.renderer.render(map);

        if (isRunning) {
            System.out.println("Количество шагов, сделанных симуляцией: " + action.getTurnCounter());
            System.out.println("[АВТО-РЕЖИМ] Нажмите ENTER, чтобы остановить...\n");
        } else {
            System.out.println("Ход сделан! Текущее количество ходов, сделанных симуляцией: " + action.getTurnCounter());
        }
    }
}