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
import static java.lang.Integer.parseInt;

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
            String[] fields;
            fields = yourChoice.split(" ");
            switch (fields[0].toLowerCase()) {
                case "list" -> {
                    printPrincessList(princesses);
                    System.out.println(operations);
                }
                case "get" -> {
                    System.out.println(get(princesses, parseInt(fields[1])));
                    System.out.println(operations);
                }
                case "add" -> {
                    addPrincess(princesses, fields);
                    System.out.println(operations);
                }
                case "delete" -> {
                    deletePrincess(princesses, parseInt(fields[1]));
                    System.out.println(operations);
                }
                case "update" -> {
                    updatePrincess(princesses, parseInt(fields[1]), fields);
                    System.out.println(operations);
                }
                case "exit" -> System.exit(0);
            }
        } while (true);
    }
}
