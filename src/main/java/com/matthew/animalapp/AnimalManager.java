package com.matthew.animalapp;

import java.util.ArrayList;
import java.util.Scanner;

public class AnimalManager {
    private static ArrayList<Dog> dogList = new ArrayList<>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<>();

    static {
        initializeDogList();
        initializeMonkeyList();
    }

    private static void initializeDogList() {
        Dog dog1 = new Dog("Spot", "German Shepherd", "male", "1", "25.6", "05/12/2019", "United States", "intake", false, "United States");
        Dog dog2 = new Dog("Rex", "Great Dane", "male", "3", "35.2", "02/03/2020", "United States", "Phase I", false, "United States");
        Dog dog3 = new Dog("Bella", "Chihuahua", "female", "4", "25.6", "12/12/2019", "Canada", "in service", true, "Canada");
        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
    }

    private static void initializeMonkeyList() {
        Monkey monkey1 = new Monkey("Brian", "male", "2", "8.5", "03/26/2020", "United States", "in service", false, "United States", "Squirrel Monkey", "14.8", "15.6", "17.2");
        Monkey monkey2 = new Monkey("George", "male", "1", "7.2", "09/12/2021", "Argentina", "Phase 4", true, "United States", "Capuchin", "19", "21.2", "18.5");
        Monkey monkey3 = new Monkey("Lisa", "female", "3", "18.2", "07/07/2019", "Colombia", "in service", true, "Colombia", "Tamarin", "15.2", "11", "12.2");
        monkeyList.add(monkey1);
        monkeyList.add(monkey2);
        monkeyList.add(monkey3);
    }

    // ---------------- INTAKE METHODS ----------------

    /**
     * Intake process for dogs.
     * Supports cancellation at any step by typing "cancel".
     */
    public static void intakeNewDog(Scanner scanner) {

        IntakeMenu.displayCancelMessage();

        String name;
        do {
            System.out.println("What is the dog's name?");
            name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("cancel")) {
                System.out.println("Dog intake cancelled.");
                return;
            }
            if (!Validation.validateName(name)) {
                System.out.println("Dog name is invalid. Name can only contain letters and spaces.");
            }
        } while (!Validation.validateName(name));


        // Prevent duplicate entries by name
        for (Dog dog : dogList) {
            if (dog.getName().equalsIgnoreCase(name)) {
                System.out.println("This dog is already in the system.");
                return;
            }
        }
        //
         //      TODO: Find API or create dog breed list to parse for input validation.
         //     TODO: Add do while as in name for menu continuation.
         //
         //
         //
        // Add do while as in name for menu continuation.
        System.out.println("What is the dog's breed?");
        String breed = scanner.nextLine();
        if (breed.equalsIgnoreCase("cancel")) return;
        breed = breed.trim();

        // Gender
        String gender;
        do {
            System.out.println("What is the dog's gender?");
            gender = scanner.nextLine().trim();
            if (gender.equalsIgnoreCase("cancel")) {
                System.out.println("Dog intake cancelled.");
                return;
            }
            if (!Validation.validateGender(gender)) {
                System.out.println("Invalid gender. Enter 'male' or 'female'.");
            }
        } while (!Validation.validateGender(gender));


        // Age
        String age;
        do {
            System.out.println("What is the dog's age?");
            age = scanner.nextLine().trim();
            if (age.equalsIgnoreCase("cancel")) {
                System.out.println("Dog intake cancelled.");
                return;
            }
            if (!Validation.validateAge(age)) {
            System.out.println("Invalid age. Must be a whole number between o and 50.");
            }
        } while (!Validation.validateAge(age));


        // Weight
        String weight;
        do {
            System.out.println("What is the dog's weight?");
            weight = scanner.nextLine().trim();
            if (weight.equalsIgnoreCase("cancel")) {
                System.out.println("Dog intake cancelled.");
                return;
            }
            if (!Validation.validateWeight(weight)) {
            System.out.println("Invalid weight. Must be a number between 0.1 and 300.");}
        } while (!Validation.validateWeight(weight));


        //Acquisition Date
        String acquisitionDate;
        do {
            System.out.println("What is the dog's acquisition date (MM/DD/YYYY)?");
            acquisitionDate = scanner.nextLine().trim();
            if (acquisitionDate.equalsIgnoreCase("cancel")) {
                System.out.println("Dog intake cancelled.");
                return;
            }
            if (!Validation.validateDate(acquisitionDate)) {
                System.out.println("Invalid acquisition date (MM/DD/YYYY).");
            }
        } while (!Validation.validateDate(acquisitionDate));


        // Acquisition Country  TODO: Add possible api of countries to parse for country validation???
        /*String acquisitionCountry;
        do {
            System.out.println("What is the dog's acquisition country?");
            acquisitionCountry = scanner.nextLine().trim();
            if (acquisitionCountry.equalsIgnoreCase("cancel")) {
                System.out.println("Dog intake cancelled.");
            }

        }*/

        // TODO: remove this acquisition country section when above implemented!!!
        System.out.println("What is the dog's acquisition country?");
        String acquisitionCountry = scanner.nextLine().trim();
        if (acquisitionCountry.equalsIgnoreCase("cancel")) return;

