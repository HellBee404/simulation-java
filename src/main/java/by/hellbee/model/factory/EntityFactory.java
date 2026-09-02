package by.hellbee.model.factory;

import by.hellbee.model.core.creature.Herbivore;
import by.hellbee.model.core.creature.Predator;
import by.hellbee.model.core.entity.Grass;
import by.hellbee.model.core.entity.Rock;
import by.hellbee.model.core.entity.Tree;

import java.util.Random;

public class EntityFactory {
    private final Random random = new Random();

    // TODO поиграться с балансом
    public Predator createPredator() {
        int speed = random.nextInt(3, 5); // speed = 3-4
        int health = random.nextInt(2, 5); // health = 2-4
        int damage = random.nextInt(1, 4); // damage = 1-3

        return new Predator(speed, health, damage);
    }

    // TODO поиграться с балансом
    public Herbivore createHerbivore() {
        int speed = random.nextInt(1, 3); // speed = 1-2
        int health = random.nextInt(5, 9); // health = 5-8

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
