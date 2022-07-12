package com.princesses.model;

public enum HairColor {

    BLACK("Black"),
    BLONDE("Blonde"),
    PLATINUM_BLONDE("Platinum_blonde"),
    STRAWBERRY_BLONDE("Strawberry_blonde"),
    RED("Red"),
    BROWN("Brown");

    private final String hairColor;

    HairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getHairColorFromEnum() {
        return hairColor;
    }
}
