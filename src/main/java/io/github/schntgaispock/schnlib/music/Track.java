package io.github.schntgaispock.schnlib.music;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import lombok.Getter;

public class Track {

    private final Map<Integer, Playable> track = new HashMap<>();
    private final @Getter Sound instrument;
    private @Getter int length = 0;

    public Track(Sound instrument) {
        this.instrument = instrument;
    }

    public Track add(Playable playable, int beats) {
        track.put(length, playable);
        length += beats;
        return this;
    }

    public Track add(Track other) {
        for (Map.Entry<Integer, Playable> entry : other.track.entrySet()) {
            track.put(length + entry.getKey(), entry.getValue());
        }
        length += other.length;
        return this;
    }

    public Track add(Track other, int repeat) {
        for (int i = 0; i < repeat; i++) {
            add(other);
        }
        return this;
    }

    public void play(Player target, int beat) {
        track.getOrDefault(beat, Playable.REST).play(target, instrument);
    }
    
}
