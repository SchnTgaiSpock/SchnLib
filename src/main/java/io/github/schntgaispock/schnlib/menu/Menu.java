package io.github.schntgaispock.schnlib.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;

import io.github.schntgaispock.schnlib.SchnLib;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@Getter
@UtilityClass
public class Menu {

    private static final Map<UUID, NavigationStack> navigationStacks = new HashMap<>();

    private static final Map<NamespacedKey, MenuScreen> screens = new HashMap<>();

    public static void register(MenuScreen... screens) throws IllegalStateException {
        for (MenuScreen screen : screens) {
            Menu.screens.put(screen.getId(), screen);
            Bukkit.getServer().getPluginManager().registerEvents(screen, SchnLib.getAddon("Could not register menu screens because SchnLib was not set up correctly! Make sure to call SchnLib#setup() with your plugin instance"));
        }
    }

    public static void open(HumanEntity player, NamespacedKey id) {
        final MenuScreen screen = screens.get(id);
        if (screen == null) {
            SchnLib.error("Could not open screen with id '" + id + "'. There may be a typo, or the screen has not been registered");
            return;
        }
        final NavigationStack stack = getStack(player);
        player.closeInventory();
        final Inventory inv = Bukkit.createInventory(screen, screen.getPreset().length, screen.getTitle());
        if (!screen.hasBackButton) {
            // No point wasting memory on inventories we are not going to see again
            stack.clear();
        }
        stack.add(inv);
        player.openInventory(inv);
    }

    public static @Nullable NavigationStack getStack(HumanEntity player) {
        return navigationStacks.get(player.getUniqueId());
    }

    public static void reOpen(HumanEntity player) {
        final Inventory inv = getStack(player).peek();
        if (inv != null) {
            player.openInventory(inv);
        }
    }

    public static void back(HumanEntity player) {
        final NavigationStack stack = getStack(player);
        stack.pop();
        player.closeInventory();
        final Inventory inv = stack.peek();
        if (inv != null) {
            player.openInventory(inv);
        }
    }

    public static void clearStack(HumanEntity player) {
        navigationStacks.get(player.getUniqueId()).clear();
    }

}
