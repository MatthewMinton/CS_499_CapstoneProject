package com.matthew.animalapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalManagerTest {
    AnimalManager manager;
    Dog dog;

    @BeforeEach
    void setup() {
        manager = new AnimalManager();
        dog = new Dog("Rex", "Labrador", "male", "3", "50",
                "01/01/2022", "United States", "in service", false, "USA");
        manager.addAnimal(dog);
    }

    @Test
    void testAddAndGetAnimal() {
        RescueAnimal found = manager.getAnimalById(dog.getUniqueId());
        assertNotNull(found);
        assertEquals("Rex", found.getName());
    }

    @Test
    void testRemoveAnimal() {
        assertTrue(manager.removeAnimal(dog.getUniqueId()));
        assertNull(manager.getAnimalById(dog.getUniqueId()));
    }

    @Test
    void testReserveAndUnreserveAnimal() {
        manager.reserveAnimal(dog.getUniqueId());
        assertTrue(manager.getAnimalById(dog.getUniqueId()).isReserved());
        manager.unreserveAnimal(dog.getUniqueId());
        assertFalse(manager.getAnimalById(dog.getUniqueId()).isReserved());
    }

    @Test
    void testListByType() {
        List<RescueAnimal> dogs = manager.listByType("dog");
        assertEquals(1, dogs.size());
        assertEquals("Rex", dogs.get(0).getName());
    }

    @Test
    void testListAvailableForService() {
        List<RescueAnimal> available = manager.listAvailableForService("dog");
        assertEquals(1, available.size());
    }
}
