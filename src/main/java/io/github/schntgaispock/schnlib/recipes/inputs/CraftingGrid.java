package io.github.schntgaispock.schnlib.recipes.inputs;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.schntgaispock.schnlib.recipes.RecipeShape;
import io.github.schntgaispock.schnlib.recipes.components.RecipeComponent;
import io.github.schntgaispock.schnlib.utils.CollectionUtil;
import lombok.Getter;

@Getter
public class CraftingGrid extends RecipeIngredients {

    private final RecipeComponent<?>[] ingredients;
    private final int width;
    private final int height;
    private final RecipeShape shape;

    public CraftingGrid(RecipeComponent<?>[] ingredients, RecipeShape shape) {
        this.shape = shape;
        switch (shape) {
            case IDENTICAL:
                this.ingredients = ingredients;
                height = 3;
                width = 3;
                break;

            case TRANSLATED:
                final var result = CollectionUtil.strip(
                    ingredients, 
                    3, 3, 
                    comp -> comp == null || comp.isEmpty());
                this.ingredients = result.first();
                width = result.second().first();
                height = result.second().second();
                break;

            default:
                this.ingredients = Arrays
                    .stream(ingredients)
                    .filter(comp -> comp == null || comp.isEmpty())
                    .toArray(RecipeComponent<?>[]::new);
                height = 0;
                width = 0;
                break;
        }
    }

    @Override
    public boolean matches(ItemStack[] ingredients) {
        return switch (shape) {
            case IDENTICAL -> matchIdentical(ingredients);
            case TRANSLATED -> matchTranslated(ingredients);
            case SHUFFLED -> false;
            case CONTAINING -> false;
            default -> false;
        };
    }

    public boolean matchIdentical(ItemStack[] ingredients) {
        if (ingredients.length != width * height) {
            return false;
        }

        for (int i = 0; i < ingredients.length; i++) {
            if (!this.ingredients[i].matches(ingredients[i])) {
                return false;
            }
        }
        
        return true;
    }

    public boolean matchTranslated(ItemStack[] ingredients) {
        if (ingredients.length != 9) {
            return false;
        }

        final var result = CollectionUtil.strip(
            ingredients, 
            3, 3, 
            item -> item == null || item.getType() == Material.AIR);
        ItemStack[] reduced = result.first();

        return matchIdentical(reduced);
    }

}
