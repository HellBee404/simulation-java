package by.hellbee;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Board {

    private static final int MAP_SIZE_X = 20;
    private static final int MAP_SIZE_Y = 10;
    private static int brownPlugCount = 0;
    private static int greenPlugCount = 0;

    private static final Random random = new Random();

    private static final String brownPlug = "\uD83D\uDFEB";
    private static final String greenPlug = "\uD83D\uDFE9";

    // убрать static
    private static final Map<String, String> grid = new HashMap<>();

    static {
        for (int y = 0; y < MAP_SIZE_Y; y++) {
            for (int x = 0; x < MAP_SIZE_X; x++) {
                grid.put(x + "," + y, brownPlug);
            }
        }
        int greenPlugCount = 10 + random.nextInt(16);
        Board.brownPlugCount = (MAP_SIZE_X * MAP_SIZE_Y) - greenPlugCount;
        Board.greenPlugCount = greenPlugCount;
        int placed = 0;
        while (placed < greenPlugCount) {
            int randomY = random.nextInt(MAP_SIZE_Y); // row
            int randomX = random.nextInt(MAP_SIZE_X); // col
            String randomKey = randomX + "," + randomY;
            if (grid.get(randomKey).equals(brownPlug)) {
                grid.put(randomKey, greenPlug);
                placed++;
            }
        }
    }

    public static void printBoard() {
        for (int y = 0; y < MAP_SIZE_Y; y++) {
            for (int x = 0; x < MAP_SIZE_X; x++) {
                System.out.print(grid.get(x + "," + y));

////////////////////////////////////////////////////////////////////////////
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) { // TEST
                    throw new RuntimeException(e);
                }
////////////////////////////////////////////////////////////////////////////

            }
            System.out.println();
        }
        System.out.println("Brown plug count: " + Board.brownPlugCount);
        System.out.println("Green plug count: " + Board.greenPlugCount);
    }
}
