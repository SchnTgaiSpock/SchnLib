package io.github.schntgaispock.schnlib.recipes.components;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public abstract class RecipeComponent<T> {
    protected final T component;
    public abstract boolean isEmpty();
    public abstract boolean matches(ItemStack item);
    public abstract ItemStack getDisplayItem();

    @Override
    public abstract int hashCode();

    public static final RecipeComponent<Void> EMPTY_ITEM = new RecipeComponent<Void>(null) {

        private final ItemStack displayItem = new ItemStack(Material.AIR);

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public boolean matches(ItemStack item) {
            return (item == null) || (item.getType() == Material.AIR);
        }

        @Override
        public ItemStack getDisplayItem() {
            return displayItem.clone();
        }

        @Override
        public int hashCode() {
            return Integer.MAX_VALUE;
        }

    };
}
