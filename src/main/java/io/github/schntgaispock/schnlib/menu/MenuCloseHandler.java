package io.github.schntgaispock.schnlib.menu;


import org.bukkit.event.inventory.InventoryCloseEvent;

@FunctionalInterface
public interface MenuCloseHandler {

    public void onClose(InventoryCloseEvent e);

}
