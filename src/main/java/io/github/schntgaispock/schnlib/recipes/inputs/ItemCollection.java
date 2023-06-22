package io.github.schntgaispock.schnlib.recipes.inputs;

import java.util.Arrays;

import io.github.schntgaispock.schnlib.recipes.RecipeShape;
import io.github.schntgaispock.schnlib.recipes.components.RecipeComponent;
import lombok.Getter;

@Getter
public class ItemCollection extends RecipeInput<RecipeComponent<?>> {

    private final RecipeComponent<?>[] collection;

    public ItemCollection(RecipeComponent<?>... collection) {
        this.collection = Arrays.stream(collection).filter(comp -> comp != null && comp.getComponent() != null).toArray(RecipeComponent<?>[]::new);
    }

    public int getSize() {
        return collection.length;
    }

    @Override
    public boolean matches(RecipeComponent<?> object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matches'");
    }

    @Override
    public RecipeShape getShape() { return RecipeShape.CONTAINS; }
    
}