        // Training Status
        String trainingStatus;
        do {
            System.out.println("What is the dog's training status?");
            trainingStatus = scanner.nextLine().trim();
            if (trainingStatus.equalsIgnoreCase("cancel")) {
                System.out.println("Dog intake cancelled.");
                return;
            }
            if (!Validation.validateTrainingStatus(trainingStatus)) {
                System.out.println("Training status must be one of the following: \n\"intake\", \"Phase I\", \"Phase II\", \"Phase III\", \"Phase IV\", \"in service\".");
            }
        } while (!Validation.validateTrainingStatus(trainingStatus));


        // Reserved status
        boolean reserved = false;
        boolean validReserved = false;
        do {
            System.out.println("Is this dog reserved? (true/false)");
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


        // TODO: As in acquisition country above, create parseable list of countries to validate entries from.
        // TODO: Add do while as above.
        // In-service country
        System.out.println("Which country is the dog in service?");
        String inServiceCountry = scanner.nextLine().trim();
        if (inServiceCountry.equalsIgnoreCase("cancel")) return;

        Dog newDog = new Dog(name, breed, gender, age, weight,
                acquisitionDate, acquisitionCountry, trainingStatus, reserved, inServiceCountry);
        dogList.add(newDog);
        System.out.println("Dog successfully added to the system.");
    }

    /**
     * Intake process for monkeys.
     * Supports cancellation at any step by typing "cancel".
     */
    public static void intakeNewMonkey(Scanner scanner) {

        IntakeMenu.displayCancelMessage();

        // Name
        String name;
        do {
            System.out.println("What is the monkey's name?");
            name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("cancel")) {
                System.out.println("Monkey intake cancelled.");
                return;
            }
            if (!Validation.validateName(name)) {
                System.out.println("Monkey name is invalid. Name can only contain letters and spaces.");
            }
        } while (!Validation.validateName(name));

        // Prevent duplicate entries by name
        for (Monkey monkey : monkeyList) {
            if (monkey.getName().equalsIgnoreCase(name)) {
                System.out.println("This monkey is already in the system.");
                return;
            }
        }


        // Gender
        String gender;
        do {
            System.out.println("What is the monkey's gender?");
            gender = scanner.nextLine().trim();
            if (gender.equalsIgnoreCase("cancel")) {
                System.out.println("Monkey intake cancelled.");
                return;
            }
            if (!Validation.validateGender(gender)) {
                System.out.println("Invalid gender. Enter 'male' or 'female'.");
            }
        } while (!Validation.validateGender(gender));


        // Age
        String age;
        do {
            System.out.println("What is the monkey's age?");
            age = scanner.nextLine().trim();
            if (age.equalsIgnoreCase("cancel")) {
                System.out.println("Monkey intake cancelled.");
                return;
            }
            if (!Validation.validateAge(age)) {
                System.out.println("Invalid age. Must be a whole number between o and 50.");
            }
        } while (!Validation.validateAge(age));


        // Weight
        String weight;
        do {
            System.out.println("What is the monkey's weight?");
            weight = scanner.nextLine().trim();
            if (weight.equalsIgnoreCase("cancel")) {
                System.out.println("Monkey intake cancelled.");
                return;
            }
            if (!Validation.validateWeight(weight)) {
                System.out.println("Invalid weight. Must be a number between 0.1 and 300.");}
        } while (!Validation.validateWeight(weight));


        // Acquisition date
        String acquisitionDate;
        do {
            System.out.println("What is the monkey's acquisition date (MM/DD/YYYY)?");
            acquisitionDate = scanner.nextLine().trim();
            if (acquisitionDate.equalsIgnoreCase("cancel")) {
                System.out.println("Monkey intake cancelled.");
                return;
            }
            if (!Validation.validateDate(acquisitionDate)) {
                System.out.println("Invalid acquisition date (MM/DD/YYYY).");
            }
        } while (!Validation.validateDate(acquisitionDate));


        //TODO: add validation as stated for acq country in intakeDog

        System.out.println("What is the monkey's acquisition country?");
        String acquisitionCountry = scanner.nextLine().trim();
        if (acquisitionCountry.equalsIgnoreCase("cancel")) return;


        // Training Status
        String trainingStatus;
        do {
            System.out.println("What is the monkey's training status?");
            trainingStatus = scanner.nextLine().trim();
            if (trainingStatus.equalsIgnoreCase("cancel")) {
                System.out.println("Monkey intake cancelled.");
                return;
            }
            if (!Validation.validateTrainingStatus(trainingStatus)) {
                System.out.println("Training status must be one of the following: " +
                        "\n\"intake\", \"Phase I\", \"Phase II\", \"Phase III\", \"Phase IV\", \"in service\".");
            }
        } while (!Validation.validateTrainingStatus(trainingStatus));


        // Reserved status

