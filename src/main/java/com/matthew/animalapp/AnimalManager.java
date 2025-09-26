package com.matthew.animalapp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * AnimalManager handles in-memory storage of rescue animals.
 * Uses a HashMap keyed by each animal's uniqueId.
 * Supports CRUD operations and filtering by type, training status, and reservation.
 */
public class AnimalManager {

    private final Map<String, RescueAnimal> animals = new HashMap<>();

    // ===== CRUD METHODS =====

    /**
     * Add a new animal. Returns true if added, false if duplicate ID.
     */
    public boolean addAnimal(RescueAnimal animal) {
        if (animal == null) return false;
        return animals.putIfAbsent(animal.getUniqueId(), animal) == null;
    }

    /**
     * Find an animal by uniqueId.
     */
    public RescueAnimal getAnimalById(String id) {
        if (id == null) return null;
        for (String key : animals.keySet()) {
            if (key.equalsIgnoreCase(id)) {
                return animals.get(key);
            }
        }
        return null;
    }

    /**
     * Remove an animal by ID. Returns true if it existed and was removed.
     * not yet implemented in menu.
     */
    public boolean removeAnimal(String id) {
        if (id == null) return false;
        for (String key : new ArrayList<>(animals.keySet())) {
            if (key.equalsIgnoreCase(id)) {
                animals.remove(key);
                return true;
            }
        }
        return false;
    }

    /**
     * Return all animals.
     */
    public List<RescueAnimal> listAll() {
        return new ArrayList<>(animals.values());
    }

    // ===== SEARCH & FILTER METHODS =====

    /**
     * List animals by type (dog or monkey).
     */
    public List<RescueAnimal> listByType(String type) {
        if (type == null) return Collections.emptyList();
        String t = type.trim().toLowerCase();
        return animals.values().stream()
                .filter(a -> {
                    if (t.equals("dog") && a instanceof Dog) return true;
                    if (t.equals("monkey") && a instanceof Monkey) return true;
                    return false;
                })
                .collect(Collectors.toList());
    }

    /**
     * List all animals with the given training status (case-insensitive).
     */
    public List<RescueAnimal> listByStatus(String type, String statusRaw) {
        RescueAnimal.TrainingStatus status;
        try {
            status = RescueAnimal.TrainingStatus.parse(statusRaw);
        } catch (Exception e) {
            return Collections.emptyList();
        }
        return listByType(type).stream()
                .filter(a -> a.getTrainingStatusEnum() == status)
                .collect(Collectors.toList());
    }

    /**
     * List animals available for service: in service and not reserved.
     */
    public List<RescueAnimal> listAvailableForService(String type) {
        return listByType(type).stream()
                .filter(a -> a.getTrainingStatusEnum() == RescueAnimal.TrainingStatus.IN_SERVICE)
                .filter(a -> !a.isReserved())
                .collect(Collectors.toList());
    }

    /**
     * Find animals by name (case-insensitive).
     */
    public List<RescueAnimal> findByName(String name) {
        if (name == null) return Collections.emptyList();
        String target = name.trim().toLowerCase();
        return animals.values().stream()
                .filter(a -> a.getName() != null && a.getName().trim().toLowerCase().equals(target))
                .collect(Collectors.toList());
    }

    // ===== TRAINING & RESERVATION METHODS =====

    /**
     * Advance training one step. Throws if invalid.
     */
    public void advanceTraining(String id) {
        RescueAnimal a = animals.get(id);
        if (a == null) throw new NoSuchElementException("No animal with id: " + id);
        a.advanceTrainingOneStep();
    }

    /**
     * Update training status explicitly.
     */
    public void updateTraining(String id, String newStatusRaw) {
        RescueAnimal a = animals.get(id);
        if (a == null) throw new NoSuchElementException("No animal with id: " + id);
        a.setTrainingStatus(newStatusRaw);
    }

    /**
     * Reserve an animal if eligible.
     */
    public void reserveAnimal(String id) {
        RescueAnimal a = animals.get(id);
        if (a == null) throw new NoSuchElementException("No animal with id: " + id);
        a.reserve();
    }

    /**
     * Unreserve an animal if eligible.
     */
    public void unreserveAnimal(String id) {
        RescueAnimal a = animals.get(id);
        if (a == null) throw new NoSuchElementException("No animal with id: " + id);
        a.unreserve();
    }
}


