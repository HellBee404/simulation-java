package by.hellbee.model.core.creature;

import by.hellbee.map.Cell;
import by.hellbee.model.core.Creature;
import by.hellbee.model.factory.Sprite;

// todo Доделать класс
public class Herbivore extends Creature {

    public Herbivore(int speed, int health) {
        super(speed, health);
    }

    public void eatResource(Cell cell) {
        // доделать
    }

    @Override
    public String getSprite() {
        return Sprite.HERBIVORE;
    }

}
