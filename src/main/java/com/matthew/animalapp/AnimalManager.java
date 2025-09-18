package com.matthew.animalapp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * AnimalManager is responsible for the core business logic of the application:
 * - Managing dog and monkey intake
 * - Validating and reserving animals
 * - Printing filtered animal lists
 *
 * By isolating this logic, Driver remains lightweight and UI code stays separate
 * from domain rules. This also improves testability.
 */
public class AnimalManager {
    private static ArrayList<Dog> dogList = new ArrayList<>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<>();

    // Static initializer: Pre-populates data so the system is never empty
    static {
        initializeDogList();
        initializeMonkeyList();
    }

    // ---------------- DATA INITIALIZATION ----------------
    private static void initializeDogList() {
        Dog dog1 = new Dog("Spot", "German Shepherd", "male", "1", "25.6",
                "05/12/2019", "United States", "intake", false, "United States");
        Dog dog2 = new Dog("Rex", "Great Dane", "male", "3", "35.2",
                "02/03/2020", "United States", "Phase I", false, "United States");
        Dog dog3 = new Dog("Bella", "Chihuahua", "female", "4", "25.6",
                "12/12/2019", "Canada", "in service", true, "Canada");
        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
    }

    private static void initializeMonkeyList() {
        Monkey monkey1 = new Monkey("Brian", "male", "2", "8.5",
                "03/26/2020", "United States", "in service", false, "United States",
                "Squirrel Monkey", "14.8", "15.6", "17.2");
        Monkey monkey2 = new Monkey("George", "male", "1", "7.2",
                "09/12/2021", "Argentina", "Phase IV", true, "United States",
                "Capuchin", "19", "21.2", "18.5");
        Monkey monkey3 = new Monkey("Lisa", "female", "3", "18.2",
                "07/07/2019", "Colombia", "in service", true, "Colombia",
                "Tamarin", "15.2", "11", "12.2");
        monkeyList.add(monkey1);
        monkeyList.add(monkey2);
        monkeyList.add(monkey3);
    }

    // ---------------- INTAKE METHODS ----------------

    /**
     * Intake process for dogs.
     * Uses loops for validation so users are guided to correct their mistakes
     * without being kicked back to the main menu.
     */
    public static void intakeNewDog(Scanner scanner) {
        IntakeMenu.displayCancelMessage();

        // Name with validation + duplicate check
        String name;
        do {
            System.out.println("What is the dog's name?");
            name = scanner.nextLine().trim();
            if (cancelCheck(name, "Dog intake cancelled.")) return;
            if (!Validation.validateName(name)) {
                System.out.println("Invalid name. Only letters and spaces are allowed.");
            }
        } while (!Validation.validateName(name));

        for (Dog dog : dogList) {
            if (dog.getName().equalsIgnoreCase(name)) {
                System.out.println("This dog is already in the system.");
                return;
            }
        }

        // Breed (TODO: could validate with an external list in future)
        System.out.println("What is the dog's breed?");
        String breed = scanner.nextLine().trim();
        if (cancelCheck(breed, "Dog intake cancelled.")) return;

        // Gender
        String gender;
        do {
            System.out.println("What is the dog's gender? (male/female)");
            gender = scanner.nextLine().trim();
            if (cancelCheck(gender, "Dog intake cancelled.")) return;
            if (!Validation.validateGender(gender)) {
                System.out.println("Invalid gender. Enter 'male' or 'female'.");
            }
        } while (!Validation.validateGender(gender));

        // Age
        String age;
        do {
            System.out.println("What is the dog's age? (0–50)");
            age = scanner.nextLine().trim();
            if (cancelCheck(age, "Dog intake cancelled.")) return;
            if (!Validation.validateAge(age)) {
                System.out.println("Invalid age. Must be a whole number between 0 and 50.");
            }
        } while (!Validation.validateAge(age));

        // Weight
        String weight;
        do {
            System.out.println("What is the dog's weight? (0.1–300)");
            weight = scanner.nextLine().trim();
            if (cancelCheck(weight, "Dog intake cancelled.")) return;
            if (!Validation.validateWeight(weight)) {
                System.out.println("Invalid weight. Must be between 0.1 and 300.");
            }
        } while (!Validation.validateWeight(weight));

        // Acquisition Date
        String acquisitionDate;
        do {
            System.out.println("What is the dog's acquisition date (MM/DD/YYYY)?");
            acquisitionDate = scanner.nextLine().trim();
            if (cancelCheck(acquisitionDate, "Dog intake cancelled.")) return;
            if (!Validation.validateDate(acquisitionDate)) {
                System.out.println("Invalid date. Ensure format is MM/DD/YYYY and not in the future.");
            }
        } while (!Validation.validateDate(acquisitionDate));

        // Acquisition Country
        System.out.println("What is the dog's acquisition country?");
        String acquisitionCountry = scanner.nextLine().trim();
        if (cancelCheck(acquisitionCountry, "Dog intake cancelled.")) return;

        // Training Status
        String trainingStatus;
        do {
            System.out.println("What is the dog's training status?");
            trainingStatus = scanner.nextLine().trim();
            if (cancelCheck(trainingStatus, "Dog intake cancelled.")) return;
            if (!Validation.validateTrainingStatus(trainingStatus)) {
                System.out.println("Training status must be one of: intake, Phase I–IV, in service.");
            }
        } while (!Validation.validateTrainingStatus(trainingStatus));

        // Reserved status
        boolean reserved = getReservedStatus(scanner, "dog");

        // In-service Country
        System.out.println("Which country is the dog in service?");
        String inServiceCountry = scanner.nextLine().trim();
        if (cancelCheck(inServiceCountry, "Dog intake cancelled.")) return;

        Dog newDog = new Dog(name, breed, gender, age, weight,
                acquisitionDate, acquisitionCountry, trainingStatus, reserved, inServiceCountry);
        dogList.add(newDog);
        System.out.println("Dog successfully added to the system.");
    }

