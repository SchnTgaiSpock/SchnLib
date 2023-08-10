package io.github.schntgaispock.schnlib.recipes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.bukkit.inventory.ItemStack;

import io.github.schntgaispock.schnlib.recipes.inputs.RecipeIngredients;
import io.github.schntgaispock.schnlib.recipes.outputs.RecipeOutput;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Recipe<IngredientsType extends RecipeIngredients, OutputType extends RecipeOutput> {

    public static final Map<
        ? extends RecipeType, 
        List<Recipe<RecipeIngredients, RecipeOutput>>
    > recipes = new HashMap<>();
    private static final int CACHE_SIZE = 20;
    public static final Map<
        Integer, 
        Recipe<RecipeIngredients, RecipeOutput>
    > recentlyUsed = new LinkedHashMap<>(CACHE_SIZE, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry<Integer, Recipe<RecipeIngredients, RecipeOutput>> eldest) {
            return size() >= CACHE_SIZE;
        };
    };
    
    final IngredientsType ingredients;
    final OutputType outputs;

    public boolean matches(@Nonnull ItemStack[] ingredients) {
        return this.ingredients.matches(ingredients);
    }

    public ItemStack[] getOutputs() {
        return outputs.getOutputs();
    }

}
