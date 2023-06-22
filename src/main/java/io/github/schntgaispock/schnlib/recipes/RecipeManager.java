package io.github.schntgaispock.schnlib.recipes;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class RecipeManager {

    private static RecipeManager instance = null;

    private final HashMap<RecipeType, Set<AbstractRecipe>> recipesByType = new HashMap<>();
    private final HashMap<Integer, Set<AbstractRecipe>> recipesByHash = new HashMap<>();

    private RecipeManager() {}

    public static RecipeManager getInstance() {
        if (instance == null) {
            instance = new RecipeManager();
        }

        return instance;
    }

    public Set<AbstractRecipe> getRecipeByType(RecipeType type) {
        return Collections.unmodifiableSet(recipesByType.getOrDefault(type, new HashSet<>()));
    }

    public Set<AbstractRecipe> getRecipeByHash(int hash) {
        return recipesByHash.get(hash);
    }

    public void registerRecipes(AbstractRecipe... recipes) {
        for (AbstractRecipe recipe : recipes) {
            if (recipesByType.containsKey(recipe.getType())) {
                recipesByType.get(recipe.getType()).add(recipe);
            } else {
                final Set<AbstractRecipe> newSet = new HashSet<>();
                newSet.add(recipe);
                recipesByType.put(recipe.getType(), newSet);
            }
        }
    }
    
}
