package by.hellbee.model.core.creature;

import by.hellbee.map.Cell;
import by.hellbee.map.Map;
import by.hellbee.model.core.Creature;
import by.hellbee.model.core.Entity;
import by.hellbee.model.core.entity.Grass;
import by.hellbee.model.factory.Sprite;

import java.util.concurrent.ThreadLocalRandom;

public class Herbivore extends Creature {

    public Herbivore(int speed, int health) {
        super(speed, health);
    }

    public void eatResource(Map map, Cell currentCell, Cell targetCell) {
        var neighborEntity = map.getEntityOnCell(targetCell);
        if (neighborEntity instanceof Grass) {
            int heal = ThreadLocalRandom.current().nextInt(1, 3);

            map.removeEntity(targetCell);
            map.moveEntity(currentCell, targetCell);

            super.setHealth(Math.min(super.getHealth() + heal, super.getMaxHealth()));
            super.addReproductionPoints();
        }
    }

    @Override
    public Class<? extends Entity> getTargetType() {
        return Grass.class;
    }

    @Override
    public String getSprite() {
        return Sprite.HERBIVORE;
    }

}
