package io.github.schntgaispock.schnlib.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import io.github.schntgaispock.schnlib.SchnLib;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.Getter;

@Getter
public class MenuScreen implements InventoryHolder, Listener {
    
    private final String title;
    private final NamespacedKey id;
    private final ItemStack[] preset;
    private final Map<Integer, MenuClickHandler> handlers = new HashMap<>();
    private @Nullable MenuOpenHandler openHandler;
    private @Nullable MenuCloseHandler closeHandler;

    boolean hasBackButton = false;

    public MenuScreen(String title, NamespacedKey id, int rows) {
        this.title = title;
        this.id = id;
        preset = new ItemStack[rows * 9];
    }

    public MenuScreen(String title, int rows) {
        this(title, new NamespacedKey(SchnLib.getAddon(), title.toLowerCase().replace(" ", "_")), rows);
    }

    @Override
    public Inventory getInventory() {
        return null;
    }

    public MenuScreen draw(ItemStack template, int... slots) {
        for (int slot : slots) {
            preset[slot] = template;
        }

        return this;
    }

    public MenuScreen draw(Material material, String name, int... slots) {
        return draw(new CustomItemStack(material, name));
    }

    public MenuScreen button(ItemStack button, MenuClickHandler handler, int slot) {
        if (slot < preset.length && slot >= 0) {
            preset[slot] = button;
            handlers.put(slot, handler);
        }

        return this;
    }

    public MenuScreen pageButton(ItemStack button, NamespacedKey navigateTo, int slot) {
        return button(button, e -> {
            final HumanEntity player = e.getWhoClicked();
            Menu.open(player, navigateTo);
            player.getWorld().playSound(player, Sound.UI_BUTTON_CLICK, SoundCategory.PLAYERS, 1, 1);
        }, slot);
    }

    public MenuScreen backButton(ItemStack button, int slot) {
        hasBackButton = true;

        return button(button, e -> {
            final HumanEntity player = e.getWhoClicked();
            Menu.back(player);
            player.getWorld().playSound(player, Sound.UI_BUTTON_CLICK, SoundCategory.PLAYERS, 1, 1);
        }, slot);
    }

    public MenuScreen toggleButton(ItemStack buttonOn, ItemStack buttonOff, boolean def, int slot) {
        return button(def ? buttonOn : buttonOff, new MenuClickHandler() {
            private boolean state = def;

            @Override
            public void onClick(InventoryClickEvent e) {
                state = !state;
                e.setCurrentItem(state ? buttonOn : buttonOff);
                e.setCancelled(true);
                final HumanEntity player = e.getWhoClicked();
                player.getWorld().playSound(player, Sound.UI_BUTTON_CLICK, SoundCategory.PLAYERS, 1, 1);
            }
        }, slot);
    }

    public MenuScreen listButton(List<ItemStack> choices, int def, int slot) {
        if (def >= choices.size()) {
            return this;
        }

        return button(choices.get(def), new MenuClickHandler() {
            private int state = def;

            @Override
            public void onClick(InventoryClickEvent e) {
                if (e.getClick().isLeftClick() && state < choices.size() - 1) {
                    state++;
                } else if (e.getClick().isRightClick() && state > 0) {
                    state--;
                }
                e.setCurrentItem(choices.get(state));
                e.setCancelled(true);
                final HumanEntity player = e.getWhoClicked();
                player.getWorld().playSound(player, Sound.UI_BUTTON_CLICK, SoundCategory.PLAYERS, 1, 1);
            }
        }, slot);
    }

    public MenuScreen itemSlot(int maxStackSize, int slot) {
        preset[slot] = new CustomItemStack(Material.AIR, " ");
        handlers.put(slot, e -> {
            final InventoryAction action = e.getAction();
            if (action != InventoryAction.PLACE_ALL && action != InventoryAction.PLACE_SOME && action != InventoryAction.PLACE_ONE) {
                return;
            }

            final ItemStack inSlot = e.getCurrentItem();
            final ItemStack inCursor = e.getCursor();

             
            if (inSlot.getAmount() > maxStackSize) {
                // TODO check if necessary
                e.setCancelled(true);
                inCursor.setAmount(inCursor.getAmount() + inSlot.getAmount() - maxStackSize);
                inSlot.setAmount(maxStackSize);
            }
        });
        
        return this;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        final Inventory inv = e.getClickedInventory();
        if (inv == null || inv.getHolder() != this) {
            return;
        }

        final int slot = e.getSlot();
        if (handlers.containsKey(slot)) {
            handlers.get(slot).onClick(e);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        if (e.getInventory().getHolder() != this) {
            return;
        }

        e.setCancelled(true);
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        if (e.getInventory().getHolder() != this) {
            return;
        }

        if (openHandler != null) {
            openHandler.onOpen(e);
        };
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().getHolder() != this) {
            return;
        }

        if (closeHandler != null) {
            closeHandler.onClose(e);
        };
    }

}
