package io.github.schntgaispock.schnlib.music;

import java.util.ArrayList;
import java.util.List;

import io.github.schntgaispock.schnlib.SchnLib;
import lombok.Getter;

public class TrackSection {

    private final @Getter List<Chord> chords = new ArrayList<>();
    private @Getter int duration = 0;

    boolean finalized = false;

    public TrackSection add(Chord... chords) {
        if (finalized) {
            SchnLib.error("Cannot add chords because this TrackSection is already finalized!");
            return this;
        }

        for (Chord chord : chords) {
            this.chords.add(chord);
            duration += chord.getDuration();
        }

        return this;
    }

    public void finalize() {
        finalized = true;
    }
    
}
