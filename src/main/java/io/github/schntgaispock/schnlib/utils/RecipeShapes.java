package io.github.schntgaispock.schnlib.utils;

import java.util.Arrays;

import org.bukkit.inventory.ItemStack;

import lombok.experimental.UtilityClass;

/**
 * A utility class to generate various common 3x3 recipe shapes
 */
@UtilityClass
public class RecipeShapes {

    /**
     * An empty recipe
     * 
     * @return An empty ItemStack array
     */
    public static ItemStack[] empty() {
        return new ItemStack[9];
    }

    /**
     * A recipe with a single item in center
     * 
     * @param item <code>A</code> The item in the top left
     * @return An array with the following:
     *         <table>
     *         <tr>
     *         <td><code>A</code></td>
     *         <td><code>_</code></td>
     *         <td><code>_</code></td>
     *         </tr>
     *         <tr>
     *         <td><code>_</code></td>
     *         <td><code>_</code></td>
     *         <td><code>_</code></td>
     *         </tr>
     *         <tr>
     *         <td><code>_</code></td>
     *         <td><code>_</code></td>
     *         <td><code>_</code></td>
     *         </tr>
     *         </table>
     */
    public static ItemStack[] single(ItemStack item) {
        final ItemStack[] recipe = new ItemStack[9];
        recipe[0] = item;
        return recipe;
    }

    /**
     * A recipe with a single item in center
     * 
     * @param item <code>A</code> The item in the center
     * @return An array with the following:
     *         <table>
     *         <tr>
     *         <td><code>_</code></td>
     *         <td><code>_</code></td>
     *         <td><code>_</code></td>
     *         </tr>
     *         <tr>
     *         <td><code>_</code></td>
     *         <td><code>A</code></td>
     *         <td><code>_</code></td>
     *         </tr>
     *         <tr>
     *         <td><code>_</code></td>
     *         <td><code>_</code></td>
     *         <td><code>_</code></td>
     *         </tr>
     *         </table>
     */
    public static ItemStack[] singleCenter(ItemStack item) {
        final ItemStack[] recipe = new ItemStack[9];
        recipe[4] = item;
        return recipe;
    }

    /**
     * A recipe with the items in the given order
     * 
     * @param items The items in the recipe
     * @return The items array padded/shrunk to 9 elements
     */
    public static ItemStack[] collection(ItemStack... items) {
        return RecipeShapes.collection(items, 9);
    }

    private static ItemStack[] collection(ItemStack[] items, int maxLength) {
        final ItemStack[] recipe = new ItemStack[9];

        int l = Math.min(items.length, maxLength);
        for (int i = 0; i < l; i++) {
            recipe[i] = items[i];
        }
        return recipe;
    }

    /**
     * A recipe with an single item in center surrounded on 8 sides
     * 
     * @param outer <code>A</code> The item in the outer 8 slots
     * @param inner <code>B</code> The item in the middle
     * @return An array with the following:
     *         <table>
     *         <tr>
     *         <td><code>A</code></td>
     *         <td><code>A</code></td>
     *         <td><code>A</code></td>
     *         </tr>
     *         <tr>
     *         <td><code>A</code></td>
     *         <td><code>B</code></td>
     *         <td><code>A</code></td>
     *         </tr>
     *         <tr>
     *         <td><code>A</code></td>
     *         <td><code>A</code></td>
     *         <td><code>A</code></td>
     *         </tr>
     *         </table>
     */
    public static ItemStack[] cyclic(ItemStack outer, ItemStack inner) {
        return new ItemStack[] { outer, outer, outer, outer, inner, outer, outer, outer, outer };
    }

    /**
     * A recipe with an item in a ring (8 slots)
     * 
     * @param item <code>A</code> The item in the middle
     * @return An array with the following:
     *         <table>
     *         <tr>
     *         <td><code>A</code></td>
     *         <td><code>A</code></td>
     *         <td><code>A</code></td>
     *         </tr>
     *         <tr>
     *         <td><code>A</code></td>
     *         <td><code>_</code></td>
     *         <td><code>A</code></td>
     *         </tr>
     *         <tr>
     *         <td><code>A</code></td>
     *         <td><code>A</code></td>
     *         <td><code>A</code></td>
     *         </tr>
     *         </table>
     */
    public static ItemStack[] ring(ItemStack item) {
        return RecipeShapes.cyclic(item, null);
    }

