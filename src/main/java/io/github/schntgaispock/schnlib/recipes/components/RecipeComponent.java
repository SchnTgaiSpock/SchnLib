package io.github.schntgaispock.schnlib.recipes.components;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;

public interface RecipeComponent<T> {

    public @Nonnull T getComponent();
    public boolean matches(@Nullable ItemStack item);
    public boolean isEmpty();
    public ItemStack getDisplayItem();

    public static @Nonnull SingleRecipeComponent of(@Nonnull ItemStack item) {
        return new SingleRecipeComponent(item);
    }

    public static @Nonnull GroupRecipeComponent of(@Nonnull Set<ItemStack> group) {
        return new GroupRecipeComponent(group);
    }

    public static @Nonnull TagRecipeComponent of(@Nonnull SlimefunTag tag) {
        return new TagRecipeComponent(tag);
    }
    
}
