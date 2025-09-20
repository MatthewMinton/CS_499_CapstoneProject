package com.matthew.animalapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MonkeyTest {

    @Test
    void testConstructorAndGetters() {
        Monkey monkey = new Monkey("George", "male", "3", "15.2",
                "02/02/2021", "Colombia", "Phase I", true, "United States",
                "Capuchin", "12.5", "20", "18");

        assertEquals("George", monkey.getName());
        assertEquals("male", monkey.getGender());
        assertEquals("3", monkey.getAge());
        assertEquals("15.2", monkey.getWeight());
        assertEquals("Colombia", monkey.getAcquisitionLocation());
        assertTrue(monkey.getReserved());
        assertEquals("Capuchin", monkey.getSpecies());
        assertEquals("12.5", monkey.getTailLength());
        assertEquals("20", monkey.getHeight());
        assertEquals("18", monkey.getBodyLength());
    }

    @Test
    void testToStringContainsKeyFields() {
        Monkey monkey = new Monkey("George", "male", "3", "15.2",
                "02/02/2021", "Colombia", "Phase I", true, "United States",
                "Capuchin", "12.5", "20", "18");

        String output = monkey.toString();
        assertTrue(output.contains("George"));
        assertTrue(output.contains("Capuchin"));
        assertTrue(output.contains("reserved=true"));
    }
}
