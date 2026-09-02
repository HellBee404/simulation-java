package by.hellbee.map;

import by.hellbee.model.core.Entity;
import by.hellbee.model.core.entity.Rock;
import by.hellbee.model.core.entity.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// TODO Доделать класс
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

        if (getEntityOnCell(to).equals(entity)) {
            System.out.println("Сущность " + entity + " успешно передвинулась из клетки " + from + " на клетку " + to);
        }
    }

    // TODO написать метод getWalkableNeighborCells(Cell from) для нахождения валидных соседей для алгоритма поиска пути
    public Cell getTargetCell(Cell currentCell) {
        return null;
    }

    public List<Cell> getWalkableNeighborCells(Cell from) {
        return null;
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities.values());
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
