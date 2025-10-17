public class Dog extends RescueAnimal {
    private String breed;

    public Dog(String name, String breed, String gender, String age, String weight, String acquisitionDate, String acquisitionCountry, String trainingStatus, boolean reserved, String inServiceCountry) {
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
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String dogBreed) {
        this.breed = dogBreed;
    }
}