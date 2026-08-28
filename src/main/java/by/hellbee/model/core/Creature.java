package by.hellbee.model.core;

// todo Доделать класс
public abstract class Creature extends Entity {

    private int speed;
    private int health;

    protected abstract void makeMove(int speed);

    protected int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
