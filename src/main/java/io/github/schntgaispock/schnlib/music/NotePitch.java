package io.github.schntgaispock.schnlib.music;

public enum NotePitch {
    Fs0, G0, Gs0, A0, As0, B0, C0, Cs0, D0, Ds0, E0, F0,
    Fs1, G1, Gs1, A1, As1, B1, C1, Cs1, D1, Ds1, E1, F1, Fs2;

    public float pitch() {
        return (float) Math.pow(2, (ordinal() - 12) / 12f);
    }
}
