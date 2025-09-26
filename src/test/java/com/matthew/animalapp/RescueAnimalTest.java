package com.matthew.animalapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RescueAnimalTest {

    @Test
    void testAdvanceTrainingOneStep() {
        Dog d = new Dog("Rex", "Labrador", "male", "3", "50",
                "01/01/2022", "United States", "intake", false, null);
        d.advanceTrainingOneStep();
        assertEquals("Phase I", d.getTrainingStatus());
    }

    @Test
    void testInvalidTrainingJumpThrows() {
        Dog d = new Dog("Rex", "Labrador", "male", "3", "50",
                "01/01/2022", "United States", "Phase I", false, null);
        assertThrows(IllegalArgumentException.class,
                () -> d.setTrainingStatus("Phase III"));
    }

    @Test
    void testReserveOnlyWhenInService() {
        Dog d = new Dog("Max", "Labrador", "male", "4", "55",
                "01/01/2021", "United States", "in service", false, "USA");
        assertDoesNotThrow(d::reserve);
    }

    @Test
    void testReserveFailsWhenNotInService() {
        Dog d = new Dog("Buddy", "Labrador", "male", "2", "45",
                "01/01/2023", "United States", "intake", false, null);
        assertThrows(IllegalStateException.class, d::reserve);
    }

    @Test
    void testEqualityAndHashCode() {
        Dog d1 = new Dog("Rex", "Labrador", "male", "3", "50",
                "01/01/2022", "United States", "intake", false, null);
        Dog d2 = new Dog("Rex", "Labrador", "male", "3", "50",
                "01/01/2022", "United States", "intake", false, null);

        assertNotEquals(d1, d2); // IDs are unique
        assertNotEquals(d1.hashCode(), d2.hashCode());
    }
}
