package by.hellbee.map.util;

public record SpawnConfig(
        int predatorPercent,
        int herbivorePercent,
        int grassPercent,
        int rockPercent,
        int treePercent
) {
    public static final SpawnConfig DEFAULT = new SpawnConfig
            (
                    5, // predator
                    8,  // herbivore
                    20, // grass
                    10, // rock
                    10 // tree
            );
}
