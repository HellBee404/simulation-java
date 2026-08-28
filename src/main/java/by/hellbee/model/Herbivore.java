package by.hellbee.model;

import by.hellbee.map.Cell;
import by.hellbee.model.core.Creature;

// todo Доделать класс
public class Herbivore extends Creature {
    private int moveCount;

    @Override
    protected void makeMove(int speed) {
        if (moveCount <= 0) {
            throw new IllegalStateException("Существо не имеет ходов.");
        }

        moveCount--;
        // доделать
    }

    public void eatResource(Cell cell) {
        if (moveCount <= 0) {
            throw new IllegalStateException("Существо не имеет ходов.");
        }

        moveCount--;
        // доделать
    }

    @Override
    public String getSprite() {
        return Sprite.HERBIVORE;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }
}
