package by.hellbee;

import by.hellbee.service.SimulationService;
import by.hellbee.service.simulation.Simulation;

public class Main {

    public static void main(String[] args) {
        SimulationService simulationService = new Simulation();
        simulationService.controlSimulation();
    }
}