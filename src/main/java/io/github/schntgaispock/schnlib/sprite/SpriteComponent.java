package io.github.schntgaispock.schnlib.sprite;


import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SpriteComponent {

    private final EntityType component;
    private final Vector offset;
    
}
