package by.hellbee.map;

import by.hellbee.model.core.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// todo Доделать класс
// todo написать метод для проверки клетки за границей карты isWithin()
// todo написать метод для валидации isValid()
// todo написать метод isCellWalkable() для алгоритма поиска пути
public class Map {

    private final int rows;
    private final int columns;

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

///////////////////////////////////////////////// ПЕРЕДЕЛАТЬ ВАЛИДАЦИЮ /////////////////////////////////////////////////
        if (from == null) {
            throw new IllegalArgumentException("Нельзя передвинуть сущность из несуществующей клетки.");
        }
        if (to == null) {
            throw new IllegalArgumentException("Нельзя передвинуть сущность в несуществующую клетку.");
        }
        if (from.equals(to)) {
            throw new IllegalArgumentException("Клетка " + to + " уже занята.");
        }
///////////////////////////////////////////////// ПЕРЕДЕЛАТЬ ВАЛИДАЦИЮ /////////////////////////////////////////////////

        Entity entity = getEntityOnCell(from);
        if (entity == null) {
            throw new IllegalArgumentException("На исходной клетки сущности нет.");
        }

        removeEntity(from);
        setEntity(to, entity);

        if (getEntityOnCell(to).equals(entity)) {
            System.out.println("Сущность успешно передвинулась из " + from + " на " + to);
        }
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
}
