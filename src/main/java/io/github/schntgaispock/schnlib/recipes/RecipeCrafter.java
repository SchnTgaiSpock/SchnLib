package io.github.schntgaispock.schnlib.recipes;

import javax.annotation.Nonnull;

import org.bukkit.inventory.ItemStack;

import io.github.schntgaispock.schnlib.recipes.inputs.RecipeIngredients;
import io.github.schntgaispock.schnlib.recipes.outputs.RecipeOutput;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public interface RecipeCrafter {

    public @Nonnull RecipeType getRecipeType();

    public default boolean canCraft(@Nonnull Recipe<RecipeIngredients, RecipeOutput> recipe) { return true; }

    public default @Nonnull ItemStack[] attemptCraft(
        @Nonnull ItemStack[] ingredients,
        boolean consumeIngredients,
        boolean cache,
        int hash
    ) {
        final Recipe<RecipeIngredients, RecipeOutput> result = Recipe.searchRecipes(getRecipeType(), ingredients, this::canCraft, consumeIngredients, cache, hash);
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
