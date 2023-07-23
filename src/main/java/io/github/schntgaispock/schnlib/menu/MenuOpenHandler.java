package io.github.schntgaispock.schnlib.menu;


import org.bukkit.event.inventory.InventoryOpenEvent;

@FunctionalInterface
public interface MenuOpenHandler {

    public void onOpen(InventoryOpenEvent e);

}