        boolean reserved = false;
        boolean validReserved = false;
        do {
            System.out.println("Is this dog reserved? (true/false)");
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


        // TODO: As in acquisition country above, create parseable list of countries to validate entries from.
        // TODO: Add do while as above.
        // In-service country
        System.out.println("Which country is the monkey in service?");
        String inServiceCountry = scanner.nextLine();
        if (inServiceCountry.equalsIgnoreCase("cancel")) return;


        //Species
        String species;
        do {
            System.out.println("What is the monkey's species?");
            species = scanner.nextLine().trim();
            if (species.equalsIgnoreCase("cancel")) {
                System.out.println("Monkey intake cancelled.");
                return;
            }
            if (!Validation.validateSpecies(species)) {
                System.out.println("Invalid species. Only the following species are accepted: " +
                        "\n\"Capuchin\", \"Guenon\", \"Macaque\", \"Tamarin\".");
            }
        } while (!Validation.validateSpecies(species));


        // Tail length
        String tailLength;
        do {
            System.out.println("What is the tail length(inches)?");
            tailLength = scanner.nextLine().trim();
            if (tailLength.equalsIgnoreCase("cancel")) {
                System.out.println("Monkey intake cancelled.");
                return;
            }
            if (!Validation.validatetailLength(tailLength)) {
                System.out.println("Invalid tail length. Must be between 1 and 25.");
            }
        }while (!Validation.validatetailLength(tailLength));


        // Height
        String height;
        do {
            System.out.println("What is the height (inches)?");
            height = scanner.nextLine().trim();
            if (height.equalsIgnoreCase("cancel")) {
                System.out.println("Monkey intake cancelled.");
                return;
            }
            if (!Validation.validateHeight(height)) {
            System.out.println("Invalid height (inches). Must be between 1 and 40.");}
        } while (!Validation.validateHeight(height));


        // Body Length
        String bodyLength;
        do {
            System.out.println("What is the body length(inches)?");
            bodyLength = scanner.nextLine().trim();
            if (bodyLength.equalsIgnoreCase("cancel")) {
                System.out.println("Monkey intake cancelled.");
            }
            if (!Validation.validatetailLength(bodyLength)) {
                System.out.println("Invalid body length. Must be between 1 and 40.");
            }
        } while (!Validation.validatetailLength(bodyLength));


        Monkey newMonkey = new Monkey(name, gender, age, weight,
                acquisitionDate, acquisitionCountry, trainingStatus, reserved,
                inServiceCountry, species, tailLength, height, bodyLength);
        monkeyList.add(newMonkey);
        System.out.println("Monkey successfully added to the system.");
    }

    // ---------------- RESERVATION ----------------

    /**
     * Reserve an animal by type and acquisition country.
     * Current limitation: reserves the first available match by country.
     * Future improvement: allow search by unique name or ID.
     */
    public static void reserveAnimal(Scanner scanner) {

        String animalType;
        do {
            System.out.println("Enter animal type (Dog/Monkey): ");
            animalType = scanner.nextLine().trim();
            if (!Validation.validatereserveAnimal(animalType)) {
                System.out.println("Invalid choice. Please select Dog or Monkey");
            }
        } while(!Validation.validatereserveAnimal(animalType));

        if (animalType.equalsIgnoreCase("Monkey")) {
            System.out.println("Enter the monkey's acquisition country: ");
            String country = scanner.nextLine().trim();

            for (Monkey obj : monkeyList) {
                if (obj.getAcquisitionLocation().equalsIgnoreCase(country)) {
                    obj.setReserved(true);
                    System.out.println("Monkey reserved successfully.");
                    return;
                }
            }
            System.out.println("No matching monkey found.");
        } else if (animalType.equalsIgnoreCase("Dog")) {
            System.out.println("Enter the dog's acquisition country: ");
            String country = scanner.nextLine().trim();

            for (Dog obj : dogList) {
                if (obj.getAcquisitionLocation().equalsIgnoreCase(country)) {
                    obj.setReserved(true);
                    System.out.println("Dog reserved successfully.");
                    return;
                }
            }
            System.out.println("No matching dog found.");
        } else {
            System.out.println("Invalid animal type entered.");
        }
    }

    // ---------------- PRINT METHODS ----------------

    /**
     * Prints animals depending on menu selection.
     * Uses the toString overrides for clean, detailed output.
     * This provides readable feedback rather than raw object references.
     */
    public static void printAnimals(char option) {
        if (option == '4') {
            System.out.println("\n--- List of Dogs ---");
            for (Dog dog : dogList) {
                System.out.println(dog.toString());
            }
        } else if (option == '5') {
            System.out.println("\n--- List of Monkeys ---");
            for (Monkey monkey : monkeyList) {
                System.out.println(monkey.toString());
            }
        } else if (option == '6') {
            System.out.println("\n--- Animals Available for Service (Not Reserved) ---");

            for (Dog dog : dogList) {
                if (dog.getTrainingStatus().equalsIgnoreCase("in service") && !dog.getReserved()) {
                    System.out.println(dog.toString());
                }
            }

            for (Monkey monkey : monkeyList) {
                if (monkey.getTrainingStatus().equalsIgnoreCase("in service") && !monkey.getReserved()) {
                    System.out.println(monkey.toString());
                }
            }
        }
    }
}