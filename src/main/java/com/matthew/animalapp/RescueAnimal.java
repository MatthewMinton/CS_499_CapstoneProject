package com.matthew.animalapp;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Optional;
import java.util.Objects;

/**
 * Abstract base class that represents any rescue animal (dog, monkey, etc.).
 * Common fields like name, gender, age, training status, etc. live here.
 * Other animals like Dog/Monkey extend this.
 */
public abstract class RescueAnimal {

    // ===== Training states =====
    public enum TrainingStatus {
        INTAKE,
        PHASE_I,
        PHASE_II,
        PHASE_III,
        PHASE_IV,
        PHASE_V,
        IN_SERVICE,
        FARM; // terminal (cannot change once here)

        /**
         * Moves training forward one step if possible.
         * Ex: INTAKE -> PHASE_I, PHASE_I -> PHASE_II, etc.
         * Returns empty if the animal can't go forward anymore.
         */
        public Optional<TrainingStatus> next() {
            return switch (this) {
                case INTAKE -> Optional.of(PHASE_I);
                case PHASE_I -> Optional.of(PHASE_II);
                case PHASE_II -> Optional.of(PHASE_III);
                case PHASE_III -> Optional.of(PHASE_IV);
                case PHASE_IV -> Optional.of(PHASE_V);
                case PHASE_V -> Optional.of(IN_SERVICE);
                default -> Optional.empty();
            };
        }

        /**
         * Parses user input into a training status.
         * Accepts different ways of writing it like "phase i" or "phase 1".
         * Makes it backward compatible with old assignments too.
         */
        public static TrainingStatus parse(String raw) {
            if (raw == null) throw new IllegalArgumentException("training status is required");
            String s = raw.trim().toLowerCase();
            return switch (s) {
                case "intake" -> INTAKE;
                case "phase i", "phase 1" -> PHASE_I;
                case "phase ii", "phase 2" -> PHASE_II;
                case "phase iii", "phase 3" -> PHASE_III;
                case "phase iv", "phase 4" -> PHASE_IV;
                case "phase v", "phase 5" -> PHASE_V;
                case "in service", "in-service", "in_service" -> IN_SERVICE;
                case "farm" -> FARM;
                default -> throw new IllegalArgumentException("Unknown training status: " + raw);
            };
        }

        /** String value for menus (keeps user's original phrasing). */
        public String menuLabel() {
            return switch (this) {
                case INTAKE -> "intake";
                case PHASE_I -> "Phase I";
                case PHASE_II -> "Phase II";
                case PHASE_III -> "Phase III";
                case PHASE_IV -> "Phase IV";
                case PHASE_V -> "Phase V";
                case IN_SERVICE -> "in service";
                case FARM -> "farm";
            };
        }
    }

    // ===== Identity generator =====
    private static final AtomicInteger ID_COUNTER = new AtomicInteger(1);

    private final String uniqueId; // immutable identity

    // ===== Common attributes (use same names/getters/setters for all animals) =====
    private String name;
    private String gender;
    private String age;
    private String weight;
    private String acquisitionDate;     // kept as String to preserve user's existing method signatures
    private String acquisitionCountry;
    private TrainingStatus trainingStatus = TrainingStatus.INTAKE;
    private boolean reserved = false;
    private String inServiceCountry;    // only meaningful when IN_SERVICE

    // ===== Constructors =====
    protected RescueAnimal() {
        this.uniqueId = "RA-" + ID_COUNTER.getAndIncrement();
    }

    protected RescueAnimal(String name,
                           String gender,
                           String age,
                           String weight,
                           String acquisitionDate,
                           String acquisitionCountry,
                           String trainingStatus,
                           boolean reserved,
                           String inServiceCountry) {
        this.uniqueId = "RA-" + ID_COUNTER.getAndIncrement();
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.acquisitionDate = acquisitionDate;
        this.acquisitionCountry = acquisitionCountry;
        // set status via parser to keep legacy compatibility
        this.trainingStatus = TrainingStatus.parse(trainingStatus);
        this.reserved = reserved;
        this.inServiceCountry = inServiceCountry;
        // Normalize: if not IN_SERVICE, force not reserved and clear inServiceCountry
        normalizeServiceFields();
    }
    /**
     * Keeps service-related fields consistent.
     * If an animal isn’t in service, it can’t be reserved and has no service country.
     */
    private void normalizeServiceFields() {
        if (this.trainingStatus != TrainingStatus.IN_SERVICE) {
            this.reserved = false;
            this.inServiceCountry = null;
        }
    }

    // ===== Reservation rules =====
    /** Return true only if the animal is eligible to be reserved (must be IN_SERVICE). */
    public boolean canBeReserved() {
        return this.trainingStatus == TrainingStatus.IN_SERVICE;
    }

    /** Reserve the animal if eligible; otherwise throws error. */
    public void reserve() {
        if (!canBeReserved()) {
            throw new IllegalStateException("Animal must be in service to be reserved.");
        }
        this.reserved = true;
    }

