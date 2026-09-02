package by.hellbee.model.core.entity;

import by.hellbee.model.core.Entity;
import by.hellbee.model.factory.Sprite;

public class Tree extends Entity {
    @Override
    public String getSprite() {
        return Sprite.TREE;
    }
}
