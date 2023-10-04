package io.github.schntgaispock.schnlib.status;

import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerStatusHolder extends StatusHolder<UUID> {

    public PlayerStatusHolder(Player holder) {
        super(holder.getUniqueId());
    }

    public PlayerStatusHolder(UUID holder) {
        super(holder);
    }
    
}
