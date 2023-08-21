package com.github.aguilasa.nasarobot.dto;

public enum Orientation {
    N, E, S, W;

    private static final int SIZE = values().length;

    public Orientation rotateLeft() {
        return values()[(ordinal() + (SIZE - 1)) % SIZE];
    }

    public Orientation rotateRight() {
        return values()[(ordinal() + 1) % SIZE];
    }
}
