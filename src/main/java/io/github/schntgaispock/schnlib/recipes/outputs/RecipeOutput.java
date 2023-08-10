package io.github.schntgaispock.schnlib.recipes.outputs;

import org.bukkit.inventory.ItemStack;

@FunctionalInterface
public interface RecipeOutput {
    
    public ItemStack[] getOutputs();

}
