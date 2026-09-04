package by.hellbee.map;

import by.hellbee.model.core.Entity;
import by.hellbee.model.core.entity.Rock;
import by.hellbee.model.core.entity.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {

    private final int columns;
    private final int rows;

    private final HashMap<Cell, Entity> entities = new HashMap<>();

    public Map(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public void moveEntity(Cell from, Cell to) {
        if (!isWalkable(from, to)) {
            throw new IllegalArgumentException("Невозможно передвинуть сущность с клетки " + from + " на клетку " + to);
        }

        Entity entity = getEntityOnCell(from);
        if (entity == null) {
            throw new IllegalArgumentException("На исходной клетки сущности нет.");
        }

        removeEntity(from);
        setEntity(to, entity);

    }

    public List<Cell> getWalkableNeighbors(Cell current) {
        var neighbours = getNeighbours(current);
        var walkableNeighbors = new ArrayList<Cell>();
        for (Cell neighbour : neighbours) {
            if (isWalkable(current, neighbour)) {
                walkableNeighbors.add(neighbour);
            }
        }
        return walkableNeighbors;
    }

    public List<Cell> getNeighbours(Cell current) {
        List<Cell> neighbours = new ArrayList<>();

        Cell leftNeighbour = new Cell(current.x() - 1, current.y());
        if (isWithin(leftNeighbour)) {
            neighbours.add(leftNeighbour);
        }
        Cell rightNeighbour = new Cell(current.x() + 1, current.y());
        if (isWithin(rightNeighbour)) {
            neighbours.add(rightNeighbour);
        }
        Cell topNeighbour = new Cell(current.x(), current.y() - 1);
        if (isWithin(topNeighbour)) {
            neighbours.add(topNeighbour);
        }
        Cell bottomNeighbour = new Cell(current.x(), current.y() + 1);
        if (isWithin(bottomNeighbour)) {
            neighbours.add(bottomNeighbour);
        }
        return neighbours;
    }

    public HashMap<Cell, Entity> getEntitiesMap() {
        return new HashMap<>(entities);
    }

    public void setEntity(Cell cell, Entity entity) {
        entities.put(cell, entity);
    }

    public void removeEntity(Cell cell) {
        entities.remove(cell);
    }

    public boolean isCellEmpty(Cell cell) {
        return !entities.containsKey(cell);
    }

    public Entity getEntityOnCell(Cell cell) {
        return entities.get(cell);
    }

    public boolean isWalkable(Cell from, Cell to) {
        return (isValid(to) && !to.equals(from))
                && !((getEntityOnCell(to) instanceof Rock))
                && !(getEntityOnCell(to) instanceof Tree);
    }

    private boolean isValid(Cell cell) {
        return cell != null && isWithin(cell);
    }

    private boolean isWithin(Cell destination) {
        return (destination.x() >= 0 && destination.x() < columns)
                && (destination.y() >= 0 && destination.y() < rows);
    }
}
