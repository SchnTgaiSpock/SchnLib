package io.github.schntgaispock.schnlib.sprite;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Consumer;
import org.bukkit.util.Vector;

import lombok.Getter;

@Getter
public class SpriteTemplate implements Cloneable {
    
    private final List<SpriteComponent> components = new ArrayList<>();
    private final @Nullable Consumer<Entity> setup;

    public SpriteTemplate(EntityType entityType, Vector offset, Consumer<Entity> setup) {
        components.add(new SpriteComponent(entityType, new Vector(0, 0, 0)));
        this.setup = setup;
    }

    public SpriteTemplate(EntityType entityType, Consumer<Entity> setup) {
        this(entityType, new Vector(0, 0, 0), setup);
    }

    public SpriteTemplate(EntityType entityType) {
        this(entityType, null);
    }
    
}
