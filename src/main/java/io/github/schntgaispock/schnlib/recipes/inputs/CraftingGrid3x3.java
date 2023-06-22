package io.github.schntgaispock.schnlib.recipes.inputs;

import javax.annotation.Nonnull;

import io.github.schntgaispock.schnlib.recipes.RecipeShape;
import io.github.schntgaispock.schnlib.recipes.components.RecipeComponent;
import io.github.schntgaispock.schnlib.recipes.inputs.types.CraftingGrid;
import lombok.Getter;

@Getter
public class CraftingGrid3x3 extends RecipeInput<RecipeComponent<?>[]> implements CraftingGrid {

    private final RecipeShape shape;
    private final RecipeComponent<?>[] components;

    public CraftingGrid3x3(RecipeShape shape, RecipeComponent<?>... components) {
        this.shape = shape;
        this.components = components;
    }

    @Override
    public int getHeight() { return 3; }

    @Override
    public int getWidth() { return 3; }

    @Override
    public RecipeShape getShape() { return shape; }

    @Override
    public CraftingGridBoundingBox getRecipeBoundingBox(@Nonnull RecipeComponent<?>... recipe) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRecipeBoundingBox'");
    }

    @Override
    public boolean matches(@Nonnull RecipeComponent<?>[] object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matches'");
    }
    
}
