package io.github.schntgaispock.schnlib.recipes.components;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.inventory.ItemStack;

public interface RecipeComponent<T> {

    public @Nonnull T getComponent();
    public boolean matches(@Nullable ItemStack item);
    public boolean isEmpty();
    public ItemStack getDisplayItem();
    
}
