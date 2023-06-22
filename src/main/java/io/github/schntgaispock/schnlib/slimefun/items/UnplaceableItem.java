package io.github.schntgaispock.schnlib.slimefun.items;

import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.events.BlockPlacerPlaceEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

/**
 * UnplaceableItem is similar to {@link UnplaceableBlock} but still allows right
 * clicking menus, items, etc...
 * 
 * @author SchnTgaiSpock
 */
public class UnplaceableItem extends SimpleSlimefunItem<BlockPlaceHandler> {

    public UnplaceableItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public BlockPlaceHandler getItemHandler() {
        return new BlockPlaceHandler(true) {
            @Override
            public void onBlockPlacerPlace(BlockPlacerPlaceEvent e) {
                e.setCancelled(true);
                BlockStorage.clearBlockInfo(e.getBlock(), false);
            }

            @Override
            public void onPlayerPlace(BlockPlaceEvent e) {
                e.setCancelled(true);
                BlockStorage.clearBlockInfo(e.getBlock(), false);
            }
        };
    }

}
