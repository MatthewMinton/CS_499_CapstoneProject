package com.matthew.animalapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    @Test
    void testConstructorAndGetters() {
        Dog dog = new Dog("Buddy", "Beagle", "male", "5", "20.5",
                "01/01/2020", "United States", "intake", false, "United States");

        assertEquals("Buddy", dog.getName());
        assertEquals("Beagle", dog.getBreed());
        assertEquals("male", dog.getGender());
        assertEquals("5", dog.getAge());
        assertEquals("20.5", dog.getWeight());
        assertEquals("01/01/2020", dog.getAcquisitionDate());
        assertEquals("United States", dog.getAcquisitionLocation());
        assertEquals("intake", dog.getTrainingStatus());
        assertFalse(dog.getReserved());
        assertEquals("United States", dog.getInServiceLocation());
    }

    @Test
    void testToStringContainsKeyFields() {
        Dog dog = new Dog("Buddy", "Beagle", "male", "5", "20.5",
                "01/01/2020", "United States", "intake", false, "United States");

        String output = dog.toString();
        assertTrue(output.contains("Buddy"));
        assertTrue(output.contains("Beagle"));
        assertTrue(output.contains("reserved=false"));
    }
}
