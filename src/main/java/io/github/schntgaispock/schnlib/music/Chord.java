package io.github.schntgaispock.schnlib.music;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import lombok.Getter;

@Getter
public class Chord implements Playable {

    private final Note[] notes;

    public Chord(Note... notes) {
        this.notes = notes;
    }

    @Override
    public void play(Player target, Sound sound) {
        for (Note note : notes) {
            note.play(target, sound);
        }
    }
    
}
