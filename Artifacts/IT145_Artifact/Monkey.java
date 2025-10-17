public class Monkey extends RescueAnimal {
    private String species;
    private String tailLength;
    private String height;
    private String bodyLength;

    public Monkey(String name, String gender, String age, String weight, String acquisitionDate, String acquisitionCountry, String trainingStatus, boolean reserved, String inServiceCountry, String species, String tailLength, String height, String bodyLength) {
        this.setName(name);
        this.setGender(gender);
        this.setAge(age);
        this.setWeight(weight);
        this.setAcquisitionDate(acquisitionDate);
        this.setAcquisitionLocation(acquisitionCountry);
        this.setTrainingStatus(trainingStatus);
        this.setReserved(reserved);
        this.setInServiceCountry(inServiceCountry);
        this.setSpecies(species);
        this.setTailLength(tailLength);
        this.setHeight(height);
        this.setBodyLength(bodyLength);
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getTailLength() {
        return this.tailLength;
    }

    public void setTailLength(String tailLength) {
        this.tailLength = tailLength;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBodyLength() {
        return this.bodyLength;
    }

    public void setBodyLength(String bodyLength) {
        this.bodyLength = bodyLength;
    }
}