    /**
     * Intake process for monkeys.
     * Adds species and body measurements in addition to standard animal fields.
     */
    public static void intakeNewMonkey(Scanner scanner) {
        IntakeMenu.displayCancelMessage();

        // Name with validation + duplicate check
        String name;
        do {
            System.out.println("What is the monkey's name?");
            name = scanner.nextLine().trim();
            if (cancelCheck(name, "Monkey intake cancelled.")) return;
            if (!Validation.validateName(name)) {
                System.out.println("Invalid name. Only letters and spaces are allowed.");
            }
        } while (!Validation.validateName(name));

        for (Monkey monkey : monkeyList) {
            if (monkey.getName().equalsIgnoreCase(name)) {
                System.out.println("This monkey is already in the system.");
                return;
            }
        }

        // Gender
        String gender;
        do {
            System.out.println("What is the monkey's gender? (male/female)");
            gender = scanner.nextLine().trim();
            if (cancelCheck(gender, "Monkey intake cancelled.")) return;
            if (!Validation.validateGender(gender)) {
                System.out.println("Invalid gender. Enter 'male' or 'female'.");
            }
        } while (!Validation.validateGender(gender));

        // Age
        String age;
        do {
            System.out.println("What is the monkey's age? (0–50)");
            age = scanner.nextLine().trim();
            if (cancelCheck(age, "Monkey intake cancelled.")) return;
            if (!Validation.validateAge(age)) {
                System.out.println("Invalid age. Must be a whole number between 0 and 50.");
            }
        } while (!Validation.validateAge(age));

        // Weight
        String weight;
        do {
            System.out.println("What is the monkey's weight? (0.1–300)");
            weight = scanner.nextLine().trim();
            if (cancelCheck(weight, "Monkey intake cancelled.")) return;
            if (!Validation.validateWeight(weight)) {
                System.out.println("Invalid weight. Must be between 0.1 and 300.");
            }
        } while (!Validation.validateWeight(weight));

        // Acquisition Date
        String acquisitionDate;
        do {
            System.out.println("What is the monkey's acquisition date (MM/DD/YYYY)?");
            acquisitionDate = scanner.nextLine().trim();
            if (cancelCheck(acquisitionDate, "Monkey intake cancelled.")) return;
            if (!Validation.validateDate(acquisitionDate)) {
                System.out.println("Invalid date. Ensure format is MM/DD/YYYY and not in the future.");
            }
        } while (!Validation.validateDate(acquisitionDate));

        // Acquisition Country
        System.out.println("What is the monkey's acquisition country?");
        String acquisitionCountry = scanner.nextLine().trim();
        if (cancelCheck(acquisitionCountry, "Monkey intake cancelled.")) return;

        // Training Status
        String trainingStatus;
        do {
            System.out.println("What is the monkey's training status?");
            trainingStatus = scanner.nextLine().trim();
            if (cancelCheck(trainingStatus, "Monkey intake cancelled.")) return;
            if (!Validation.validateTrainingStatus(trainingStatus)) {
                System.out.println("Training status must be one of: intake, Phase I–IV, in service.");
            }
        } while (!Validation.validateTrainingStatus(trainingStatus));

        // Reserved status
        boolean reserved = getReservedStatus(scanner, "monkey");

        // In-service Country
        System.out.println("Which country is the monkey in service?");
        String inServiceCountry = scanner.nextLine().trim();
        if (cancelCheck(inServiceCountry, "Monkey intake cancelled.")) return;

        // Species
        String species;
        do {
            System.out.println("What is the monkey's species?");
            species = scanner.nextLine().trim();
            if (cancelCheck(species, "Monkey intake cancelled.")) return;
            if (!Validation.validateSpecies(species)) {
                System.out.println("Invalid species. Accepted: Capuchin, Guenon, Macaque, Tamarin.");
            }
        } while (!Validation.validateSpecies(species));

        // Tail Length
        String tailLength;
        do {
            System.out.println("What is the tail length (inches)? (1–25)");
            tailLength = scanner.nextLine().trim();
            if (cancelCheck(tailLength, "Monkey intake cancelled.")) return;
            if (!Validation.validateTailLength(tailLength)) {
                System.out.println("Invalid tail length. Must be a positive number.");
            }
        } while (!Validation.validateTailLength(tailLength));

        // Height
        String height;
        do {
            System.out.println("What is the height (inches)? (1–40)");
            height = scanner.nextLine().trim();
            if (cancelCheck(height, "Monkey intake cancelled.")) return;
            if (!Validation.validateHeight(height)) {
                System.out.println("Invalid height. Must be a positive number.");
            }
        } while (!Validation.validateHeight(height));

        // Body Length
        String bodyLength;
        do {
            System.out.println("What is the body length (inches)? (1–40)");
            bodyLength = scanner.nextLine().trim();
            if (cancelCheck(bodyLength, "Monkey intake cancelled.")) return;
            if (!Validation.validateBodyLength(bodyLength)) {
                System.out.println("Invalid body length. Must be a positive number.");
            }
        } while (!Validation.validateBodyLength(bodyLength));

        Monkey newMonkey = new Monkey(name, gender, age, weight,
                acquisitionDate, acquisitionCountry, trainingStatus, reserved,
                inServiceCountry, species, tailLength, height, bodyLength);
        monkeyList.add(newMonkey);
        System.out.println("Monkey successfully added to the system.");
    }

