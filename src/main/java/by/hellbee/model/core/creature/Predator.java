package by.hellbee.model.core.creature;

import by.hellbee.model.core.Creature;
import by.hellbee.model.factory.Sprite;

// todo Доделать класс
public class Predator extends Creature {
    private final int damage;

    public Predator(int speed, int health, int damage) {
        super(speed, health);
        this.damage = damage;
    }


    public void attackHerbivore(Herbivore herbivore) {

        herbivore.setHealth(herbivore.getHealth() - this.damage);
        // доделать
    }

    @Override
    public String getSprite() {
        return Sprite.PREDATOR;
    }
}
