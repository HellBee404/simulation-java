package by.hellbee.service;

public interface SimulationService {

    // меня контроля симуляции
    void controlSimulation();

    // запуск симуляции
    void startSimulation();

    // остановки симуляции, завершение программы
    void stopSimulation();

    // просимулировать и отрендерить один ход
    void nextTurn();
}
