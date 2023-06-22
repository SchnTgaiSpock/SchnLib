package io.github.schntgaispock.schnlib.recipes.inputs.types;

import javax.annotation.Nonnull;

import io.github.schntgaispock.schnlib.recipes.components.RecipeComponent;
import io.github.schntgaispock.schnlib.recipes.inputs.CraftingGrid3x3;
import lombok.Data;

/**
 * Represents an m by n crafting grid.
 * 
 * @see CraftingGrid3x3 for a 3x3 grid
 */
public interface CraftingGrid {

    @Data
    public class CraftingGridBoundingBox {
        private int top;
        private int left;
        private int bottom;
        private int right;
    }

    public abstract CraftingGridBoundingBox getRecipeBoundingBox(@Nonnull RecipeComponent<?>... recipe);
    public abstract int getHeight();
    public abstract int getWidth();

}
