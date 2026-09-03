package by.hellbee.service.bfs;

import by.hellbee.map.Cell;
import by.hellbee.map.Map;
import by.hellbee.model.core.Entity;
import by.hellbee.service.PathfinderService;

import java.util.*;

public class BFS implements PathfinderService {

    private final java.util.Map<Cell, Cell> pathMemorizer = new HashMap<>(); // key = end, value = start
    private final Set<Cell> visitedCells = new HashSet<>();

    private final Deque<Cell> nodes = new ArrayDeque<>();

    @Override
    public Cell findNextStep(Map map, Cell startCell, Class<? extends Entity> targetEntity) {
        pathMemorizer.clear();
        visitedCells.clear();
        nodes.clear();

        nodes.offer(startCell);
        visitedCells.add(startCell);
        while (!nodes.isEmpty()) {
            Cell currentCell = nodes.poll();

            if (map.getEntityOnCell(currentCell) != null
                    && targetEntity.isInstance(map.getEntityOnCell(currentCell))
                    && !currentCell.equals(startCell)) {

                Cell step = currentCell;
                while (!pathMemorizer.get(step).equals(startCell)) {
                    step = pathMemorizer.get(step);
                }
                return step;

            }

            List<Cell> neighbours = map.getWalkableNeighbors(currentCell);
            for (Cell neighbour : neighbours) {
                if (!visitedCells.contains(neighbour)) {
                    if (map.getEntityOnCell(neighbour) == null
                            || targetEntity.isInstance(map.getEntityOnCell(neighbour))) {
                        visitedCells.add(neighbour);
                        pathMemorizer.put(neighbour, currentCell);
                        nodes.offer(neighbour);
                    }
                }
            }
        }
        return null;
    }

}
