package io.github.schntgaispock.schnlib.music;

import org.bukkit.Location;
import org.bukkit.Sound;

import lombok.Getter;

// TODO this entire thing

public class ProlongedChord extends Chord {

    public ProlongedChord(float volume, float pitch, int duration, int ticksPerBeat, Sound... sounds) {
        super(volume, pitch, duration, sounds);
    }

    @Override
    public void play(Location location, float volume) {
        for (Sound sound : getSounds()) {
            location.getWorld().playSound(location, sound, volume, volume);
        }
    }
    
}
