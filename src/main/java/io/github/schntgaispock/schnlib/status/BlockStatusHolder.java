package io.github.schntgaispock.schnlib.status;

import org.bukkit.Location;
import org.bukkit.block.Block;

import lombok.Getter;

public class BlockStatusHolder implements StatusHolder {

    private final @Getter Location holder;

    public BlockStatusHolder(Location holder) {
        this.holder = holder;
    }

    public BlockStatusHolder(Block holder) {
        this.holder = holder.getLocation();
    }
    
}
