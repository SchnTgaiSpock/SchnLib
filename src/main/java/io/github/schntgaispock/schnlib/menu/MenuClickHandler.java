package io.github.schntgaispock.schnlib.menu;

import org.bukkit.event.inventory.InventoryClickEvent;

@FunctionalInterface
public interface MenuClickHandler {

    public void onClick(InventoryClickEvent e);

}
