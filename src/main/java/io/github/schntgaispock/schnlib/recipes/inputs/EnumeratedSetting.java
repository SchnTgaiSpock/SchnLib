package io.github.schntgaispock.schnlib.recipes.inputs;

import io.github.schntgaispock.schnlib.recipes.RecipeShape;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnumeratedSetting<E extends Enum<E>> extends RecipeInput<E> {

    private final E setting;

    @Override
    public boolean matches(E object) {
        return object == setting;
    }

    @Override
    public RecipeShape getShape() {
        return RecipeShape.NONE;
    }
    
}
