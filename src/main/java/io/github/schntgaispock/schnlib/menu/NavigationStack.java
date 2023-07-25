package io.github.schntgaispock.schnlib.menu;

import java.util.Deque;
import java.util.LinkedList;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import lombok.Getter;

public class NavigationStack {

    private final @Getter Player player;
    private final Deque<Inventory> stack = new LinkedList<>();

    public NavigationStack(Player player) {
        this.player = player;
    }

    public void add(Inventory inv) {
        stack.add(inv);
    }

    /**
     * Returns the last menu inventory viewed by the player, or null
     * if there was none.
     * 
     * @return
     */
    public @Nullable Inventory pop() {
        return stack.pop();
    }

    public @Nullable Inventory peek() {
        return stack.peek();
    }

    public void clear() {
        stack.clear();
    }

}
