package io.github.schntgaispock.schnlib.recipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import com.google.common.base.Predicate;

import io.github.schntgaispock.schnlib.collections.Pair;
import io.github.schntgaispock.schnlib.recipes.inputs.RecipeIngredients;
import io.github.schntgaispock.schnlib.recipes.outputs.RecipeOutput;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Getter;

@Getter
public class Recipe {

    private static final @Getter Map<
        RecipeType, 
        List<Recipe>
    > recipes = new HashMap<>();
    private static final int CACHE_SIZE = 50;
    private static final @Getter Map<
        Integer, 
        Recipe
    > recentlyUsed = new LinkedHashMap<>(CACHE_SIZE, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry<Integer, Recipe> eldest) {
            return size() >= CACHE_SIZE;
        };
    };

    final RecipeIngredients ingredients;
    final RecipeOutput outputs;

    public Recipe(RecipeIngredients ingredients, RecipeOutput outputs) {
        this.ingredients = ingredients;
        this.outputs = outputs;
    }

    public boolean matches(@Nonnull ItemStack[] ingredients, boolean consumeIngredients) {
        return this.ingredients.matches(ingredients, consumeIngredients);
    }

    public ItemStack[] getOutputs() {
        return outputs.getOutputs();
    }

    public Pair<ItemStack[], List<ItemStack>> getGuideDisplay() {
        return ingredients.getGuideDisplay();
    }

    @ParametersAreNonnullByDefault // wtf java
    public static @Nullable Recipe searchRecipes(
        RecipeType type,
        ItemStack[] ingredients,
        @Nullable Predicate<Recipe> canCraft,
        boolean consumeIngredients,
        boolean cache,
        int hash
    ) {
        if (recentlyUsed.containsKey(hash)) {
            final Recipe recipe = recentlyUsed.get(hash);
            return canCraft.test(recipe) ? recipe : null;
        }

        for (Recipe recipe : recipes.get(type)) {
            if (canCraft != null && canCraft.test(recipe) && recipe.matches(ingredients, consumeIngredients)) {
                if (cache) {
                    recentlyUsed.put(hash, recipe);
                }
                return recipe;
            }
        }

        return null;
    }

    @ParametersAreNonnullByDefault
    public static @Nullable Recipe searchRecipes(
        RecipeType type,
        ItemStack[] ingredients,
        @Nullable Predicate<Recipe> canCraft,
        boolean consumeIngredients
    ) {
        int hash = 1;
        for (ItemStack item : ingredients) {
            hash = hash * 31 + (item == null ? 0 : item.hashCode());
        }

        return searchRecipes(type, ingredients, canCraft, consumeIngredients, true, hash);
    }

    @ParametersAreNonnullByDefault
    public static @Nullable Recipe searchRecipes(
        RecipeType type, 
        ItemStack[] ingredients,
        @Nullable Predicate<Recipe> canCraft
    ) {
        return searchRecipes(type, ingredients, canCraft, true);
    }

    @ParametersAreNonnullByDefault
    public static @Nullable Recipe searchRecipes(
        RecipeType type, 
        ItemStack[] ingredients
    ) {
        return searchRecipes(type, ingredients, null);
    }

    @SafeVarargs
    public static void registerRecipes(RecipeType recipeType, Recipe... recipes) {
        for (Recipe recipe : recipes) {
        if (Recipe.recipes.containsKey(recipeType)) {
            Recipe.recipes.get(recipeType).add(recipe);
        } else {
            final List<Recipe> newList = new ArrayList<>();
            newList.add(recipe);
            Recipe.recipes.put(recipeType, newList);
        }
        }
    }

}
