package com.princesses;

import com.princesses.model.EyeColor;
import com.princesses.model.HairColor;
import com.princesses.model.Princess;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Util {

    private static final File file = Path.of("src", "resources", "disney-princesses.txt").toFile();

    public static List<Princess> readFile() {
        List<Princess> princessList = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                Princess princess = new Princess();
                String[] split = scanner.nextLine().replace(" | ", ",").split(",");
                princess.setId(parseInt(split[0]));
                princess.setName(split[1]);
                princess.setAge(parseInt(split[2]));
                princess.setHairColor(HairColor.valueOf(split[3].toUpperCase()));
                princess.setEyeColor(EyeColor.valueOf(split[4].toUpperCase()));
                princessList.add(princess);
            }
        } catch (IOException e) {
            System.err.println("Unexpected error in readFile() method");
        }
        return princessList;
    }

    public static void printPrincessList(List<Princess> princesses) {
        princesses.forEach(System.out::println);
    }

    public static Princess get(List<Princess> princesses, int id) {
        for (Princess princess : princesses) {
            if (princess.getId() == id) {
                return princess;
            }
        }
        return new Princess();
    }

    public static boolean addPrincess(List<Princess> princesses, String[] fields) {
        Princess princess = new Princess();
        princess.setName(fields[2]);
        princess.setId(parseInt(fields[1]));
        if (parseInt(fields[3]) <= 99 && parseInt(fields[3]) > 0) {
            princess.setAge(parseInt(fields[3]));
        } else {
            System.err.println("Age should be between 0 and 99 years");
            return false;
        }
        setHairColor(princess, fields);
        setEyeColor(princess, fields);
        int beforeWriting = princesses.size();
        princesses.add(princess);
        int afterWriting = princesses.size();

        return afterWriting > beforeWriting;
    }

    public static boolean deletePrincess(List<Princess> princesses, int id) {
        return princesses.removeIf(princess -> princess.getId() == id);
    }

    public static void updatePrincess(List<Princess> princesses, int id, String[] fields) {
        for (Princess princess : princesses) {
            int a = princess.getId();
            if (a == id) {
                Objects.requireNonNull(princess).setName(fields[2]);
                if (parseInt(fields[3]) <= 99 && parseInt(fields[3]) > 0) {
                    princess.setAge(parseInt(fields[3]));
                } else {
                    System.err.println("Age should be between 0 and 99 years");
                    break;
                }
                princess.setAge(parseInt(fields[3]));
                setHairColor(princess, fields);
                setEyeColor(princess, fields);
            }
        }
    }

    private static void setEyeColor(Princess princess, String[] fields) {
        switch (fields[5].toLowerCase()) {
            case "brown" -> princess.setEyeColor(EyeColor.BROWN);
            case "blue" -> princess.setEyeColor(EyeColor.BLUE);
            case "violet" -> princess.setEyeColor(EyeColor.VIOLET);
            case "hazel" -> princess.setEyeColor(EyeColor.HAZEL);
        }
    }

    private static void setHairColor(Princess princess, String[] fields) {
        switch (fields[4].toLowerCase()) {
            case "black" -> princess.setHairColor(HairColor.BLACK);
            case "blonde" -> princess.setHairColor(HairColor.BLONDE);
            case "platinum-blonde" -> princess.setHairColor(HairColor.PLATINUM_BLONDE);
            case "strawberry-blonde" -> princess.setHairColor(HairColor.STRAWBERRY_BLONDE);
            case "red" -> princess.setHairColor(HairColor.RED);
            case "brown" -> princess.setHairColor(HairColor.BROWN);
        }
    }
}