package com.matthew.animalapp;

import java.util.List;
import java.util.Scanner;

public class MenuController {
    private final Scanner scanner;
    private final AnimalManager manager;

    public MenuController(Scanner scanner, AnimalManager manager) {
        this.scanner = scanner;
        this.manager = manager;
    }

    /**
     * Show main system menu and handle user choice
     */
    public boolean showMainMenu() {
        System.out.println("\n****************************************");
        System.out.println("*    Grazioso Salvare Rescue Manager   *");
        System.out.println("****************************************");
        System.out.println(" Please choose an option below:");
        System.out.println("Type \"cancel\" at any time to exit to main menu.");
        System.out.println(" ┌─────────────────────────────────────┐");
        System.out.println(" │ [1] Intake a new animal             │");
        System.out.println(" │ [2] Reserve/Unreserve an animal     │");
        System.out.println(" │ [3] Print list of all dogs          │");
        System.out.println(" │ [4] Print list of all monkeys       │");
        System.out.println(" │ [5] Print available animals         │");
        System.out.println(" │ [6] Update training status          │");
        System.out.println(" │ [7] Remove an animal                │");
        System.out.println(" │ [q] Quit application                │");
        System.out.println(" └─────────────────────────────────────┘");
        System.out.print(" Enter a menu selection: ");

        String choice = scanner.nextLine().trim();

        try {
            switch (choice) {
                case "1" -> showIntakeMenu();
                case "2" -> reserveAnimal();
                case "3" -> printDogs();
                case "4" -> printMonkeys();
                case "5" -> printAvailableAnimals();
                case "6" -> updateTrainingStatus();
                case "7" -> removeAnimal();
                case "q", "Q" -> {
                    return false;
                }
                default -> System.out.println("Invalid selection. Please try again.");
            }
        } catch (Validation.CancelException e) {
            System.out.println("Action cancelled. Returning to main menu.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    // ===== Intake Menu =====
    private void showIntakeMenu() {
        System.out.println("\n--- Intake Menu ---");
        System.out.println(" ┌───────────────────────────┐");
        System.out.println(" │ [1] Intake a new dog      │");
        System.out.println(" │ [2] Intake a new monkey   │");
        System.out.println(" │ [c] Cancel                │");
        System.out.println(" └───────────────────────────┘");
        System.out.print(" Enter your choice: ");

        String choice = scanner.nextLine().trim().toLowerCase();
        switch (choice) {
            case "1", "dog" -> intakeNewDog();
            case "2", "monkey" -> intakeNewMonkey();
            case "c", "cancel" -> throw new Validation.CancelException();
            default -> System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    void intakeNewDog() {
        String name = Validation.readNonEmpty(scanner, "Enter name");
        String breed = Validation.readBreed(scanner);
        String gender = Validation.readGender(scanner);
        int age = Validation.readBoundedInt(scanner, "Enter age", 1, 30, "years");
        double weight = Validation.readBoundedDouble(scanner, "Enter weight", 1, 440, "lbs");
        String acquisitionDate = Validation.readDate(scanner, "Enter acquisition date", age);
        String acquisitionCountry = Validation.readAcquisitionCountry(scanner);

        String trainingStatus = Validation.readTrainingStatus(scanner);
        String inServiceCountry = null;
        boolean reserved = false;

        if (trainingStatus.equalsIgnoreCase("in service")) {
            inServiceCountry = Validation.readInServiceCountry(scanner);
            reserved = Validation.readYesNo(scanner, "Reserve this dog immediately?");
        }

        Dog dog = new Dog(name, breed, gender, String.valueOf(age), String.valueOf(weight),
                acquisitionDate, acquisitionCountry, trainingStatus,
                reserved, inServiceCountry);

        if (Validation.readYesNo(scanner, "Confirm intake of this dog?")) {
            manager.addAnimal(dog);
            System.out.println("\nDog successfully added:\n" + dog);
        } else {
            System.out.println("Dog intake cancelled.");
        }
    }

    void intakeNewMonkey() {
        String name = Validation.readNonEmpty(scanner, "Enter name");
        String species = Validation.readMonkeySpecies(scanner);
        String gender = Validation.readNonEmpty(scanner, "Enter gender");
        int age = Validation.readBoundedInt(scanner, "Enter age", 1, 30, "years");
        double weight = Validation.readBoundedDouble(scanner, "Enter weight", 1, 440, "lbs");
        String acquisitionDate = Validation.readDate(scanner, "Enter acquisition date", age);
        String acquisitionCountry = Validation.readAcquisitionCountry(scanner);

        double tailLength = Validation.readBoundedDouble(scanner, "Enter tail length", 1, 60, "inches");
        double height = Validation.readBoundedDouble(scanner, "Enter height", 1, 100, "inches");
        double bodyLength = Validation.readBoundedDouble(scanner, "Enter body length", 1, 80, "inches");

        String trainingStatus = Validation.readTrainingStatus(scanner);
        String inServiceCountry = null;
        boolean reserved = false;

        if (trainingStatus.equalsIgnoreCase("in service")) {
            inServiceCountry = Validation.readInServiceCountry(scanner);
            reserved = Validation.readYesNo(scanner, "Reserve this monkey immediately?");
        }

        Monkey monkey = new Monkey(name, species, gender, String.valueOf(age), String.valueOf(weight),
                acquisitionDate, acquisitionCountry, trainingStatus,
                reserved, inServiceCountry, tailLength, height, bodyLength);

        if (Validation.readYesNo(scanner, "Confirm intake of this monkey?")) {
            manager.addAnimal(monkey);
            System.out.println("\nMonkey successfully added:\n" + monkey);
        } else {
            System.out.println("Monkey intake cancelled.");
        }
    }

    // ===== Reservation Logic =====
    private void reserveAnimal() {
        System.out.println("\n--- Reserve/Unreserve Animal ---");
        String searchInput = Validation.readNonEmpty(scanner, "Enter animal ID or name");

        // Try by ID first
        RescueAnimal animal = manager.getAnimalById(searchInput);

        // If not found, try by name
        if (animal == null) {
            List<RescueAnimal> matches = manager.findByName(searchInput);
            if (matches.isEmpty()) {
                System.out.println("No animal found with ID or name: " + searchInput);
                return;
            }
            if (matches.size() > 1) {
                System.out.println("Multiple animals found with that name:");
                for (int i = 0; i < matches.size(); i++) {
                    System.out.println("[" + (i + 1) + "]\n" + matches.get(i).toString());
                }

                int choice = Validation.readBoundedInt(scanner,
                        "Select which animal (1-" + matches.size() + ")", 1, matches.size(), "");
                animal = matches.get(choice - 1);
            } else {
                animal = matches.get(0);
            }
        }

        // Show current record
        System.out.println("\nCurrent Animal Record:");
        System.out.println("========================================");
        System.out.println(" Rescue Animal Record");
        System.out.println("========================================");
        System.out.println(animal);

        // Only allow if in service
        if (animal.getTrainingStatusEnum() != RescueAnimal.TrainingStatus.IN_SERVICE) {
            System.out.println("This animal is not in service and cannot be reserved.");
            return;
        }

        if (!animal.isReserved()) {
            // Reserve flow
            boolean confirm = Validation.readYesNo(scanner,
                    "Confirm reservation of this animal?");
            if (!confirm) {
                System.out.println("Reservation cancelled.");
                return;
            }

            manager.reserveAnimal(animal.getUniqueId());

            RescueAnimal updated = manager.getAnimalById(animal.getUniqueId());
            System.out.println("\nUpdated Animal Record:");
            System.out.println("========================================");
            System.out.println(" Rescue Animal Record");
            System.out.println("========================================");
            System.out.println(updated);

            System.out.println("Animal reserved successfully.");
        } else {
            // Unreserve flow
            boolean confirm = Validation.readYesNo(scanner,
                    "This animal is currently reserved. Release reservation?");
            if (!confirm) {
                System.out.println("Unreserve action cancelled.");
                return;
            }

            manager.unreserveAnimal(animal.getUniqueId());

            RescueAnimal updated = manager.getAnimalById(animal.getUniqueId());
            System.out.println("\nUpdated Animal Record:");
            System.out.println("========================================");
            System.out.println(" Rescue Animal Record");
            System.out.println("========================================");
            System.out.println(updated);

            System.out.println("Animal unreserved successfully.");
        }
    }

    // ===== Listing Logic =====
    private boolean printDogs() {
        System.out.println("\n--- List of All Dogs ---");
        List<RescueAnimal> dogs = manager.listByType("dog");
        if (dogs.isEmpty()) {
            System.out.println("No dogs in system.");
        } else {
            System.out.println("\n========================================");
            System.out.println(" Rescue Animal Record");
            System.out.println("========================================");
            dogs.stream()
                    .sorted((a, b) -> a.getUniqueId().compareTo(b.getUniqueId()))
                    .forEach(System.out::println);
        }
        return handleAfterPrint();
    }

    private boolean printMonkeys() {
        System.out.println("\n--- List of All Monkeys ---");
        List<RescueAnimal> monkeys = manager.listByType("monkey");
        if (monkeys.isEmpty()) {
            System.out.println("No monkeys in system.");
        } else {
            System.out.println("\n========================================");
            System.out.println(" Rescue Animal Record");
            System.out.println("========================================");
            monkeys.stream()
                    .sorted((a, b) -> a.getUniqueId().compareTo(b.getUniqueId()))
                    .forEach(System.out::println);
        }
        return handleAfterPrint();
    }

    private boolean printAvailableAnimals() {
        System.out.println("\n--- Available Animals (In Service, Not Reserved) ---");
        List<RescueAnimal> available = manager.listAvailableForService("dog");
        available.addAll(manager.listAvailableForService("monkey"));
        if (available.isEmpty()) {
            System.out.println("No animals currently in service and available for reservation.");
        } else {
            System.out.println("\n========================================");
            System.out.println(" Rescue Animal Record");
            System.out.println("========================================");
            available.stream()
                    .sorted((a, b) -> a.getUniqueId().compareTo(b.getUniqueId()))
                    .forEach(System.out::println);
        }
        return handleAvailableAfterPrint();
    }

    // ===== Training Update =====
    private void updateTrainingStatus() {
        System.out.println("\n--- Update Training Status ---");
        String searchInput = Validation.readNonEmpty(scanner, "Enter animal ID or name");
        RescueAnimal animal = manager.getAnimalById(searchInput);

        // If not found by ID, try by name
        if (animal == null) {
            List<RescueAnimal> matches = manager.findByName(searchInput);
            if (matches.isEmpty()) {
                System.out.println("No animal found with ID or name: " + searchInput);
                return;
            }
            if (matches.size() > 1) {
                System.out.println("Multiple animals found with that name:");
                for (int i = 0; i < matches.size(); i++) {
                    System.out.println("[" + (i + 1) + "]\n" + matches.get(i).toString());
                }

                int choice = Validation.readBoundedInt(scanner,
                        "Select which animal (1-" + matches.size() + ")", 1, matches.size(), "");
                animal = matches.get(choice - 1);
            } else {
                animal = matches.get(0);
            }
        }

        // Show current record
        System.out.println("\nCurrent Animal Record:");
        System.out.println("========================================");
        System.out.println(" Rescue Animal Record");
        System.out.println("========================================");
        System.out.println(animal);

        // Build menu depending on current status
        boolean canAdvance = animal.getTrainingStatusEnum() != RescueAnimal.TrainingStatus.IN_SERVICE
                && animal.getTrainingStatusEnum() != RescueAnimal.TrainingStatus.FARM;

        int option;
        if (canAdvance) {
            System.out.println("\nChoose an option:");
            System.out.println(" [1] Advance training one step");
            System.out.println(" [2] Manually set training status");
            option = Validation.readBoundedInt(scanner, "Select", 1, 2, "");
        } else {
            // Only manual allowed
            System.out.println("\nThis animal cannot be advanced (already in service or farm).");
            System.out.println(" [2] Manually set training status");
            option = 2; // force manual mode
        }

        try {
            if (option == 1) {
                // Advance training by one step
                manager.advanceTraining(animal.getUniqueId());
                System.out.println("\nTraining advanced one step.");
            } else if (option == 2) {
                // Manual update flow
                String newStatus = Validation.readTrainingStatus(scanner);

                boolean confirm = Validation.readYesNo(scanner,
                        "Confirm update of training status from " +
                                animal.getTrainingStatus() + " to " + newStatus + "?");

                if (!confirm) {
                    System.out.println("Training status update cancelled.");
                    return;
                }

                manager.updateTraining(animal.getUniqueId(), newStatus);
            }

            // Show updated record
            System.out.println("\nUpdated Animal Record:");
            System.out.println(manager.getAnimalById(animal.getUniqueId()));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void removeAnimal() {
        System.out.println("\n--- Remove Animal ---");
        String searchInput = Validation.readNonEmpty(scanner, "Enter animal ID or name");

        // Try by ID first
        RescueAnimal animal = manager.getAnimalById(searchInput);

        // If not found, try by name
        if (animal == null) {
            List<RescueAnimal> matches = manager.findByName(searchInput);
            if (matches.isEmpty()) {
                System.out.println("No animal found with ID or name: " + searchInput);
                return;
            }
            if (matches.size() > 1) {
                System.out.println("Multiple animals found with that name:");
                for (int i = 0; i < matches.size(); i++) {
                    System.out.println("[" + (i + 1) + "]\n" + matches.get(i).toString());
                }
                int choice = Validation.readBoundedInt(scanner,
                        "Select which animal (1-" + matches.size() + ")", 1, matches.size(), "");
                animal = matches.get(choice - 1);
            } else {
                animal = matches.get(0);
            }
        }

        // Show the record before deletion
        System.out.println("\nSelected Animal Record:");
        System.out.println("========================================");
        System.out.println(animal);

        // Confirm
        boolean confirm = Validation.readYesNo(scanner,
                "Are you sure you want to remove this animal?");
        if (!confirm) {
            System.out.println("Removal cancelled.");
            return;
        }

        // Remove
        boolean removed = manager.removeAnimal(animal.getUniqueId());
        if (removed) {
            System.out.println("Animal successfully removed.");
        } else {
            System.out.println("Failed to remove animal (not found).");
        }
    }


    // ====== Post-print navigation ======
    private boolean handleAfterPrint() {
        boolean stayRunning = true;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Return to main menu? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            switch (choice) {
                case "y" -> validInput = true; // go back to menu
                case "n" -> {
                    return false;
                } // exit program
                default -> System.out.println("Invalid choice. Enter 'y' or 'n'.");
            }
        }
        return stayRunning;
    }

    private boolean handleAvailableAfterPrint() {
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter 'main' to return to menu, 'reserve' to reserve an animal, or 'exit' to quit: ");
            String choice = scanner.nextLine().trim().toLowerCase();
            switch (choice) {
                case "main" -> {
                    return true;
                }
                case "reserve" -> {
                    reserveAnimal();
                    return true;
                }
                case "exit" -> {
                    return false;
                }
                default -> System.out.println("Invalid choice. Enter 'main', 'reserve', or 'exit'.");
            }
        }
        return true;
    }
}
