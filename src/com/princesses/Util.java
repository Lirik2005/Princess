package com.princesses;

import com.princesses.model.EyeColor;
import com.princesses.model.HairColor;
import com.princesses.model.Princess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Util {

    private static final File file = Path.of("src", "resources", "disney-princesses.txt").toFile();

    public static List<Princess> readFile() {
        List<Princess> princessList = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                Princess princess = new Princess();
                String[] split = scanner.nextLine().replace(" | ", ",").split(",");
                princess.setId(Integer.parseInt(split[0]));
                princess.setName(split[1]);
                princess.setAge(Integer.parseInt(split[2]));
                princess.setHairColor(HairColor.valueOf(split[3].toUpperCase()));
                princess.setEyeColor(EyeColor.valueOf(split[4].toUpperCase()));
                princessList.add(princess);
            }
        } catch (IOException e) {
            System.err.println("Unexpected error in readFile() method");
        }
        return princessList;
    }

    private static void writeFile(Princess princess) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true))) {
            writePrincessFields(fileWriter, princess);

        } catch (IOException e) {
            System.err.println("Unexpected error in writeFile() method");
        }
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

    public static boolean addPrincess() {
        Scanner scanner = new Scanner(System.in);
        Princess princess = new Princess();
        System.out.println("Enter princess name:");
        princess.setName(scanner.nextLine());
        System.out.println("Enter princess id:");
        princess.setId(scanner.nextInt());
        int age;
        do {
            System.out.println("Enter princess age between 0 and 99 years:");
            age = scanner.nextInt();
            princess.setAge(age);
        } while (age <= 0 || age > 99);
        System.out.println("""
                                   Choose hair color and print a number:
                                   1. Black
                                   2. Blonde
                                   3. Platinum-blonde
                                   4. Strawberry-blonde
                                   5. Red
                                   6. Brown""");
        setHairColor(scanner, princess);
        System.out.println("Choose eye color and print a number:\n1. Brown\n2. Blue\n3. Violet\n4. Hazel");
        setEyeColor(scanner, princess);
        int beforeWriting = readFile().size();
        writeFile(princess);
        int afterWriting = readFile().size();

        return afterWriting > beforeWriting;
    }

    public static boolean deletePrincess(List<Princess> princesses, int id) {
        boolean isDeleted = princesses.removeIf(princess -> princess.getId() == id);
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {

            for (Princess princess : princesses) {
                writePrincessFields(fileWriter, princess);
            }
        } catch (IOException e) {
            System.err.println("Unexpected error in deletePrincess() method");
        }
        return isDeleted;
    }

    public static void updatePrincess(List<Princess> princesses, int id) {
        Scanner scanner = new Scanner(System.in);
        for (Princess princess : princesses) {
            int a = princess.getId();
            if (a == id) {
                System.out.println("Enter new princess name:");
                Objects.requireNonNull(princess).setName(scanner.nextLine());
                int age;
                do {
                    System.out.println("Enter new princess age between 0 and 99 years:");
                    age = scanner.nextInt();
                    princess.setAge(age);
                } while (age <= 0 || age > 99);
                System.out.println("""
                                           Choose new hair color and print a number:
                                           1. Black
                                           2. Blonde
                                           3. Platinum-blonde
                                           4. Strawberry-blonde
                                           5. Red
                                           6. Brown""");
                setHairColor(scanner, princess);
                System.out.println("Choose new eye color and print a number:\n1. Brown\n2. Blue\n3. Violet\n4. Hazel");
                setEyeColor(scanner, princess);
            }

        }
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {
            for (Princess princess : princesses) {
                writePrincessFields(fileWriter, princess);
            }
        } catch (IOException e) {
            System.err.println("Unexpected error in updatePrincess() method");
        }
    }

    private static void setEyeColor(Scanner scanner, Princess princess) {
        switch (scanner.nextInt()) {
            case 1 -> princess.setEyeColor(EyeColor.BROWN);
            case 2 -> princess.setEyeColor(EyeColor.BLUE);
            case 3 -> princess.setEyeColor(EyeColor.VIOLET);
            case 4 -> princess.setEyeColor(EyeColor.HAZEL);
        }
    }

    private static void setHairColor(Scanner scanner, Princess princess) {
        switch (scanner.nextInt()) {
            case 1 -> princess.setHairColor(HairColor.BLACK);
            case 2 -> princess.setHairColor(HairColor.BLONDE);
            case 3 -> princess.setHairColor(HairColor.PLATINUM_BLONDE);
            case 4 -> princess.setHairColor(HairColor.STRAWBERRY_BLONDE);
            case 5 -> princess.setHairColor(HairColor.RED);
            case 6 -> princess.setHairColor(HairColor.BROWN);
        }
    }

    public static void writePrincessFields(BufferedWriter fileWriter, Princess princess) throws IOException {
        fileWriter.write(String.valueOf(princess.getId()).concat(" | "));
        fileWriter.write(princess.getName().concat(" | "));
        fileWriter.write(String.valueOf(princess.getAge()).concat(" | "));
        fileWriter.write(princess.getHairColor().getHairColorFromEnum().concat(" | "));
        fileWriter.write(princess.getEyeColor().getEyeColorFromEnum());
        fileWriter.newLine();
    }
}