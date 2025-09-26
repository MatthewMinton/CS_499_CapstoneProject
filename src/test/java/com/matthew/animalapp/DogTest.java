package com.matthew.animalapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    @Test
    void testBreedGetterSetter() {
        Dog d = new Dog("Rex", "German Shepherd", "male", "3", "55",
                "01/01/2022", "United States", "intake", false, null);
        assertEquals("German Shepherd", d.getBreed());
        d.setBreed("Labrador");
        assertEquals("Labrador", d.getBreed());
    }

    @Test
    void testToStringContainsBreed() {
        Dog d = new Dog("Rex", "German Shepherd", "male", "3", "55",
                "01/01/2022", "United States", "intake", false, null);
        String out = d.toString();
        assertTrue(out.contains("Breed: German Shepherd"));
    }
}
