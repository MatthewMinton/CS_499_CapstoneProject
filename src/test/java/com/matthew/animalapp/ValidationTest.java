package com.matthew.animalapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    // ---------- NAME & GENDER ----------
    @Test
    void testValidateName() {
        assertTrue(Validation.validateName("Bella"));
        assertTrue(Validation.validateName("Mr Spot"));
        assertFalse(Validation.validateName("123Bella"));
        assertFalse(Validation.validateName("")); // empty
        assertFalse(Validation.validateName(null)); // null
    }

    @Test
    void testValidateGender() {
        assertTrue(Validation.validateGender("male"));
        assertTrue(Validation.validateGender("FEMALE")); // case-insensitive
        assertFalse(Validation.validateGender("other"));
        assertFalse(Validation.validateGender(""));
    }

    // ---------- AGE & WEIGHT ----------
    @Test
    void testValidateAge() {
        assertTrue(Validation.validateAge("0"));
        assertTrue(Validation.validateAge("25"));
        assertTrue(Validation.validateAge("50"));
        assertFalse(Validation.validateAge("-1"));
        assertFalse(Validation.validateAge("100"));
        assertFalse(Validation.validateAge("abc"));
    }

    @Test
    void testValidateWeight() {
        assertTrue(Validation.validateWeight("0.1"));
        assertTrue(Validation.validateWeight("150"));
        assertTrue(Validation.validateWeight("300"));
        assertFalse(Validation.validateWeight("0"));
        assertFalse(Validation.validateWeight("301"));
        assertFalse(Validation.validateWeight("abc"));
    }

    // ---------- DATE ----------
    @Test
    void testValidateDate() {
        assertTrue(Validation.validateDate("05/12/2019"));
        assertFalse(Validation.validateDate("2019/05/12")); // wrong format
        assertFalse(Validation.validateDate("13/01/2020")); // invalid month
        assertFalse(Validation.validateDate("02/30/2020")); // invalid day

    }

    // ---------- TRAINING STATUS ----------
    @Test
    void testValidateTrainingStatus() {
        assertTrue(Validation.validateTrainingStatus("intake"));
        assertTrue(Validation.validateTrainingStatus("Phase II"));
        assertTrue(Validation.validateTrainingStatus("in service"));
        assertFalse(Validation.validateTrainingStatus("graduated"));
    }

    // ---------- BREED ----------
    @Test
    void testValidateBreed() {
        assertTrue(Validation.validateBreed("German Shepherd"));
        assertTrue(Validation.validateBreed("poodle")); // case-insensitive
        assertFalse(Validation.validateBreed("Wolfdog"));
        assertFalse(Validation.validateBreed(""));
    }

    // ---------- SPECIES ----------
    @Test
    void testValidateSpecies() {
        assertTrue(Validation.validateSpecies("Capuchin"));
        assertTrue(Validation.validateSpecies("macaque")); // case-insensitive
        assertFalse(Validation.validateSpecies("Dog"));
        assertFalse(Validation.validateSpecies(""));
    }

    // ---------- MEASUREMENTS ----------
    @Test
    void testValidateTailLength() {
        assertTrue(Validation.validateTailLength("1"));
        assertTrue(Validation.validateTailLength("25"));
        assertFalse(Validation.validateTailLength("0"));
        assertFalse(Validation.validateTailLength("30"));
        assertFalse(Validation.validateTailLength("abc"));
    }

    @Test
    void testValidateHeight() {
        assertTrue(Validation.validateHeight("1"));
        assertTrue(Validation.validateHeight("40"));
        assertFalse(Validation.validateHeight("0"));
        assertFalse(Validation.validateHeight("41"));
        assertFalse(Validation.validateHeight("abc"));
    }

    @Test
    void testValidateBodyLength() {
        assertTrue(Validation.validateBodyLength("1"));
        assertTrue(Validation.validateBodyLength("40"));
        assertFalse(Validation.validateBodyLength("0"));
        assertFalse(Validation.validateBodyLength("41"));
        assertFalse(Validation.validateBodyLength("abc"));
    }

    // ---------- GENERAL PURPOSE ----------
    @Test
    void testValidatePositiveNumber() {
        assertTrue(Validation.validatePositiveNumber("10"));
        assertTrue(Validation.validatePositiveNumber("0.1"));
        assertFalse(Validation.validatePositiveNumber("0"));
        assertFalse(Validation.validatePositiveNumber("-5"));
        assertFalse(Validation.validatePositiveNumber("abc"));
    }

    // ---------- RESERVE ANIMAL ----------
    @Test
    void testValidateReserveAnimal() {
        assertTrue(Validation.validateReserveAnimal("dog"));
        assertTrue(Validation.validateReserveAnimal("Monkey"));
        assertFalse(Validation.validateReserveAnimal("cat"));
        assertFalse(Validation.validateReserveAnimal(""));
    }

    // ---------- COUNTRY ----------
    @Test
    void testValidateCountry() {
        assertTrue(Validation.validateCountry("United States"));
        assertTrue(Validation.validateCountry("canada")); // case-insensitive
        assertFalse(Validation.validateCountry("Brazil"));
        assertFalse(Validation.validateCountry(""));
    }
}
