package by.hellbee.map.util;

import by.hellbee.map.Cell;
import by.hellbee.map.Map;
import by.hellbee.model.core.Entity;
import by.hellbee.model.factory.EntityFactory;

import java.util.Random;

// todo подумать о том, чтобы сделать это в многопоточке
public class Action {

    private final Random random = new Random();

    private final int rows = 30;
    private final int columns = 15;

    private final Map map = new Map(rows, columns);
    private final EntityFactory entityFactory = new EntityFactory();

    public void initActions() {

        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                Cell cell = new Cell(x, y);
                Entity entity = placeRandomEntity();
                if (entity != null) {
                    map.setEntity(cell, entity);
                }
            }
        }
    }

    public Entity placeRandomEntity() {
        int chance = random.nextInt(100);
        SpawnConfig config = SpawnConfig.DEFAULT;

        int currentThreshold = config.predatorPercent();
        if (chance < currentThreshold) {
            return entityFactory.createPredator();
        }

        currentThreshold += config.herbivorePercent();
        if (chance < currentThreshold) {
            return entityFactory.createHerbivore();
        }

        currentThreshold += config.grassPercent();
        if (chance < currentThreshold) {
            return entityFactory.createGrass();
        }

        currentThreshold += config.rockPercent();
        if (chance < currentThreshold) {
            return entityFactory.createRock();
        }

        currentThreshold += config.treePercent();
        if (chance < currentThreshold) {
            return entityFactory.createTree();
        } else {
            return null;
        }
    }

    public Map getMap() {
        return map;
    }
}
