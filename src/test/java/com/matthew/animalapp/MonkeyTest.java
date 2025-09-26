package com.matthew.animalapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonkeyTest {

    @Test
    void testMeasurements() {
        Monkey m = new Monkey("George", "Capuchin", "male", "5", "30",
                "01/01/2020", "United States", "intake", false, null,
                12.5, 24.0, 18.0);
        assertEquals("Capuchin", m.getSpecies());
        assertEquals(12.5, m.getTailLength());
        assertEquals(24.0, m.getHeight());
        assertEquals(18.0, m.getBodyLength());
    }

    @Test
    void testToStringContainsMeasurements() {
        Monkey m = new Monkey("George", "Capuchin", "male", "5", "30",
                "01/01/2020", "United States", "intake", false, null,
                12.5, 24.0, 18.0);
        String out = m.toString();
        assertTrue(out.contains("Tail Length"));
        assertTrue(out.contains("Height"));
        assertTrue(out.contains("Body Length"));
    }
}
