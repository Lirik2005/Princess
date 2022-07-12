package com.princesses;

import com.princesses.model.Princess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.princesses.Util.addPrincess;
import static com.princesses.Util.deletePrincess;
import static com.princesses.Util.get;
import static com.princesses.Util.printPrincessList;
import static com.princesses.Util.readFile;
import static com.princesses.Util.updatePrincess;
import static com.princesses.Util.writePrincessFields;

public class PrincessRunner {

    public static void main(String[] args) {

        String operations = """
                Choose your command:
                1. Get all princess
                2. Get princess by id
                3. Add new princess
                4. Delete princess by id
                5. Update princess by id
                6. Exit program""";
        List<Princess> princesses = readFile();
        List<Princess> primaryPrincessList = new ArrayList<>();
        primaryPrincessList.addAll(princesses);
        System.out.println("System ready.");
        System.out.println(operations);

        Scanner scanner = new Scanner(System.in);
        int yourChoice;

        do {
            yourChoice = scanner.nextInt();
            switch (yourChoice) {
                case 1 -> {
                    printPrincessList(princesses);
                    System.out.println(operations);
                }
                case 2 -> {
                    System.out.println("Enter princess id");
                    System.out.println(get(princesses, scanner.nextInt()));
                    System.out.println(operations);
                }
                case 3 -> {
                    addPrincess();
                    System.out.println(operations);
                }
                case 4 -> {
                    System.out.println("Enter princess id");
                    deletePrincess(princesses, scanner.nextInt());
                    System.out.println(operations);
                }
                case 5 -> {
                    System.out.println("Enter princess id");
                    updatePrincess(princesses, scanner.nextInt());
                    System.out.println(operations);
                }
                case 6 -> {
                    File file = Path.of("src", "resources", "disney-princesses.txt").toFile();
                    try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {
                        for (Princess primaryPrincess : primaryPrincessList) {
                            writePrincessFields(fileWriter, primaryPrincess);
                        }
                    } catch (IOException e) {
                        System.err.println("Unexpected error in updatePrincess() method");
                    }
                    System.exit(0);
                }
            }
        } while (true);
    }
}
