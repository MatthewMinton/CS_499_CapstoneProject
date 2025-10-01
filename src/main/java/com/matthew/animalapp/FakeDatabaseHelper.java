package com.matthew.animalapp;

import java.util.ArrayList;
import java.util.List;

/*
* This class is designed so that testing can be conducted without the need
* for a secondary testing database. All testing is done in memory and is
* not perpetual and therefore will not skew database entries.
*/

public class FakeDatabaseHelper extends DatabaseHelper {

    private final List<RescueAnimal> store = new ArrayList<>();

    public FakeDatabaseHelper() {
        super(DatabaseConnector.forTest()); // not actually used
    }

    @Override
    public void addOrUpdateAnimal(RescueAnimal a) {
        store.removeIf(existing -> existing.getUniqueId().equals(a.getUniqueId()));
        store.add(a);
    }

    @Override
    public RescueAnimal getAnimalById(String id) {
        return store.stream().filter(a -> a.getUniqueId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<RescueAnimal> listAnimals() {
        return new ArrayList<>(store);
    }

    @Override
    public void deleteAnimal(String id) {
        store.removeIf(a -> a.getUniqueId().equals(id));
    }
}