    /** Unreserve the animal (allowed only if in service). */
    public void unreserve() {
        if (this.trainingStatus != TrainingStatus.IN_SERVICE) {
            // Keep behavior strict to avoid inconsistent state
            throw new IllegalStateException("Only in-service animals can change reservation state.");
        }
        this.reserved = false;
    }

    // ===== Training progression =====
    /**
     * Enforces training rules:
     * - Can't change if already FARM
     * - Only move forward one step at a time
     * - FARM can be set from anywhere and then locks status forever
     */
    public void setTrainingStatus(String newStatusRaw) {
        setTrainingStatus(TrainingStatus.parse(newStatusRaw));
    }

    public void setTrainingStatus(TrainingStatus newStatus) {
        Objects.requireNonNull(newStatus, "new training status");
        if (this.trainingStatus == TrainingStatus.FARM) {
            throw new IllegalStateException("Training status is locked to 'farm' and cannot change.");
        }

        if (newStatus == TrainingStatus.FARM) {
            // terminal move
            this.trainingStatus = TrainingStatus.FARM;
            this.reserved = false;
            this.inServiceCountry = null;
            return;
        }

        if (newStatus == this.trainingStatus) {
            return; // no-op
        }

        // Allow only single-step forward progression
        Optional<TrainingStatus> next = this.trainingStatus.next();
        if (next.isPresent() && next.get() == newStatus) {
            this.trainingStatus = newStatus;
        } else {
            throw new IllegalArgumentException(
                    "Invalid training transition: " + this.trainingStatus.menuLabel() +
                            " -> " + newStatus.menuLabel() + ". Animals can only advance one phase at a time.");
        }

        // Normalize service fields based on new status
        normalizeServiceFields();
    }

    /** Shortcut: automatically moves animal forward one training step. */
    public void advanceTrainingOneStep() {
        if (this.trainingStatus == TrainingStatus.FARM) {
            throw new IllegalStateException("Cannot advance training after farm status.");
        }
        Optional<TrainingStatus> next = this.trainingStatus.next();
        if (next.isEmpty()) {
            throw new IllegalStateException("No further training steps from: " + this.trainingStatus.menuLabel());
        }
        setTrainingStatus(next.get());
    }

    /** Mark animal as farm (terminal). */
    public void setToFarm() {
        setTrainingStatus(TrainingStatus.FARM);
    }

    // ===== In-service country rules =====
    /**
     * Set in-service country. Only allowed when trainingStatus == IN_SERVICE.
     * If null/blank is provided, clears the field (still requires IN_SERVICE).
     * Country validation (allowed list) should be handled in Validation class.
     */
    public void setInServiceCountry(String country) {
        if (this.trainingStatus != TrainingStatus.IN_SERVICE) {
            throw new IllegalStateException("In-service country can be set only when status is 'in service'.");
        }
        this.inServiceCountry = (country == null || country.isBlank()) ? null : country.trim();
    }

    // ===== Getters/setters =====

    public String getUniqueId() { return uniqueId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getWeight() { return weight; }
    public void setWeight(String weight) { this.weight = weight; }

    public String getAcquisitionDate() { return acquisitionDate; }
    public void setAcquisitionDate(String acquisitionDate) { this.acquisitionDate = acquisitionDate; }

    public String getAcquisitionCountry() { return acquisitionCountry; }
    public void setAcquisitionCountry(String acquisitionCountry) { this.acquisitionCountry = acquisitionCountry; }

    public String getTrainingStatus() { return trainingStatus.menuLabel(); }
    public TrainingStatus getTrainingStatusEnum() { return trainingStatus; }

    public boolean isReserved() { return reserved; }
    public void setReserved(boolean reserved) {
        if (reserved) {
            // reserving = must be IN_SERVICE
            if (!canBeReserved()) {
                throw new IllegalStateException("Animal must be in service to be reserved.");
            }
            this.reserved = true;
        } else {
            // unreserve allowed only if in-service
            if (this.trainingStatus != TrainingStatus.IN_SERVICE) {
                throw new IllegalStateException("Only in-service animals can be unreserved.");
            }
            this.reserved = false;
        }
    }

    public String getInServiceCountry() { return inServiceCountry; }

    // ===== Equality & string representation =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RescueAnimal that)) return false;
        return Objects.equals(uniqueId, that.uniqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId);
    }
    /** Creates a formatted string version of the animal record for printing. */
    @Override
    public String toString() {
        return "******************************\n" +
                "Rescue ID: " + uniqueId + "\n" +
                "Name: " + valueOrDash(name) + "\n" +
                "Gender: " + valueOrDash(gender) + "\n" +
                "Acquisition Date: " + valueOrDash(acquisitionDate) + "\n" +
                "Acquisition Country: " + valueOrDash(acquisitionCountry) + "\n" +
                "Training Status: " + trainingStatus.menuLabel() + "\n" +
                "Reserved: " + reserved + "\n" +
                "In-Service Country: " + valueOrDash(inServiceCountry) + "\n";
    }

    private static String valueOrDash(String s) {
        return (s == null || s.isBlank()) ? "-" : s;
    }
}




