package io.github.schntgaispock.schnlib.recipes.components;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import lombok.Getter;

@Getter
public class TagRecipeComponent implements RecipeComponent<SlimefunTag> {

    private final SlimefunTag component;

    public TagRecipeComponent(@Nonnull SlimefunTag component) {
        this.component = component;
    }

    @Override
    public boolean matches(@Nullable ItemStack item) {
        return getComponent().getValues().stream().anyMatch(mat -> ItemUtils.canStack(new ItemStack(mat), item));
    }

    @Override
    public boolean isEmpty() {
        return getComponent().isEmpty();
    }

    @Override
    public ItemStack getDisplayItem() {
        if (isEmpty()) return null;
        return new ItemStack(component.stream().findFirst().get());
    }
    
}
