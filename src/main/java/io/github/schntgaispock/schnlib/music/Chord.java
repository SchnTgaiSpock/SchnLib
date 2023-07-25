package io.github.schntgaispock.schnlib.music;

import org.bukkit.Location;
import org.bukkit.Sound;

import lombok.Getter;

public class Chord {

    private final @Getter float volume;
    private final @Getter float[] pitches;

    public Chord(float volume, float... pitches) {
        this.volume = volume;
        this.pitches = pitches;
    }

    public Chord(float volume, NotePitch... pitches) {
        this.volume = volume;
        this.pitches = new float[pitches.length];
        for (int i = 0; i < pitches.length; i++) {
            this.pitches[i] = pitches[i].pitch();
        }
    }

    public void play(Location location, float volume, Sound instrument) {
        final float trueVolume = volume * this.volume;
        for (float pitch : pitches) {
            location.getWorld().playSound(location, instrument, trueVolume, pitch);
        }
    }
    
}
