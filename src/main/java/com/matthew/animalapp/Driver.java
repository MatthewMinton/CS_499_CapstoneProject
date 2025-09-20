///***********************************************************************************************************************
/// Author: Matthew Minton
/// Course: CS 499 Computer Science Capstone Project
/// Purpose: This software is derived from project 2 of IT 145: Foundation in Application Development.
///   My intent is to take the original code (some provided, other sections created) and build upon them to
///   show the extent of my growth in software development during my time as a student at SNHU. This software uses a
///   command line interface that allows users to intake animals (monkeys and dogs) and save them so they can
///   be reserved for training and later deployed as service animals.
///***********************************************************************************************************************

package com.matthew.animalapp;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Driver acts as the controller for the Rescue Animal application.
 * Its responsibility is limited to menu navigation and delegating work
 * to supporting classes such as AnimalManager (business logic) and
 * IntakeMenu (user-facing messages).
 *
 * By keeping Driver lightweight, business logic remains modular and testable.
 */
public class Driver {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean continueMenu = true;

        // Main application loop. Runs until the user chooses to quit.
        while (continueMenu) {
            char option;
            boolean validOption;

            // Menu validation loop
            do {
                IntakeMenu.displayMainMenu();
                try {
                    option = input.next().toLowerCase().charAt(0);
                    input.nextLine(); // consume newline
                    validOption = true;

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
                        case '4': case '5': case '6':
                            AnimalManager.printAnimals(option);
                            break;
                        case 'q':
                            IntakeMenu.displayExitMessage();
                            continueMenu = false;
                            break;
                        default:
                            validOption = false;
                            IntakeMenu.displayInvalidOption();
                            break;
                    }
                } catch (InputMismatchException e) {
                    validOption = false;
                    IntakeMenu.displayInvalidOption();
                    input.nextLine(); // clear bad input
                    option = '\0';
                }
            } while (!validOption);

            // Restart prompt with strict validation
            if (continueMenu) {
                String restart;
                do {
                    System.out.print("Return to main menu? (y/n): ");
                    restart = input.nextLine().trim();
                } while (!restart.equalsIgnoreCase("y") && !restart.equalsIgnoreCase("n"));

                if (restart.equalsIgnoreCase("n")) {
                    IntakeMenu.displayExitMessage();
                    continueMenu = false;
                }
            }
        }

        input.close(); // close scanner at exit
    }
}
