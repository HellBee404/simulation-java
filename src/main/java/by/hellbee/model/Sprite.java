package by.hellbee.model;

public record Sprite() {
    // Creatures
    public static final String PREDATOR = "\uD83E\uDD8A"; // 🦊 хищник
    public static final String HERBIVORE = "\uD83D\uDC14"; // 🐔 травоядный

    // Resources
    public static final String GRASS = "\uD83C\uDF31"; // 🌱 ресурс для травоядных

    // Environments
    public static final String FLOOR = "\uD83D\uDFEB"; // 🟫 (пустая клетка)
    public static final String ROCK = "\uD83E\uDEA8"; // 🪨 Камень (статичное препятствие)
    public static final String TREE = "\uD83C\uDF33"; // 🌳 Дерево (статичное препятствие)
}