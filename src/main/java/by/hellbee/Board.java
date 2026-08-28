package by.hellbee;

import by.hellbee.model.Sprite;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Board {

    private static final int MAP_SIZE_X = 20;
    private static final int MAP_SIZE_Y = 10;
    private static int floorCellCount = 0;
    private static int grassCount = 0;
    private static int rockCount = 0;

    private final Random random = new Random();

    private final Map<String, String> grid = new HashMap<>();

    {
        for (int y = 0; y < MAP_SIZE_Y; y++) {
            for (int x = 0; x < MAP_SIZE_X; x++) {
                grid.put(x + "," + y, Sprite.FLOOR);
            }
        }
        int grassCount = 10 + random.nextInt(16);
        int rockCount = 10 + random.nextInt(16);
        Board.floorCellCount = (MAP_SIZE_X * MAP_SIZE_Y) - rockCount - grassCount;
        Board.grassCount = grassCount;
        Board.rockCount = rockCount;
        int placed = 0;

        // расстановка травы
        while (placed < grassCount) {
            int randomY = random.nextInt(MAP_SIZE_Y); // row
            int randomX = random.nextInt(MAP_SIZE_X); // col
            String randomKey = randomX + "," + randomY;
            if (grid.get(randomKey).equals(Sprite.FLOOR) && !grid.get(randomKey).equals(Sprite.ROCK)) {
                grid.put(randomKey, Sprite.GRASS);
                placed++;
            }
        }
        placed = 0;

        // расстановка камней
        while (placed < grassCount) {
            int randomY = random.nextInt(MAP_SIZE_Y); // row
            int randomX = random.nextInt(MAP_SIZE_X); // col
            String randomKey = randomX + "," + randomY;
            if (grid.get(randomKey).equals(Sprite.FLOOR) && !grid.get(randomKey).equals(Sprite.GRASS)) {
                grid.put(randomKey, Sprite.ROCK);
                placed++;
            }
        }
    }

    public void printBoard() {
        for (int y = 0; y < MAP_SIZE_Y; y++) {
            for (int x = 0; x < MAP_SIZE_X; x++) {
                System.out.print(grid.get(x + "," + y));

////////////////////////////////////////////////////////////////////////////
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) { /////////////// FOR TEST
//                    throw new RuntimeException(e);
//                }
////////////////////////////////////////////////////////////////////////////

            }
            System.out.println();
        }
        System.out.println("Floor cell count: " + Board.floorCellCount);
        System.out.println("Grass count: " + Board.grassCount);
        System.out.println("Rock count: " + Board.rockCount);
    }
}
