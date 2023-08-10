package io.github.schntgaispock.schnlib.recipes.inputs;

import javax.annotation.Nonnull;

import org.bukkit.inventory.ItemStack;

public abstract class RecipeIngredients {

    public abstract boolean matches(@Nonnull ItemStack[] ingredients);
    
}
