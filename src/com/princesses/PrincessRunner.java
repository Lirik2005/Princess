package com.princesses;

import com.princesses.model.Princess;

import java.util.List;
import java.util.Scanner;

import static com.princesses.Util.addPrincess;
import static com.princesses.Util.deletePrincess;
import static com.princesses.Util.get;
import static com.princesses.Util.printPrincessList;
import static com.princesses.Util.readFile;
import static com.princesses.Util.updatePrincess;

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
        System.out.println("System ready.");
        System.out.println(operations);

        Scanner scanner = new Scanner(System.in);
        String yourChoice;

        do {
            yourChoice = scanner.nextLine();
            switch (yourChoice) {
                case "list" -> {
                    printPrincessList(princesses);
                    System.out.println(operations);
                }
                case "get" -> {
                    System.out.println("Enter princess id");
                    System.out.println(get(princesses, scanner.nextInt()));
                    System.out.println(operations);
                }
                case "add" -> {
                    addPrincess(princesses);
                    System.out.println(operations);
                }
                case "delete" -> {
                    System.out.println("Enter princess id");
                    deletePrincess(princesses, scanner.nextInt());
                    System.out.println(operations);
                }
                case "update" -> {
                    System.out.println("Enter princess id");
                    updatePrincess(princesses, scanner.nextInt());
                    System.out.println(operations);
                }
                case "exit" -> System.exit(0);
            }
        } while (true);
    }
}
