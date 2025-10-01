package com.matthew.animalapp;

///***********************************************************************************************************************
/// Author: Matthew Minton
/// Course: CS 499 Computer Science Capstone Project
/// Purpose: This software is derived from project 2 of IT 145: Foundation in Application Development.
///   My intent is to take the original code (some provided, other sections created) and build upon them to
///   show the extent of my growth in software development during my time as a student at SNHU. This software uses a
///   command line interface that allows users to intake animals (monkeys and dogs) and save them so they can
///   be reserved for training and later deployed as service animals.
///***********************************************************************************************************************

import java.util.Scanner;

public class Driver {
    private final Scanner scanner = new Scanner(System.in);
    private final AnimalManager manager = new AnimalManager();
    private final MenuController menuController = new MenuController(scanner, manager);

    public static void main(String[] args) {
        new Driver().run();
    }

    /** Main application loop */
    public void run() {
        boolean running = true;
        while (running) {
            running = menuController.showMainMenu();
        }
        System.out.println("\n****************************************");
        System.out.println("*    Exiting Grazioso Salvare Manager   *");
        System.out.println("****************************************");
    }
}
