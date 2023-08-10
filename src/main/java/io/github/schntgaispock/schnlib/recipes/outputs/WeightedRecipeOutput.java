package io.github.schntgaispock.schnlib.recipes.outputs;

import org.bukkit.inventory.ItemStack;

import io.github.schntgaispock.schnlib.collections.LootTable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WeightedRecipeOutput implements RecipeOutput {

    final LootTable<ItemStack> outputTable;

    @Override
    public ItemStack[] getOutputs() {
        return new ItemStack[] { outputTable.generate().clone() };
    }
    
}