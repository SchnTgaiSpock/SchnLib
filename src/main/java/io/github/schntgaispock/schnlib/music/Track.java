package io.github.schntgaispock.schnlib.music;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Sound;

import io.github.schntgaispock.schnlib.SchnLib;
import io.github.schntgaispock.schnlib.SchnLib.Options;
import io.github.schntgaispock.schnlib.collections.Pair;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter 
@RequiredArgsConstructor
public class Track {

    private final Map<Integer, Chord> chords;
    private final float volume;
    private final Sound instrument;

    private int duration = 0;

    public Track(float volume, Sound instrument) {
        this(new HashMap<>(), volume, instrument);
    }

    public Track add(int duration, Chord chord) {
        this.duration += duration;
        chords.put(this.duration, chord);

        return this;
    }

    public Track add(TrackSection section, int repeat) {
        if (!section.finalized && !SchnLib.getOptions().contains(Options.IGNORE_UNFINALIZED_TRACK_SECTIONS)) {
            SchnLib.warn("TrackSection is not finalized! Changes in notes will not be reflected in this track");
        }

        for (int i = 0; i < repeat; i++) {
            for (Pair<Integer, Chord> pair : section.getChords()) {
                this.duration += pair.first();
                this.chords.put(this.duration, pair.second());
            }
        }

        return this;
    }

    public Track add(TrackSection section) {
        return add(section, 1);
    }

    public Track transpose(Sound instrument) {
        return new Track(volume, instrument);
    }

    public void play(int beat, Location at) {
        if (chords.containsKey(beat)) {
            chords.get(beat).play(at, beat, instrument);
        }
    }

}
