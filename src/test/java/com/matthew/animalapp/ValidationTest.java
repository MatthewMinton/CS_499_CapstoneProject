package com.matthew.animalapp;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @Test
    void testReadYesNoYes() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("y\n".getBytes()));
        assertTrue(Validation.readYesNo(scanner, "Confirm?"));
    }

    @Test
    void testReadYesNoNo() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("n\n".getBytes()));
        assertFalse(Validation.readYesNo(scanner, "Confirm?"));
    }

    @Test
    void testCancelThrows() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("cancel\n".getBytes()));
        assertThrows(Validation.CancelException.class,
                () -> Validation.readNonEmpty(scanner, "Enter name"));
    }

    @Test
    void testReadBreedValid() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("Labrador Retriever\n".getBytes()));
        assertEquals("Labrador Retriever", Validation.readBreed(scanner));
    }

    @Test
    void testReadTrainingStatusValid() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("phase i\n".getBytes()));
        String status = Validation.readTrainingStatus(scanner);
        assertEquals("Phase I", status);
    }
}
