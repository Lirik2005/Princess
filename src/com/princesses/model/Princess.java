package com.princesses.model;

import static java.lang.String.format;

public class Princess {

    private Integer id;
    private String name;
    private Integer age;
    private HairColor hairColor;
    private EyeColor eyeColor;
    
    public Princess() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    @Override
    public String toString() {
        return format("Princess id: %d,\nPrincess name: %s,\nPrincess age: %d,\nPrincess hair color: %s,\nPrincess eye color: %s\n", id,
                      name, age, hairColor.getHairColorFromEnum(), eyeColor.getEyeColorFromEnum());
    }
}
