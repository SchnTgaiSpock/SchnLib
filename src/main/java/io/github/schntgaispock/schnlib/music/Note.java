package io.github.schntgaispock.schnlib.music;

import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Note implements Playable {

    public static final Note FS0 = new Note(0);
    public static final Note G0 = new Note(1);
    public static final Note GS0 = new Note(2);
    public static final Note A0 = new Note(3);
    public static final Note AS0 = new Note(4);
    public static final Note B0 = new Note(5);
    public static final Note C0 = new Note(6);
    public static final Note CS0 = new Note(7);
    public static final Note D0 = new Note(8);
    public static final Note DS0 = new Note(9);
    public static final Note E0 = new Note(10);
    public static final Note F0 = new Note(11);
    public static final Note FS1 = new Note(12);
    public static final Note G1 = new Note(13);
    public static final Note GS1 = new Note(14);
    public static final Note A1 = new Note(15);
    public static final Note AS1 = new Note(16);
    public static final Note B1 = new Note(17);
    public static final Note C1 = new Note(18);
    public static final Note CS1 = new Note(19);
    public static final Note D1 = new Note(20);
    public static final Note DS1 = new Note(21);
    public static final Note E1 = new Note(22);
    public static final Note F1 = new Note(23);
    public static final Note FS2 = new Note(24);

    private final float pitch;
    private final float volume;

    public Note(float pitch) {
        this(pitch, 1.0f);
    }

    public Note(int note, float volume) {
        this((float) Math.pow(2, note / 12.0 - 1), volume);
    }

    public Note(int note) {
        this(note, 1.0f);
    }

    public Note volume(float volume) {
        return new Note(pitch, volume);
    }

    @Override
    public void play(Player target, Sound sound) {
        target.playSound(target, sound, SoundCategory.MUSIC, volume, pitch);
    }
    
}
