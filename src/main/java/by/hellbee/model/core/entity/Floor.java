package by.hellbee.model.core.entity;

import by.hellbee.model.core.Entity;
import by.hellbee.model.factory.Sprite;

public class Floor extends Entity {
    @Override
    public String getSprite() {
        return Sprite.FLOOR;
    }
}
