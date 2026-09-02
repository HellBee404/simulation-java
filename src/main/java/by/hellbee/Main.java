package by.hellbee;

import by.hellbee.map.Map;
import by.hellbee.map.render.Render;
import by.hellbee.map.render.RendererService;
import by.hellbee.map.util.Action;
import by.hellbee.model.core.entity.Grass;
import by.hellbee.model.core.entity.Rock;
import by.hellbee.model.core.entity.Tree;

public class Main {

    public static void main(String[] args) {
        Action action = new Action();
        action.initActions();
        Map map = action.getMap();
        RendererService renderer = new Render();

        renderer.render(map);
        System.out.println();

        for (int i = 0; i < map.getEntities().size(); i++) {
            var entity = map.getEntities().get(i);
            if (entity instanceof Grass || entity instanceof Rock || entity instanceof Tree) {
                continue;
            }
            System.out.println(entity.toString());
        }
    }
}