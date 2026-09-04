package by.hellbee.map.util;

import by.hellbee.config.ConfigLoader;
import by.hellbee.map.Cell;
import by.hellbee.map.Map;
import by.hellbee.model.core.Creature;
import by.hellbee.model.core.Entity;
import by.hellbee.model.core.creature.Herbivore;
import by.hellbee.model.core.creature.Predator;
import by.hellbee.model.core.entity.Grass;
import by.hellbee.model.factory.EntityFactory;
import by.hellbee.service.PathfinderService;
import by.hellbee.service.bfs.BFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Action {

    private final Random random = new Random();

    private final int rows = ConfigLoader.getInt("map.rows", 30);
    private final int columns = ConfigLoader.getInt("map.columns", 15);

    private long turnCounter = 0;
    private static final int REPRODUCTION_THRESHOLD = ConfigLoader.getInt("action.reproduction.threshold", 4);

    private final Map map = new Map(rows, columns);
    private final EntityFactory entityFactory = new EntityFactory();
    private final PathfinderService pathfinderService;
    private final SpawnConfig config;

    public Action() {
        this.pathfinderService = resolvePathfinder();
        this.config = resolveSpawnConfig();
    }

    public Action(PathfinderService pathfinderService, SpawnConfig config) {
        this.pathfinderService = pathfinderService;
        this.config = config;
    }

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

    public void turnActions() {
        turnCounter++;
        List<Cell> creatureCells = new ArrayList<>();

        for (java.util.Map.Entry<Cell, Entity> entry : map.getEntitiesMap().entrySet()) {
            if (entry.getValue() instanceof Creature) {
                creatureCells.add(entry.getKey());
            }
        }

        for (Cell creatureCell : creatureCells) {
            var entity = map.getEntityOnCell(creatureCell);
            if (entity instanceof Creature creature) {

                if (creature.isDead()) {
                    map.setEntity(creatureCell, null);
                    continue;
                }

                if (creature.getReproductionPoints() >= REPRODUCTION_THRESHOLD) {
                    reproductionCreature(creatureCell);
                    creature.resetReproductionPoints();
                    creature.spendMoveForReproduction();
                }

                creature.resetMoveCount();
                Cell finalCell = creature.makeMove(map, creatureCell, pathfinderService);
                creature.decreaseHunger();
                if (creature.isDead()) {
                    map.setEntity(finalCell, null);
                }
            }
        }

        if (turnCounter % 3 == 0) {
            spawnMoreGrassIfNeeded();
        }
    }

    private void reproductionCreature(Cell parentCellEntity) {
        var neighbours = map.getWalkableNeighbors(parentCellEntity);
        var reproductiveNeighbours = neighbours.stream()
                .filter(map::isCellEmpty)
                .toList();

        if (!reproductiveNeighbours.isEmpty()) {
            int randomCell = random.nextInt(reproductiveNeighbours.size());
            Cell toReproductionCell = reproductiveNeighbours.get(randomCell);
            if (map.isCellEmpty(toReproductionCell)) {
                Entity parentEntity = map.getEntityOnCell(parentCellEntity);
                if (parentEntity instanceof Herbivore) {
                    map.setEntity(toReproductionCell, entityFactory.createHerbivore());
                }
                if (parentEntity instanceof Predator) {
                    map.setEntity(toReproductionCell, entityFactory.createPredator());
                }
            }
        }
    }

    private void spawnMoreGrassIfNeeded() {
        int spawnThresholdFactor = 2;
        long totalCells = columns * rows;
        long emptyCells = totalCells - map.getEntitiesMap().size();

        long targetGrassCount = (totalCells * config.herbivoreResourcePercent()) / 100;
        long triggerThreshold = targetGrassCount / spawnThresholdFactor;

        long currentGrassCount = map.getEntitiesMap().values().stream()
                .filter(entity -> entity instanceof Grass)
                .count();

        if (currentGrassCount < triggerThreshold) {
            long grassToSpawn = targetGrassCount - currentGrassCount;

            long limit = Math.min(grassToSpawn, emptyCells);
            while (limit > 0) {
                int randomX = random.nextInt(columns);
                int randomY = random.nextInt(rows);
                Cell cell = new Cell(randomX, randomY);

                if (map.isCellEmpty(cell)) {
                    map.setEntity(cell, entityFactory.createGrass());
                    limit--;
                }
            }
        }
    }

    private Entity placeRandomEntity() {
        int chance = random.nextInt(100);

        int currentThreshold = config.predatorPercent();
        if (chance < currentThreshold) {
            return entityFactory.createPredator();
        }

        currentThreshold += config.herbivorePercent();
        if (chance < currentThreshold) {
            return entityFactory.createHerbivore();
        }

        currentThreshold += config.herbivoreResourcePercent();
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

    private PathfinderService resolvePathfinder() {
        String algo = ConfigLoader.getString("pathfinder.algorithm", "bfs");
        return switch (algo.toLowerCase()) {
            case "bfs" -> new BFS();
            default -> new BFS();
        };
    }

    private SpawnConfig resolveSpawnConfig() {
        String preset = ConfigLoader.getString("spawn.preset", "default");
        return switch (preset.toLowerCase()) {
            case "default" -> SpawnConfig.DEFAULT;
            default -> SpawnConfig.DEFAULT;
        };
    }

    public long getTurnCounter() {
        return turnCounter;
    }

    public Map getMap() {
        return map;
    }
}
