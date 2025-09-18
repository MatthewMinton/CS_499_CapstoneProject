package com.matthew.animalapp;

/**
 * IntakeMenu centralizes all menu-related output.
 * Separating UI text from business logic keeps the system easier to maintain.
 */
public class IntakeMenu {

    public static void displayMainMenu() {
        System.out.println("\n\n");
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║                                                ║");
        System.out.println("║     🐾 Welcome to the Rescue Animal System 🐾 ║");
        System.out.println("║                                                ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║ [1] Intake a new dog                           ║");
        System.out.println("║------------------------------------------------║");
        System.out.println("║ [2] Intake a new monkey                        ║");
        System.out.println("║------------------------------------------------║");
        System.out.println("║ [3] Reserve an animal                          ║");
        System.out.println("║------------------------------------------------║");
        System.out.println("║ [4] Print a list of all dogs                   ║");
        System.out.println("║------------------------------------------------║");
        System.out.println("║ [5] Print a list of all monkeys                ║");
        System.out.println("║------------------------------------------------║");
        System.out.println("║ [6] Print a list of all animals not reserved   ║");
        System.out.println("║------------------------------------------------║");
        System.out.println("║ [q] Quit application                           ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.print("Enter a menu selection: ");
    }



    public static void displayInvalidOption() {
        System.out.println("Invalid option. Please enter a number 1–6 or 'q' to quit.");
    }

    public static void displayExitMessage() {
        System.out.println("You have exited the application.");
    }

    /**
     * Reminder shown at the start of an intake process.
     * This gives users a clear escape option so they aren’t forced
     * to complete intake if they change their mind mid-process.
     */
    public static void displayCancelMessage() {
        System.out.println("Type 'cancel' at any prompt to exit intake without saving.");
    }
}
