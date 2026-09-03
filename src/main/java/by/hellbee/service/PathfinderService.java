package by.hellbee.service;

import by.hellbee.map.Cell;
import by.hellbee.map.Map;
import by.hellbee.model.core.Entity;

public interface PathfinderService {
    Cell findNextStep(Map map, Cell startCell, Class<? extends Entity> targetEntity);
}
