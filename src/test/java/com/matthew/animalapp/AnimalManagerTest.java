package com.matthew.animalapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AnimalManagerTest {

    @BeforeEach
    void resetAnimalLists() {
        // Reset lists to a clean state for each test
        // Reflection could also be used, but here we just directly re-init.
        try {
            var dogListField = AnimalManager.class.getDeclaredField("dogList");
            var monkeyListField = AnimalManager.class.getDeclaredField("monkeyList");
            dogListField.setAccessible(true);
            monkeyListField.setAccessible(true);
            ((java.util.List<?>) dogListField.get(null)).clear();
            ((java.util.List<?>) monkeyListField.get(null)).clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testIntakeNewDogAddsDog() {
        String input = "Buddy\nBeagle\nmale\n5\n20.5\n01/01/2020\nUnited States\nintake\nfalse\nUnited States\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        AnimalManager.intakeNewDog(scanner);

        // Verify dog was added
        try {
            var dogListField = AnimalManager.class.getDeclaredField("dogList");
            dogListField.setAccessible(true);
            var dogList = (java.util.List<Dog>) dogListField.get(null);

            assertEquals(1, dogList.size());
            assertEquals("Buddy", dogList.get(0).getName());
        } catch (Exception e) {
            fail("Failed to access dogList");
        }
    }

    @Test
    void testReserveDogByCountry() {
        Dog dog = new Dog("Buddy", "Beagle", "male", "5", "20.5",
                "01/01/2020", "United States", "intake", false, "United States");

        try {
            var dogListField = AnimalManager.class.getDeclaredField("dogList");
            dogListField.setAccessible(true);
            var dogList = (java.util.List<Dog>) dogListField.get(null);
            dogList.add(dog);
        } catch (Exception e) {
            fail("Failed to seed dogList");
        }

        String input = "Dog\nUnited States\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        AnimalManager.reserveAnimal(scanner);

        assertTrue(dog.getReserved());
    }
}
