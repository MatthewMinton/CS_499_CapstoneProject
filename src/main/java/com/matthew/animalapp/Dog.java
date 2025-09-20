package com.matthew.animalapp;

/**
 * Dog class extends RescueAnimal with the breed attribute.
 */
public class Dog extends RescueAnimal {
    private String breed;

    public Dog(String name, String breed, String gender, String age, String weight, String acquisitionDate,
               String acquisitionCountry, String trainingStatus, boolean reserved, String inServiceCountry) {
        this.setName(name);
        this.setBreed(breed);
        this.setGender(gender);
        this.setAge(age);
        this.setWeight(weight);
        this.setAcquisitionDate(acquisitionDate);
        this.setAcquisitionLocation(acquisitionCountry);
        this.setTrainingStatus(trainingStatus);
        this.setReserved(reserved);
        this.setInServiceCountry(inServiceCountry);
        this.setAnimalType("dog");
    }

    public String getBreed() { return this.breed; }
    public void setBreed(String dogBreed) { this.breed = dogBreed; }

    @Override
    public String toString() {
        return "Dog{name='" + getName() + "', breed='" + breed + "', age='" + getAge() +
                "', reserved=" + getReserved() + ", inServiceCountry='" + getInServiceLocation() + "'}";
    }
}
