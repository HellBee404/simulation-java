package by.hellbee.model.core;

import by.hellbee.map.Cell;
import by.hellbee.map.Map;
import by.hellbee.model.core.creature.Herbivore;
import by.hellbee.model.core.creature.Predator;
import by.hellbee.model.core.entity.Grass;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Creature extends Entity {

    private final int speed;
    private int health;
    private final int maxHealth;
    private int moveCount = 0;

    public Creature(int speed, int health) {
        this.speed = speed;
        this.health = health;
        this.maxHealth = health;
        this.resetMoveCount();
    }

    protected void makeMove(Map map, Cell currentCell) {
        if (!this.hasMoves()) {
            throw new IllegalStateException("Существо " + map.getEntityOnCell(currentCell) + " не имеет ходов");
        }

        while (this.hasMoves()) {

            var walkableNeighbor = map.getTargetCell(currentCell);

            Entity currentEntity = map.getEntityOnCell(currentCell);

            if (currentEntity instanceof Herbivore herbivore
                    && map.getEntityOnCell(walkableNeighbor) instanceof Grass) {
                herbivore.eatResource(map, currentCell, walkableNeighbor);
                currentCell = walkableNeighbor;

                this.decreaseMoves();
            } else if (currentEntity instanceof Predator predator
                    && map.getEntityOnCell(walkableNeighbor) instanceof Herbivore) {
                predator.attackHerbivore(map, currentCell, walkableNeighbor);

                if (map.getEntityOnCell(walkableNeighbor) == predator) {
                    currentCell = walkableNeighbor;

                    this.decreaseMoves();
                    continue;
                }

                this.decreaseMoves();
            } else if (map.isCellEmpty(walkableNeighbor)) {
                map.moveEntity(currentCell, walkableNeighbor);
                currentCell = walkableNeighbor;

                this.decreaseMoves();
            } else {
                break;
            }

        }
    }

    // for debug
    @Override
    public String toString() {
        return "Creature{" +
                this.getSprite() +
                ", speed=" + speed +
                ", health=" + health +
                ", moveCount=" + moveCount +
                '}';
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
}
