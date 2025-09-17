package com.matthew.animalapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validation {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public static boolean validateName(String name) {
        return name != null && name.matches("[A-Za-z ]+") && !name.trim().isEmpty();
    }

    public static boolean validateGender(String gender) {
        return gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female");
    }

    public static boolean validateAge(String age) {
        try {
            int num = Integer.parseInt(age);
            return num >= 0 && num <= 50;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateWeight(String weight) {
        try {
            double num = Double.parseDouble(weight);
            return num > 0.1 && num <= 300;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date, DATE_FORMAT);
            return !parsedDate.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validateTrainingStatus(String status) {
        String[] validStatuses = {"intake", "Phase I", "Phase II", "Phase III", "Phase IV", "in service"};
        for (String valid : validStatuses) {
            if (valid.equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateSpecies(String species) {
        String[] validSpecies = {"Capuchin", "Guenon", "Macaque", "Tamarin"};
        for (String valid : validSpecies) {
            if (valid.equalsIgnoreCase(species)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validatetailLength(String tailLength) {
        try {
            double num = Double.parseDouble(tailLength);
            return num > 0.1 && num <= 25;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateHeight(String height) {
        try {
            double num = Double.parseDouble(height);
            return num > 0.1 && num <= 40;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validatereserveAnimal(String animalType) {
        String[] validType = {"Dog", "Monkey"};
        for (String valid : validType) {
            if (valid.equalsIgnoreCase(animalType)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validatebodyLength(String bodyLength) {
        try {
            double num = Double.parseDouble(bodyLength);
            return num > 0.1 && num <= 40;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
