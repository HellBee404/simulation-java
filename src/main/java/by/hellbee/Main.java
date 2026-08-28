package by.hellbee;

import by.hellbee.map.Action;
import by.hellbee.map.Map;
import by.hellbee.map.Render;
import by.hellbee.map.RendererService;

public class Main {

    public static void main(String[] args) {
        while (true) {
            Action action = new Action();
            action.initActions();
            Map map = action.getMap();
            RendererService render = new Render();
            render.render(map);
            System.out.println();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}