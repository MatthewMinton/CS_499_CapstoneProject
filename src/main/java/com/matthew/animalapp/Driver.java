
///***********************************************************************************************************************
/// Author: Matthew Minton                                                                                               *
/// Course: CS 499 Computer Science capstone project.                                                                    *
/// Purpose: This software is derived from project 2 of IT 145: Foundation in application development.                   *
///   My intent is to take the original code (some provided other sections created) and build upon them to               *
///   show the extent of my growth in software development during my time as a student at SNHU. This software uses a     *
///   command line interface that allows users to intake animals, in this case only monkeys and dogs currently, and save *
///   so they can be reserved for training to be used as service animals.                                                *
///                                                                                                                      *


package com.matthew.animalapp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
    /**
     * The main method that drives the rescue animal system application.
     * It presents a menu to the user, takes their input, and performs
     * actions based on the selected option within a loop.
     *
     */
    public static void main(String[] args) {
        // Create a single Scanner object for all user input.
        // It's a best practice to create it once and pass it around.
        Scanner input = new Scanner(System.in);
        String userChoice = "";
        boolean continueMenu = true;

        // Main application loop. The program runs as long as continueMenu is true.
        while (continueMenu) {
            char option;
            boolean validOption;

            // Loop to handle menu selection and re-prompt for invalid input.
            do {
                IntakeMenu.displayMainMenu();
                try {
                    // Get the user's choice and consume the rest of the line.
                    option = input.next().toLowerCase().charAt(0);
                    input.nextLine();
                    validOption = true; // Assume valid until proven otherwise.

                    // Check if the option is one of the valid choices.
                    switch (option) {
                        case '1':
                            AnimalManager.intakeNewDog(input);
                            break;
                        case '2':
                            AnimalManager.intakeNewMonkey(input);
                            break;
                        case '3':
                            AnimalManager.reserveAnimal(input);
                            break;
                        case '4':
                            AnimalManager.printAnimals(option);
                            break;
                        case '5':
                            AnimalManager.printAnimals(option);
                            break;
                        case '6':
                            AnimalManager.printAnimals(option);
                            break;
                        case 'q':
                            IntakeMenu.displayExitMessage();
                            continueMenu = false; // Set flag to exit main loop
                            break;
                        default:
                            validOption = false; // Invalid option, loop again.
                            IntakeMenu.displayInvalidOption();
                            break;
                    }
                } catch (InputMismatchException e) {
                    // Catch cases where the user enters non-character input
                    validOption = false;
                    IntakeMenu.displayInvalidOption();
                    input.nextLine(); // Clear the buffer after an error
                    option = '\0'; // Set to a default value to satisfy the loop condition
                }
            } while (!validOption); // Continue this inner loop until a valid option is entered.

            // After a task is performed (unless the user quit), ask to return to menu.
            if (continueMenu) {
                System.out.print("Return to main menu? (y/n): ");
                String restart = input.nextLine();
                if (!restart.equalsIgnoreCase("y")) {
                    IntakeMenu.displayExitMessage();
                    continueMenu = false;
                }
            }
        }

        // Close the scanner resource when the program exits.
        input.close();
    }
}