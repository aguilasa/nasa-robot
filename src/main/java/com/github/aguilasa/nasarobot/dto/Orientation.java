package com.github.aguilasa.nasarobot.dto;

public enum Orientation {
    N, E, S, W;

    public Orientation rotateLeft() {
        return values()[(ordinal() + 3) % 4];
    }

    public Orientation rotateRight() {
        return values()[(ordinal() + 1) % 4];
    }
}
