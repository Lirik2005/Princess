package com.princesses.model;

public enum EyeColor {

    BROWN("Brown"),
    BLUE("Blue"),
    VIOLET("Violet"),
    HAZEL("Hazel");

    private final String eyeColor;

    EyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getEyeColorFromEnum() {
        return eyeColor;
    }
}
