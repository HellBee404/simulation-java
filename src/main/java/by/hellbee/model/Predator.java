package by.hellbee.model;

import by.hellbee.model.core.Creature;

// todo Доделать класс
public class Predator extends Creature {
    private final int DAMAGE = 1;
    private int moveCount;

    @Override
    protected void makeMove(int speed) {
        if (moveCount <= 0) {
            throw new IllegalStateException("Существо не имеет ходов.");
        }

        moveCount--;
        // доделать
    }

    public void attackHerbivore(Herbivore herbivore) {
        if (moveCount <= 0) {
            throw new IllegalStateException("Существо не имеет ходов.");
        }

        herbivore.setHealth(herbivore.getHealth() - DAMAGE);
        moveCount--;
        // доделать
    }

    @Override
    public String getSprite() {
        return Sprite.PREDATOR;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public int getDAMAGE() {
        return this.DAMAGE;
    }
}
