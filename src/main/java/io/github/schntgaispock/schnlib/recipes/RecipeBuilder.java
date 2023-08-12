package io.github.schntgaispock.schnlib.recipes;

import java.util.Arrays;
import java.util.Set;
import java.util.function.BiFunction;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.schntgaispock.schnlib.collections.LootTable;
import io.github.schntgaispock.schnlib.recipes.components.GroupRecipeComponent;
import io.github.schntgaispock.schnlib.recipes.components.RecipeComponent;
import io.github.schntgaispock.schnlib.recipes.components.SingleRecipeComponent;
import io.github.schntgaispock.schnlib.recipes.components.TagRecipeComponent;
import io.github.schntgaispock.schnlib.recipes.inputs.CraftingGrid;
import io.github.schntgaispock.schnlib.recipes.inputs.RecipeIngredients;
import io.github.schntgaispock.schnlib.recipes.outputs.ItemRecipeOutput;
import io.github.schntgaispock.schnlib.recipes.outputs.RecipeOutput;
import io.github.schntgaispock.schnlib.recipes.outputs.WeightedRecipeOutput;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import lombok.Getter;

@Getter
public class RecipeBuilder {

    private RecipeType type;
    private RecipeComponent<?>[] ingredients;
    private RecipeShape shape = RecipeShape.TRANSLATED;
    private RecipeOutput output = RecipeOutput.EMPTY;
    private BiFunction<RecipeComponent<?>[], RecipeShape, RecipeIngredients> ingredientsProducer = CraftingGrid::new;

    public RecipeBuilder() {}

    public RecipeBuilder type(RecipeType type) {
        this.type = type;
        return this;
    }

    @SuppressWarnings("unchecked")
    public RecipeBuilder ingredients(Object... ingredients) {
        this.ingredients = new RecipeComponent<?>[ingredients.length];
        for (int i = 0; i < ingredients.length; i++) {
            final Object ingredient = ingredients[i];
            if (ingredient instanceof final ItemStack item) {
                this.ingredients[i] = new SingleRecipeComponent(item);
            } else if (ingredient instanceof final Material mat) {
                this.ingredients[i] = new SingleRecipeComponent(new ItemStack(mat));
            } else if (ingredient instanceof final Set<?> group) {
                if (group.isEmpty()) {
                    continue;
                }

                if (group.stream().findAny().get() instanceof ItemStack) {
                    this.ingredients[i] = new GroupRecipeComponent((Set<ItemStack>) group);
                }
            } else if (ingredient instanceof final SlimefunTag tag) {
                this.ingredients[i] = new TagRecipeComponent(tag);
            }
        }

        return this;
    }

    public RecipeBuilder ingredients(RecipeComponent<?>... ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public RecipeBuilder ingredients(ItemStack... ingredients) {
        return ingredients(Arrays.stream(ingredients)
            .map(ingredient -> ingredient == null ? null : new SingleRecipeComponent(ingredient))
            .toArray(RecipeComponent<?>[]::new)
        );
    }

    public RecipeBuilder output(RecipeOutput outputs) {
        this.output = outputs;
        return this;
    }

    public RecipeBuilder output(ItemStack... outputs) {
        this.output = new ItemRecipeOutput(outputs);
        return this;
    }

    public RecipeBuilder output(LootTable<ItemStack> outputs) {
        this.output = new WeightedRecipeOutput(outputs);
        return this;
    }

    public Recipe<? extends RecipeIngredients, ? extends RecipeOutput> build() {
        return new Recipe<RecipeIngredients, RecipeOutput>(ingredientsProducer.apply(ingredients, shape), output);
    }
    
}
