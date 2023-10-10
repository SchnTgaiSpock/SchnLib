package io.github.schntgaispock.schnlib.status;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class BlockStatusHolder extends StatusHolder<Location> {

    public BlockStatusHolder(Block holder) {
        super(holder.getLocation());
    }

    public BlockStatusHolder(Location holder) {
        super(holder);
    }
    
}
