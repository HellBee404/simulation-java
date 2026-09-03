package by.hellbee.model.core.creature;

import by.hellbee.map.Cell;
import by.hellbee.map.Map;
import by.hellbee.model.core.Creature;
import by.hellbee.model.core.Entity;
import by.hellbee.model.factory.Sprite;

import java.util.concurrent.ThreadLocalRandom;

public class Predator extends Creature {
    private final int damage;

    public Predator(int speed, int health, int damage) {
        super(speed, health);
        this.damage = damage;
    }

    public void attackHerbivore(Map map, Cell currentCell, Cell targetCell) {
        var neighborEntity = map.getEntityOnCell(targetCell);

        if (neighborEntity instanceof Herbivore herbivore) {
            herbivore.setHealth(herbivore.getHealth() - this.damage);

            if (herbivore.getHealth() <= 0) {

                int heal = ThreadLocalRandom.current().nextInt(1, 3);

                map.removeEntity(targetCell);
                map.moveEntity(currentCell, targetCell);

                super.setHealth(Math.min(super.getHealth() + heal, super.getMaxHealth()));
                super.addReproductionPoints();
            }
        }
    }

    @Override
    public Class<? extends Entity> getTargetType() {
        return Herbivore.class;
    }

    @Override
    public String getSprite() {
        return Sprite.PREDATOR;
    }
}
