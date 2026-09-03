package by.hellbee.map.render;

import by.hellbee.map.Cell;
import by.hellbee.map.Map;
import by.hellbee.model.core.Entity;
import by.hellbee.model.factory.Sprite;

public class Render implements RendererService {

    // TODO переделать рендер, чтобы ничего не мерцало при выводе
    @Override
    public void render(Map map) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
