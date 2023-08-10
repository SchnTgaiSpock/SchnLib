package io.github.schntgaispock.schnlib.recipes;

public enum RecipeShape {
    IDENTICAL, // Must be a perfect copy of what is displayed
    TRANSLATED, // Shaped recipes
    SHUFFLED, // Shapeless recipes
    CONTAINING, // Shape doesn't matter and all of what is displayed must be present (e.g. Smeltery)
}