    /**
     * A recipe with a single item in center surrounded on 8 sides with alternating
     * items
     * 
     * @param corner <code>A</code> The item in the corner 4 slots
     * @param middle <code>B</code> The item in the other 4 slots
     * @param inner  <code>C</code> The item in the middle
     * @return An array with the following:
     *         <table>
     *         <tr>
     *         <td><code>A</code></td>
     *         <td><code>B</code></td>
     *         <td><code>A</code></td>
     *         </tr>
     *         <tr>
     *         <td><code>B</code></td>
     *         <td><code>C</code></td>
     *         <td><code>B</code></td>
     *         </tr>
     *         <tr>
     *         <td><code>A</code></td>
     *         <td><code>B</code></td>
     *         <td><code>A</code></td>
     *         </tr>
     *         </table>
     */
    public static ItemStack[] cyclicAlternating(ItemStack corner, ItemStack middle, ItemStack inner) {
        return new ItemStack[] { corner, middle, corner, middle, inner, middle, corner, middle, corner };
    }

    /**
     * A recipe with an alternating ring of items
     * 
     * @param corner <code>A</code> The item in the corner 4 slots
     * @param middle <code>B</code> The item in the other 4 slots
     * @return An array with the following:
     *         <table>
     *         <tr>
     *         <td><code>A</code></td>
     *         <td><code>B</code></td>
     *         <td><code>A</code></td>
     *         </tr>
     *         <tr>
     *         <td><code>B</code></td>
     *         <td><code>_</code></td>
     *         <td><code>B</code></td>
     *         </tr>
     *         <tr>
     *         <td><code>A</code></td>
     *         <td><code>B</code></td>
     *         <td><code>A</code></td>
     *         </tr>
     *         </table>
     */
    public static ItemStack[] cyclicAlternating(ItemStack corner, ItemStack middle) {
        return RecipeShapes.cyclicAlternating(corner, middle, null);
    }

    /**
     * A recipe with 3 items in a row
     * 
     * @param item      The item in the recipe
     * @param rowNumber 0, 1, or 2, for the upper, middle, and lower rows,
     *                  respectively
     * @return The recipe array
     */
    public static ItemStack[] row(ItemStack item, int rowNumber) {
        final int rowStart = NumberUtil.clamp(rowNumber, 0, 2) * 3;
        final ItemStack[] recipe = new ItemStack[9];
        recipe[rowStart] = recipe[rowStart + 1] = recipe[rowStart + 2] = item;

        return recipe;
    }

    /**
     * A recipe with 3 items in a column
     * 
     * @param item      The item in the recipe
     * @param rowNumber 0, 1, or 2, for the left, center, and right columns,
     *                  respectively
     * @return The recipe array
     */
    public static ItemStack[] column(ItemStack item, int colNumber) {
        final int colStart = NumberUtil.clamp(colNumber, 0, 2);
        final ItemStack[] recipe = new ItemStack[9];
        recipe[colStart] = recipe[colStart + 3] = recipe[colStart + 6] = item;

        return recipe;
    }

    /**
     * A recipe with 3 items in a column
     * 
     * @param item  The item in the recipe
     * @param slope -1 or 1 for the descending or ascending diagonals, respectively
     * @return The recipe array
     */
    public static ItemStack[] diagonal(ItemStack item, int slope) {
        final ItemStack[] recipe = new ItemStack[9];
        if (slope == 1)
            recipe[2] = recipe[4] = recipe[6] = item;
        else if (slope == -1)
            recipe[0] = recipe[4] = recipe[8] = item;

        return recipe;
    }

    /**
     * A recipe with an item in all 9 slots
     * 
     * @param item The item in the recipe
     * @return An array with 9 <code>item</code>s
     */
    public static ItemStack[] block(ItemStack item) {
        final ItemStack[] recipe = new ItemStack[9];
        Arrays.fill(recipe, item);
        return recipe;
    }

}
