package io.github.schntgaispock.schnlib.music;

import org.bukkit.Location;

public interface PlayableComponent {

    public void play(Location location, float volume);

    public int getDuration();

}
