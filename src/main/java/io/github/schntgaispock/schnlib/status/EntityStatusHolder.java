package io.github.schntgaispock.schnlib.status;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;

public class EntityStatusHolder implements StatusHolder {

    private final Reference<Entity> holder;

    public EntityStatusHolder(Entity holder) {
        this.holder = new WeakReference<>(holder);
    }

    public @Nullable Entity getHolder() {
        return holder.get();
    }
    
}
