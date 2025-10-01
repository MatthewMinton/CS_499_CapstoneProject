package com.matthew.animalapp;

/**
 * Monkey is a specific type of RescueAnimal.
 * Adds species and body measurements.
 */
public class Monkey extends RescueAnimal {

    private String species;
    private double tailLength;
    private double height;
    private double bodyLength;

    public Monkey(String name,
                  String species,
                  String gender,
                  String age,
                  String weight,
                  String acquisitionDate,
                  String acquisitionCountry,
                  String trainingStatus,
                  boolean reserved,
                  String inServiceCountry,
                  double tailLength,
                  double height,
                  double bodyLength) {
        super(name, gender, age, weight, acquisitionDate, acquisitionCountry,
                trainingStatus, reserved, inServiceCountry);
        this.species = species;
        this.tailLength = tailLength;
        this.height = height;
        this.bodyLength = bodyLength;
    }

    // New constructor (preserves ID from DB)
    public Monkey(String id,
                  String name,
                  String species,
                  String gender,
                  String age,
                  String weight,
                  String acquisitionDate,
                  String acquisitionCountry,
                  String trainingStatus,
                  boolean reserved,
                  String inServiceCountry,
                  double tailLength,
                  double height,
                  double bodyLength) {
        super(id, name, gender, age, weight, acquisitionDate, acquisitionCountry,
                trainingStatus, reserved, inServiceCountry);
        this.species = species;
        this.tailLength = tailLength;
        this.height = height;
        this.bodyLength = bodyLength;
    }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public double getTailLength() { return tailLength; }
    public void setTailLength(double tailLength) { this.tailLength = tailLength; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public double getBodyLength() { return bodyLength; }
    public void setBodyLength(double bodyLength) { this.bodyLength = bodyLength; }

    @Override
    public String toString() {
        return super.toString() +
                "Species: " + species + "\n" +
                "Age: " + getAge() + " years\n" +
                "Weight: " + getWeight() + " lbs\n" +
                "Tail Length: " + tailLength + " inches\n" +
                "Height: " + height + " inches\n" +
                "Body Length: " + bodyLength + " inches\n";
    }
}
