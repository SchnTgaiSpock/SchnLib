package io.github.schntgaispock.schnlib.sprite;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Sprite {

    private final @Getter SpriteTemplate template;
    private final @Getter List<Entity> entities = new ArrayList<>();

    public void spawn(Location location) {
        for (SpriteComponent component : template.getComponents()) {
            final Entity entity = location.getWorld().spawnEntity(location, component.getComponent(), false);
            template.getSetup().accept(entity);
            entities.add(entity);
        }
    }

    public void kill() {
        entities.forEach(entity -> entity.remove());
    }
    
}
