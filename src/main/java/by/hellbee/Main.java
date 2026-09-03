package by.hellbee;

import by.hellbee.map.Map;
import by.hellbee.map.render.Render;
import by.hellbee.map.render.RendererService;
import by.hellbee.map.util.Action;
import by.hellbee.map.util.SpawnConfig;
import by.hellbee.service.bfs.BFS;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Action action = new Action(new BFS(), SpawnConfig.DEFAULT);
        action.initActions();
        Map map = action.getMap();
        RendererService renderer = new Render();

        System.out.println();

        try (Scanner input = new Scanner(System.in)) {
            boolean quit = false;
            while (!quit) {
                renderer.render(map);
                System.out.print("Вы хотите сделать просимулировать 1 ход? (Y - ДА, N - НЕТ, Q - ЗАКОНЧИТЬ СИМУЛЯЦИЮ): ");
                String answer = input.nextLine();
                switch (answer) {
                    case "Y" -> action.turnActions();
                    case "N" -> {
                    }
                    case "Q" -> quit = true;
                    default -> System.out.println("Введите корректный вариант");
                }
            }
            System.out.println("Симуляция завершена! Количество действий, совершенных симуляцией: " + action.getTurnCounter());
        }
    }
}