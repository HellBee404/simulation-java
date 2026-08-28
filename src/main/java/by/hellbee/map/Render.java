package by.hellbee.map;

import by.hellbee.model.Sprite;
import by.hellbee.model.core.Entity;

public class Render implements RendererService {

    @Override
    public void render(Map map) {
        for (int i = 0; i < map.getColumns(); i++) {
            for (int j = 0; j < map.getRows(); j++) {
                Cell cell = new Cell(i, j);
                Entity entity = map.getEntityOnCell(cell);
                if (entity != null) {
                    System.out.print(entity.getSprite());
                } else {
                    System.out.print(Sprite.FLOOR);
                }
            }
            System.out.println();
        }
    }

}
