package by.hellbee.model.core;

import by.hellbee.map.Cell;
import by.hellbee.map.Map;
import by.hellbee.model.core.creature.Herbivore;
import by.hellbee.model.core.creature.Predator;
import by.hellbee.model.core.entity.Grass;
import by.hellbee.service.PathfinderService;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Creature extends Entity {

    private final int speed;
    private int health;
    private final int maxHealth;
    private int moveCount = 0;

    // TODO вынести все конфиги в отдельный файл
    private int reproductionPoints = 0;
    private static final int REPRODUCTION_POINTS_LIMIT = 3;
    private int hunger = 5;
    private static final int MAX_SATURATION = 10;

    public Creature(int speed, int health) {
        this.speed = speed;
        this.health = health;
        this.maxHealth = health;
        this.resetMoveCount();
    }

    public Cell makeMove(Map map, Cell currentCell, PathfinderService pathfinderService) {
        if (!this.hasMoves()) {
            throw new IllegalStateException("Существо " + map.getEntityOnCell(currentCell) + " не имеет ходов");
        }

        while (this.hasMoves()) {

            Cell nextStep = pathfinderService.findNextStep(map, currentCell, this.getTargetType());

            if (nextStep == null) {
                List<Cell> emptyNeighbors = map.getNeighbours(currentCell).stream()
                        .filter(map::isCellEmpty)
                        .toList();

                if (!emptyNeighbors.isEmpty()) {
                    Cell randomNeighbor = emptyNeighbors.get(ThreadLocalRandom.current().nextInt(emptyNeighbors.size()));
                    map.moveEntity(currentCell, randomNeighbor);
                    currentCell = randomNeighbor;
                }
                this.decreaseMoves();
                continue;
            }

            Entity currentEntity = map.getEntityOnCell(currentCell);

            if (currentEntity instanceof Herbivore herbivore
                    && map.getEntityOnCell(nextStep) instanceof Grass) {
                herbivore.eatResource(map, currentCell, nextStep);
                currentCell = nextStep;

                this.decreaseMoves();
            } else if (currentEntity instanceof Predator predator
                    && map.getEntityOnCell(nextStep) instanceof Herbivore) {
                predator.attackHerbivore(map, currentCell, nextStep);

                if (map.getEntityOnCell(nextStep) == predator) {
                    currentCell = nextStep;

                    this.decreaseMoves();
                    continue;
                }

                this.decreaseMoves();
            } else if (map.isCellEmpty(nextStep)) {
                map.moveEntity(currentCell, nextStep);
                currentCell = nextStep;

                this.decreaseMoves();
            } else {
                break;
            }

        }
        return currentCell;
    }

    public void spendMoveForReproduction() {
        if (this.hasMoves()) {
            this.decreaseMoves();
        }
    }

    public abstract Class<? extends Entity> getTargetType();

    public boolean isDead() {
        return this.health <= 0;
    }

    public void increaseHunger(int amount) {
        this.hunger = Math.min(this.hunger + amount, MAX_SATURATION);
    }

    public boolean isHungry() {
        return this.hunger < MAX_SATURATION;
    }

    public void decreaseHunger() {
        if (this.hunger > 0) {
            this.hunger--;
        } else {
            decreaseHealth();
        }
    }

    private void decreaseHealth() {
        if (this.health <= 0) {
            return;
        }
        if (this.hunger <= 0) {
            this.health--;
        }
    }

    public void addReproductionPoints() {
        int randomPoint = ThreadLocalRandom.current().nextInt(1, REPRODUCTION_POINTS_LIMIT + 1);
        this.reproductionPoints += randomPoint;
    }

    public void resetMoveCount() {
        this.moveCount = ThreadLocalRandom.current().nextInt(1, this.speed + 1);
    }

    private boolean hasMoves() {
        return this.moveCount > 0 && this.speed > 0;
    }

    private void decreaseMoves() {
        this.moveCount--;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Creature creature = (Creature) o;
        return getSpeed() == creature.getSpeed() && getHealth() == creature.getHealth() && getMaxHealth() == creature.getMaxHealth() && getMoveCount() == creature.getMoveCount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpeed(), getHealth(), getMaxHealth(), getMoveCount());
    }

    public long getReproductionPoints() {
        return reproductionPoints;
    }

    public void resetReproductionPoints() {
        this.reproductionPoints = 0;
    }
}
