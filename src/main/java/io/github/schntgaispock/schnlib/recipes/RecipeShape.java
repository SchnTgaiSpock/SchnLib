package io.github.schntgaispock.schnlib.recipes;

/**
 * The shape of the recipe.
 * It is up to the recipe implementation to determine how to match the recipe
 * based on shape, but in general:
 * <ul>
 * <li>SHAPED inputs require the recipe to be in the exact same
 * configuration</li>
 * <li>SHAPELESS inputs require any permutation of the recipe</li>
 * <li>CONTAINS inputs require any permutation of the recipe <em>to be
 * present</em>. I.e. Extra parts in the given recipe are allowed</li>
 * <li>NONE inputs do not care about shape for some reason or another</li>
 * </ul>
 */
public enum RecipeShape {

    SHAPED, SHAPELESS, CONTAINS, NONE

}
