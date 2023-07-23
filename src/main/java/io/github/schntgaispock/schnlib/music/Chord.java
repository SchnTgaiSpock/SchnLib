package io.github.schntgaispock.schnlib.music;

import org.bukkit.Location;
import org.bukkit.Sound;

import lombok.Getter;

public class Chord implements PlayableComponent {

    private final @Getter float volume;
    private final @Getter float pitch;
    private final @Getter int duration;
    private final @Getter Sound[] sounds;

    public Chord(float volume, float pitch, int duration, Sound... sounds) {
        this.volume = volume;
        this.pitch = pitch;
        this.duration = duration;
        this.sounds = sounds;
    }

    @Override
    public void play(Location location, float volume) {
        for (Sound sound : sounds) {
            location.getWorld().playSound(location, sound, volume, volume);
        }
    }
    
}
