package by.hellbee.map.util;

import by.hellbee.config.ConfigLoader;

public record SpawnConfig(
        int predatorPercent,
        int herbivorePercent,
        int herbivoreResourcePercent,
        int rockPercent,
        int treePercent
) {
    public static final SpawnConfig DEFAULT = new SpawnConfig
            (
                    ConfigLoader.getInt("spawn.default.predatorPercent", 5), // predator
                    ConfigLoader.getInt("spawn.default.herbivorePercent", 8), // herbivore
                    ConfigLoader.getInt("spawn.default.herbivoreResourcePercent", 20), // herbivoreResource
                    ConfigLoader.getInt("spawn.default.rockPercent", 10), // rock
                    ConfigLoader.getInt("spawn.default.treePercent", 10) // tree
            );
}
