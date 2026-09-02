package by.hellbee.model.core;

import by.hellbee.map.Cell;
import by.hellbee.map.Map;

import java.util.concurrent.ThreadLocalRandom;

// todo Доделать класс
public abstract class Creature extends Entity {

    private final int speed;
    private int health;
    private int moveCount = 0;

    public Creature(int speed, int health) {
        this.speed = speed;
        this.health = health;
        this.resetMoveCount();
    }

    protected void makeMove(Map map, Cell currentCell) {
        if (!this.hasMoves()) {
            throw new IllegalStateException("Существо " + map.getEntityOnCell(currentCell) + " не имеет ходов");
        }

        while (this.hasMoves()) {

            var walkableNeighbor = map.getTargetCell(currentCell);

            map.moveEntity(currentCell, walkableNeighbor);
            currentCell = walkableNeighbor;
            this.decreaseMoves();
        }
    }

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

    public int getHealth() {
        return health;
    }
}
