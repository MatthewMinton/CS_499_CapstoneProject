public class RescueAnimal {
	private String name;
	private String animalType;
	private String gender;
	private String age;
	private String weight;
	private String acquisitionDate;
	private String acquisitionCountry;
	private String trainingStatus;
	private boolean reserved;
	private String inServiceCountry;

	public RescueAnimal() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnimalType() {
		return this.animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAcquisitionDate() {
		return this.acquisitionDate;
	}

	public void setAcquisitionDate(String acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}

	public String getAcquisitionLocation() {
		return this.acquisitionCountry;
	}

	public void setAcquisitionLocation(String acquisitionCountry) {
		this.acquisitionCountry = acquisitionCountry;
	}

	public boolean getReserved() {
		return this.reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public String getInServiceLocation() {
		return this.inServiceCountry;
	}

	public void setInServiceCountry(String inServiceCountry) {
		this.inServiceCountry = inServiceCountry;
	}

	public String getTrainingStatus() {
		return this.trainingStatus;
	}

	public void setTrainingStatus(String trainingStatus) {
		this.trainingStatus = trainingStatus;
	}
}