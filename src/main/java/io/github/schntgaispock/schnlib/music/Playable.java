package io.github.schntgaispock.schnlib.music;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

@FunctionalInterface
public interface Playable {

    // Mainly for internal use
    public static final Playable REST = (target, sound) -> {}; 
    
    public void play(Player target, Sound sound);

}
