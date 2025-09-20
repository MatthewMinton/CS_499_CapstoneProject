package com.matthew.animalapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


/**
 * Validation class centralizes all input checks for the rescue animal system.
 *
 * The goal is to keep business logic (AnimalManager) focused on workflows,
 * while all data integrity rules live here. This separation improves readability,
 * reusability, and makes it easier to adjust rules later without touching
 * intake/reservation code.
 */
public class Validation {

    // ---------------- CONSTANTS ----------------
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/uuuu").withResolverStyle(ResolverStyle.STRICT);
    private static final int MAX_AGE = 50;
    private static final double MIN_WEIGHT = 0.1;
    private static final double MAX_WEIGHT = 300.0;
    private static final double MIN_TAIL_LENGTH = 1.0;
    private static final double MAX_TAIL_LENGTH = 25.0;
    private static final double MIN_HEIGHT = 1.0;
    private static final double MAX_HEIGHT = 40.0;
    private static final double MIN_BODY_LENGTH = 1.0;
    private static final double MAX_BODY_LENGTH = 40.0;
    public static final String[] ACCEPTED_COUNTRIES = {"United States", "Canada", "Argentina", "Colombia"};
    public static final String[] ACCEPTED_BREEDS = {"French Bulldog", "Labrador Retriever", "Golden Retriever", "German Shepherd", "Poodle",
        "Bulldog", "Dachshund", "Beagle", "Rottweiler", "English Pointer"};

    // ---------------- NAME & GENDER ----------------

    /**
     * Validates animal name. Must be letters and spaces only, non-empty.
     * This avoids weird entries like numbers or punctuation in names.
     */
    public static boolean validateName(String name) {
        return name != null && name.matches("[A-Za-z ]+") && !name.trim().isEmpty();
    }

    /**
     * Validates gender field. Keeps it consistent with system assumptions.
     */
    public static boolean validateGender(String gender) {
        return gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female");
    }

    // ---------------- AGE & WEIGHT ----------------

    /**
     * Validates age as integer between 0 and MAX_AGE.
     */
    public static boolean validateAge(String age) {
        try {
            int num = Integer.parseInt(age);
            return num >= 0 && num <= MAX_AGE;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates weight as double between MIN_WEIGHT and MAX_WEIGHT.
     */
    public static boolean validateWeight(String weight) {
        try {
            double num = Double.parseDouble(weight);
            return num >= MIN_WEIGHT && num <= MAX_WEIGHT;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // ---------------- DATE ----------------

    /**
     * Validates acquisition date. Must be MM/DD/YYYY and cannot be in the future.
     */
    public static boolean validateDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date, DATE_FORMAT);
            return !parsedDate.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // ---------------- TRAINING STATUS ----------------

    /**
     * Validates training status against a fixed set of acceptable values.
     * Using an explicit whitelist prevents typos and enforces workflow states.
     */
    public static boolean validateTrainingStatus(String status) {
        String[] validStatuses = {"intake", "Phase I", "Phase II", "Phase III", "Phase IV", "in service"};
        for (String valid : validStatuses) {
            if (valid.equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }

    // ---------------- BREEDS ----------------

    /**
     * Validate dog breeds. For sake of project, only limited breeds are included.
     */
    public static boolean validateBreed(String breed) {
        String[] validBreed = ACCEPTED_BREEDS;
        for (String valid : validBreed) {
            if (valid.equalsIgnoreCase(breed)) {
                return true;
            }
        }
        return false;
    }

    // ---------------- SPECIES ----------------

    /**
     * Validates monkey species. Keeps system consistent with project requirements
     * (only certain species are trainable).
     */
    public static boolean validateSpecies(String species) {
        String[] validSpecies = {"Capuchin", "Guenon", "Macaque", "Tamarin"};
        for (String valid : validSpecies) {
            if (valid.equalsIgnoreCase(species)) {
                return true;
            }
        }
        return false;
    }

    // ---------------- MEASUREMENTS ----------------

    /**
     * Validates monkey tail length in inches. Range enforced by project rules.
     */
    public static boolean validateTailLength(String value) {
        try {
            double num = Double.parseDouble(value);
            return num >= MIN_TAIL_LENGTH && num <= MAX_TAIL_LENGTH;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates monkey height in inches. Range enforced by project rules.
     */
    public static boolean validateHeight(String value) {
        try {
            double num = Double.parseDouble(value);
            return num >= MIN_HEIGHT && num <= MAX_HEIGHT;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates monkey body length in inches. Range enforced by project rules.
     */
    public static boolean validateBodyLength(String value) {
        try {
            double num = Double.parseDouble(value);
            return num >= MIN_BODY_LENGTH && num <= MAX_BODY_LENGTH;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // ---------------- GENERAL PURPOSE ----------------

    /**
     * Validates a generic positive number. Useful for fallback scenarios.
     */
    public static boolean validatePositiveNumber(String value) {
        try {
            double num = Double.parseDouble(value);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates reservation input for animal type.
     * Enforces strict Dog/Monkey input.
     */
    public static boolean validateReserveAnimal(String animalType) {
        return animalType.equalsIgnoreCase("dog") || animalType.equalsIgnoreCase("monkey");
    }

    // ---------------------COUNTRY---------------------

    public static boolean validateCountry(String country) {
        String[] validCountry = ACCEPTED_COUNTRIES;
        for (String valid : validCountry) {
            if (valid.equalsIgnoreCase(country)) {
                return true;
            }
        }
        return false;
    }
}
