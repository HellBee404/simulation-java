package by.hellbee.model;

public class EntityFactory {

    public Predator createPredator() {
        return new Predator();
    }

    public Herbivore createHerbivore() {
        return new Herbivore();
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
