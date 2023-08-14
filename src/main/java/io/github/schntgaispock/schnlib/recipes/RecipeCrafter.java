package io.github.schntgaispock.schnlib.recipes;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public interface RecipeCrafter {

    public @Nonnull RecipeType getRecipeType();

    public default List<Recipe> getRecipes() {
        return Recipe.getRecipes().containsKey(getRecipeType()) 
            ? Recipe.getRecipes().get(getRecipeType()) 
            : Collections.emptyList();
    }

    public default boolean canCraft(@Nonnull Recipe recipe) { return true; }

    public default @Nonnull ItemStack[] attemptCraft(
        @Nonnull ItemStack[] ingredients,
        boolean consumeIngredients,
        boolean cache,
        int hash
    ) {
        final Recipe result = Recipe.searchRecipes(getRecipeType(), ingredients, this::canCraft, consumeIngredients, cache, hash);
        return result == null ? new ItemStack[0] : result.getOutputs();
    }

    public default @Nonnull ItemStack[] attemptCraft(
        @Nonnull ItemStack[] ingredients,
        boolean consumeIngredients,
        int hash
    ) {
        return attemptCraft(ingredients, consumeIngredients, true, hash);
    }
    
    public default @Nonnull ItemStack[] attemptCraft(@Nonnull ItemStack[] ingredients, boolean consumeIngredients) {
        int hash = 1;
        for (ItemStack item : ingredients) {
            hash = hash * 31 + (item == null ? 0 : item.hashCode());
        }

        return attemptCraft(ingredients, consumeIngredients, hash);
    }

    public default @Nonnull ItemStack[] attemptCraft(@Nonnull ItemStack[] ingredients) {
        return attemptCraft(ingredients, true);
    }
    
}
