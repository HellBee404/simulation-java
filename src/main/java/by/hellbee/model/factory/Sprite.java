package by.hellbee.model.factory;

import by.hellbee.config.ConfigLoader;

public record Sprite() {
    // Creatures
    public static final String PREDATOR = ConfigLoader.getString("sprite.predator", "\uD83E\uDD8A"); // 🦊 хищник
    public static final String HERBIVORE = ConfigLoader.getString("sprite.herbivore", "\uD83D\uDC14"); // 🐔 травоядный

    // Resources
    public static final String HERBIVORE_RESOURCE = ConfigLoader.getString("sprite.herbivoreResource", "\uD83C\uDF31"); // 🌱 ресурс для травоядных

    // Environments
    public static final String FLOOR = ConfigLoader.getString("sprite.floor", "\uD83D\uDFEB"); // 🟫 (пустая клетка)
    public static final String ROCK = ConfigLoader.getString("sprite.rock", "\uD83E\uDEA8"); // 🪨 Камень (статичное препятствие)
    public static final String TREE = ConfigLoader.getString("sprite.tree", "\uD83C\uDF33"); // 🌳 Дерево (статичное препятствие)
}