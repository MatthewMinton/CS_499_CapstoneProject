package com.matthew.animalapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Validation utilities for console input.
 * Provides cancel handling, yes/no prompts, and restricted input checks.
 */
public final class Validation {

    private static final Set<String> ALLOWED_BREEDS = new HashSet<>(Arrays.asList(
            "German Shepherd", "Labrador Retriever", "Belgian Malinois", "Bloodhound"
    ));

    private static final Set<String> ALLOWED_MONKEY_SPECIES = new HashSet<>(Arrays.asList(
            "Capuchin", "Guenon", "Macaque", "Marmoset", "Squirrel monkey", "Tamarin"
    ));

    private static final Set<String> ALLOWED_COUNTRIES = new HashSet<>(Arrays.asList(
            "United States", "Canada", "Mexico"
    ));

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private Validation() {
        // utility class, no instances
    }

    // ===== Cancel handling =====
    public static class CancelException extends RuntimeException {}

    public static void checkCancel(String input) {
        if (input != null && input.trim().equalsIgnoreCase("cancel")) {
            throw new CancelException();
        }
    }

    // ===== Yes/No =====
    public static boolean readYesNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim();
            checkCancel(input);
            if (input.equalsIgnoreCase("y")) return true;
            if (input.equalsIgnoreCase("n")) return false;
            System.out.println("Please enter 'y' or 'n'.");
        }
    }

    // ===== String =====
    public static String readNonEmpty(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " (or cancel): ");
            String input = scanner.nextLine().trim();
            checkCancel(input);
            if (!input.isEmpty()) return input;
            System.out.println("Input cannot be empty.");
        }
    }

    // ===== Gender =====
    public static String readGender(Scanner scanner) {
        while (true) {
            System.out.print("Enter gender (male/female): ");
            String input = scanner.nextLine().trim();
            checkCancel(input);
            if (input.equalsIgnoreCase("male")) return "male";
            if (input.equalsIgnoreCase("female")) return "female";
            System.out.println("Invalid gender. Please enter 'male' or 'female'.");
        }
    }

    // ===== Numeric with ranges (US units) =====
    public static int readBoundedInt(Scanner scanner, String prompt, int min, int max, String unit) {
        while (true) {
            System.out.print(prompt + " (" + unit + ", or cancel): ");
            String input = scanner.nextLine().trim();
            checkCancel(input);
            try {
                int v = Integer.parseInt(input);
                if (v >= min && v <= max) return v;
            } catch (NumberFormatException ignored) {}
            System.out.println("Please enter an integer between " + min + " and " + max + " " + unit + ".");
        }
    }

    public static double readBoundedDouble(Scanner scanner, String prompt, double min, double max, String unit) {
        while (true) {
            System.out.print(prompt + " (" + unit + ", or cancel): ");
            String input = scanner.nextLine().trim();
            checkCancel(input);
            try {
                double v = Double.parseDouble(input);
                if (v >= min && v <= max) return v;
            } catch (NumberFormatException ignored) {}
            System.out.println("Please enter a number between " + min + " and " + max + " " + unit + ".");
        }
    }

    // ===== Date =====
    public static String readDate(Scanner scanner, String prompt, int age) {
        int currentYear = LocalDate.now().getYear();
        int minYear = currentYear - (age + 1);
        while (true) {
            System.out.print(prompt + " (MM/dd/yyyy): ");
            String input = scanner.nextLine().trim();
            checkCancel(input);
            try {
                LocalDate date = LocalDate.parse(input, DATE_FMT);
                int year = date.getYear();
                if (year >= minYear && year <= currentYear) {
                    return input;
                } else {
                    System.out.println("Year must be between " + minYear + " and " + currentYear + ".");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Use MM/dd/yyyy.");
            }
        }
    }


    // ===== Restricted =====
    public static String readBreed(Scanner scanner) {
        while (true) {
            System.out.print("Enter breed " + ALLOWED_BREEDS + " (or cancel): ");
            String input = scanner.nextLine().trim();
            checkCancel(input);
            for (String breed : ALLOWED_BREEDS) {
                if (breed.equalsIgnoreCase(input)) {
                    return breed;
                }
            }
            System.out.println("Invalid breed. Allowed: " + ALLOWED_BREEDS);
        }
    }

    public static String readMonkeySpecies(Scanner scanner) {
        while (true) {
            System.out.print("Enter species " + ALLOWED_MONKEY_SPECIES + " (or cancel): ");
            String input = scanner.nextLine().trim();
            checkCancel(input);
            for (String species : ALLOWED_MONKEY_SPECIES) {
                if (species.equalsIgnoreCase(input)) {
                    return species;
                }
            }
            System.out.println("Invalid species. Allowed: " + ALLOWED_MONKEY_SPECIES);
        }
    }

    public static String readAcquisitionCountry(Scanner scanner) {
        while (true) {
            System.out.print("Enter aquisition country " + ALLOWED_COUNTRIES + " (or cancel): ");
            String input = scanner.nextLine().trim();
            checkCancel(input);
            for (String country : ALLOWED_COUNTRIES) {
                if (country.equalsIgnoreCase(input)) {
                    return country;
                }
            }
            System.out.println("Invalid country. Allowed: " + ALLOWED_COUNTRIES);
        }
    }

    public static String readInServiceCountry(Scanner scanner) {
        while (true) {
            System.out.print("Enter in service country " + ALLOWED_COUNTRIES + " (or cancel): ");
            String input = scanner.nextLine().trim();
            checkCancel(input);
            for (String country : ALLOWED_COUNTRIES) {
                if (country.equalsIgnoreCase(input)) {
                    return country;
                }
            }
            System.out.println("Invalid country. Allowed: " + ALLOWED_COUNTRIES);
        }
    }

    public static String readTrainingStatus(Scanner scanner) {
        while (true) {
            System.out.print("Enter training status (intake, Phase I–V, in service, farm) or cancel: ");
            String input = scanner.nextLine().trim();
            checkCancel(input);
            try {
                return RescueAnimal.TrainingStatus.parse(input).menuLabel();
            } catch (Exception e) {
                System.out.println("Invalid training status. Must be intake, Phase I–V, in service, or farm.");
            }
        }
    }
}
