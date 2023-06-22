package io.github.schntgaispock.schnlib.recipes.inputs;

import javax.annotation.Nonnull;

import io.github.schntgaispock.schnlib.recipes.RecipeShape;
import lombok.Getter;

/**
 * Contains information about a certain input/paratemer of a recipe.
 * 
 * @see CraftingGrid3x3
 * @see ItemCollection
 * @see EnumeratedSetting
 */
@Getter
public abstract class RecipeInput<T> {

    /**
     * 
     * @param object The object to check against.
     * @return True if a successful match
     */
    public abstract boolean matches(@Nonnull T object);
    public abstract RecipeShape getShape();

}
