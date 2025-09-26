package com.matthew.animalapp;

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
