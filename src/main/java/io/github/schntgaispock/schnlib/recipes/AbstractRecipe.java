package io.github.schntgaispock.schnlib.recipes;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractRecipe {
    
    private final RecipeType type;
    private final ItemStack[] outputs;

}
