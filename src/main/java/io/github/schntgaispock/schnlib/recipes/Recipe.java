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

import io.github.schntgaispock.schnlib.recipes.inputs.RecipeIngredients;
import io.github.schntgaispock.schnlib.recipes.outputs.RecipeOutput;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Recipe<I extends RecipeIngredients, O extends RecipeOutput> {

    public static final Map<
        RecipeType, 
        List<Recipe<? extends RecipeIngredients, ? extends RecipeOutput>>
    > recipes = new HashMap<>();
    private static final int CACHE_SIZE = 50;
    public static final Map<
        Integer, 
        Recipe<? extends RecipeIngredients, ? extends RecipeOutput>
    > recentlyUsed = new LinkedHashMap<>(CACHE_SIZE, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry<Integer, Recipe<? extends RecipeIngredients, ? extends RecipeOutput>> eldest) {
            return size() >= CACHE_SIZE;
        };
    };

    final I ingredients;
    final O outputs;

    public boolean matches(@Nonnull ItemStack[] ingredients, boolean consumeIngredients) {
        return this.ingredients.matches(ingredients, consumeIngredients);
    }

    public ItemStack[] getOutputs() {
        return outputs.getOutputs();
    }

    @ParametersAreNonnullByDefault // wtf java
    public static @Nullable Recipe<? extends RecipeIngredients, ? extends RecipeOutput> searchRecipes(
        RecipeType type,
        ItemStack[] ingredients,
        @Nullable Predicate<Recipe<? extends RecipeIngredients, ? extends RecipeOutput>> canCraft,
        boolean consumeIngredients,
        boolean cache,
        int hash
    ) {
        if (recentlyUsed.containsKey(hash)) {
            final Recipe<? extends RecipeIngredients, ? extends RecipeOutput> recipe = recentlyUsed.get(hash);
            return canCraft.test(recipe) ? recipe : null;
        }

        for (Recipe<? extends RecipeIngredients, ? extends RecipeOutput> recipe : recipes.get(type)) {
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
    public static @Nullable Recipe<? extends RecipeIngredients, ? extends RecipeOutput> searchRecipes(
        RecipeType type,
        ItemStack[] ingredients,
        @Nullable Predicate<Recipe<? extends RecipeIngredients, ? extends RecipeOutput>> canCraft,
        boolean consumeIngredients
    ) {
        int hash = 1;
        for (ItemStack item : ingredients) {
            hash = hash * 31 + (item == null ? 0 : item.hashCode());
        }

        return searchRecipes(type, ingredients, canCraft, consumeIngredients, true, hash);
    }

    @ParametersAreNonnullByDefault
    public static @Nullable Recipe<? extends RecipeIngredients, ? extends RecipeOutput> searchRecipes(
        RecipeType type, 
        ItemStack[] ingredients,
        @Nullable Predicate<Recipe<? extends RecipeIngredients, ? extends RecipeOutput>> canCraft
    ) {
        return searchRecipes(type, ingredients, canCraft, true);
    }

    @ParametersAreNonnullByDefault
    public static @Nullable Recipe<? extends RecipeIngredients, ? extends RecipeOutput> searchRecipes(
        RecipeType type, 
        ItemStack[] ingredients
    ) {
        return searchRecipes(type, ingredients, null);
    }

    @SafeVarargs
    public static void registerRecipes(RecipeType recipeType, Recipe<? extends RecipeIngredients, ? extends RecipeOutput>... recipes) {
        for (Recipe<? extends RecipeIngredients, ? extends RecipeOutput> recipe : recipes) {
        if (Recipe.recipes.containsKey(recipeType)) {
            Recipe.recipes.get(recipeType).add(recipe);
        } else {
            final List<Recipe<? extends RecipeIngredients, ? extends RecipeOutput>> newList = new ArrayList<>();
            newList.add(recipe);
            Recipe.recipes.put(recipeType, newList);
        }
        }
    }

}
