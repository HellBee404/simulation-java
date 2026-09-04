package by.hellbee.model.factory;

import by.hellbee.config.ConfigLoader;
import by.hellbee.model.core.creature.Herbivore;
import by.hellbee.model.core.creature.Predator;
import by.hellbee.model.core.entity.Grass;
import by.hellbee.model.core.entity.Rock;
import by.hellbee.model.core.entity.Tree;

import java.util.Random;

public class EntityFactory {
    private final Random random = new Random();

    public Predator createPredator() {
        int speed = random.nextInt(
                ConfigLoader.getInt("entity.predator.speed.min", 3),
                ConfigLoader.getInt("entity.predator.speed.max", 5));
        int health = random.nextInt(
                ConfigLoader.getInt("entity.predator.health.min", 2),
                ConfigLoader.getInt("entity.predator.health.max", 5));
        int damage = random.nextInt(
                ConfigLoader.getInt("entity.predator.damage.min", 1),
                ConfigLoader.getInt("entity.predator.damage.max", 4));

        return new Predator(speed, health, damage);
    }

    public Herbivore createHerbivore() {
        int speed = random.nextInt(
                ConfigLoader.getInt("entity.herbivore.speed.min", 1),
                ConfigLoader.getInt("entity.herbivore.speed.max", 3));
        int health = random.nextInt(
                ConfigLoader.getInt("entity.herbivore.health.min", 5),
                ConfigLoader.getInt("entity.herbivore.health.max", 9));

        return new Herbivore(speed, health);
    }

    public Grass createGrass() {
        return new Grass();
    }

    public Rock createRock() {
        return new Rock();
    }

    public Tree createTree() {
        return new Tree();
    }
}
