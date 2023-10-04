package io.github.schntgaispock.schnlib.status;

import java.util.UUID;

import org.bukkit.entity.Entity;

public class EntityStatusHolder extends StatusHolder<UUID> {

    public EntityStatusHolder(Entity holder) {
        super(holder.getUniqueId());
    }

    public EntityStatusHolder(UUID holder) {
        super(holder);
    }
    
}