    // ---------------- RESERVATION ----------------

    /**
     * Reserve an animal by type and acquisition country.
     * Current design reserves the first available match.
     * Future enhancement: search by unique ID for precision.
     */
    public static void reserveAnimal(Scanner scanner) {
        String animalType;
        do {
            System.out.println("Enter animal type (Dog/Monkey): ");
            animalType = scanner.nextLine().trim();
            if (!Validation.validateReserveAnimal(animalType)) {
                System.out.println("Invalid type. Please enter 'Dog' or 'Monkey'.");
            }
        } while (!Validation.validateReserveAnimal(animalType));

        System.out.println("Enter the acquisition country: ");
        String country = scanner.nextLine().trim();

        if (animalType.equalsIgnoreCase("Monkey")) {
            for (Monkey obj : monkeyList) {
                if (obj.getAcquisitionLocation().equalsIgnoreCase(country)) {
                    obj.setReserved(true);
                    System.out.println("Monkey reserved successfully.");
                    return;
                }
            }
            System.out.println("No matching monkey found.");
        } else {
            for (Dog obj : dogList) {
                if (obj.getAcquisitionLocation().equalsIgnoreCase(country)) {
                    obj.setReserved(true);
                    System.out.println("Dog reserved successfully.");
                    return;
                }
            }
            System.out.println("No matching dog found.");
        }
    }

    // ---------------- PRINT METHODS ----------------

    /**
     * Prints animals depending on menu selection.
     * Each object relies on its overridden toString() for clean output.
     */
    public static void printAnimals(char option) {
        if (option == '4') {
            System.out.println("\n--- List of Dogs ---");
            for (Dog dog : dogList) {
                System.out.println(dog);
            }
        } else if (option == '5') {
            System.out.println("\n--- List of Monkeys ---");
            for (Monkey monkey : monkeyList) {
                System.out.println(monkey);
            }
        } else if (option == '6') {
            System.out.println("\n--- Animals Available for Service (Not Reserved) ---");
            for (Dog dog : dogList) {
                if (dog.getTrainingStatus().equalsIgnoreCase("in service") && !dog.getReserved()) {
                    System.out.println(dog);
                }
            }
            for (Monkey monkey : monkeyList) {
                if (monkey.getTrainingStatus().equalsIgnoreCase("in service") && !monkey.getReserved()) {
                    System.out.println(monkey);
                }
            }
        }
    }

    // ---------------- HELPER METHODS ----------------

    /**
     * Universal cancel check to avoid repeating string comparisons.
     */
    private static boolean cancelCheck(String input, String cancelMessage) {
        if (input.equalsIgnoreCase("cancel")) {
            System.out.println(cancelMessage);
            return true;
        }
        return false;
    }

    /**
     * Prompts for reserved status (true/false) until valid input is received.
     */
    private static boolean getReservedStatus(Scanner scanner, String animalType) {
        boolean reserved = false;
        boolean validReserved = false;
        do {
            System.out.println("Is this " + animalType + " reserved? (true/false)");
            String reservedInput = scanner.nextLine().trim();
            if (reservedInput.equalsIgnoreCase("true")) {
                reserved = true;
                validReserved = true;
            } else if (reservedInput.equalsIgnoreCase("false")) {
                reserved = false;
                validReserved = true;
            } else {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
            }
        } while (!validReserved);
        return reserved;
    }
}
