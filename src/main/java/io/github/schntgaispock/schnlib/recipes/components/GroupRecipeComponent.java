package io.github.schntgaispock.schnlib.recipes.components;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import lombok.Getter;

@Getter 
public class GroupRecipeComponent implements RecipeComponent<Set<ItemStack>> {

    private final Set<ItemStack> component;

    public GroupRecipeComponent(@Nonnull Set<ItemStack> component) {
        this.component = component;
    }

    @Override
    public boolean matches(@Nullable ItemStack item) {
        return component.stream().anyMatch(comp -> ItemUtils.canStack(comp, item));
    }

    @Override
    public boolean isEmpty() {
        return component.isEmpty();
    }

    @Override
    public ItemStack getDisplayItem() {
        if (isEmpty()) return null;
        return component.stream().findFirst().get().clone();
    }

    @Override
    public String toString() {
        return "GroupRecipeComponent(" + getComponent().toString() + ")";
    }
    
}
