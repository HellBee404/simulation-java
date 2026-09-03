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

                List<Cell> path = new ArrayList<>();
                Cell step = currentCell;
                while (step != null && !step.equals(startCell)) {
                    path.add(step);
                    step = pathMemorizer.get(step);
                }
                if (!path.isEmpty()) {
                    return path.getLast();
                }

            }

            List<Cell> neighbours = map.getNeighbours(currentCell);

            for (Cell neighbour : neighbours) {
                if (!visitedCells.contains(neighbour)) {
                    Entity entity = map.getEntityOnCell(neighbour);

                    if (entity == null || targetEntity.isInstance(entity) || neighbour.equals(startCell)) {
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
