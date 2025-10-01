package com.matthew.animalapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private final DatabaseConnector connector;

    public DatabaseHelper(DatabaseConnector connector) {
        this.connector = connector;
        createTableIfNotExists();
    }

    void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS animals (
                id TEXT PRIMARY KEY,
                type TEXT NOT NULL,
                name TEXT,
                breed TEXT,
                species TEXT,
                gender TEXT,
                age TEXT,
                weight TEXT,
                acquisitionDate TEXT,
                acquisitionCountry TEXT,
                trainingStatus TEXT,
                reserved INTEGER,
                inServiceCountry TEXT,
                tailLength REAL,
                height REAL,
                bodyLength REAL
            )
        """;
        try (Connection conn = connector.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating animals table", e);
        }
    }

    // --- Add or Update (using UPSERT) ---
    public void addOrUpdateAnimal(RescueAnimal a) {
        String sql = """
            INSERT INTO animals (id, type, name, breed, species, gender, age, weight,
                                 acquisitionDate, acquisitionCountry, trainingStatus,
                                 reserved, inServiceCountry, tailLength, height, bodyLength)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            ON CONFLICT(id) DO UPDATE SET
                type=excluded.type,
                name=excluded.name,
                breed=excluded.breed,
                species=excluded.species,
                gender=excluded.gender,
                age=excluded.age,
                weight=excluded.weight,
                acquisitionDate=excluded.acquisitionDate,
                acquisitionCountry=excluded.acquisitionCountry,
                trainingStatus=excluded.trainingStatus,
                reserved=excluded.reserved,
                inServiceCountry=excluded.inServiceCountry,
                tailLength=excluded.tailLength,
                height=excluded.height,
                bodyLength=excluded.bodyLength
        """;

        try (Connection conn = connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            fillAnimalStatement(ps, a);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving animal", e);
        }
    }

    public RescueAnimal getAnimalById(String id) {
        String sql = "SELECT * FROM animals WHERE id = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRowToAnimal(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching animal by id", e);
        }
        return null;
    }

    public List<RescueAnimal> listAnimals() {
        List<RescueAnimal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animals";
        try (Connection conn = connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                animals.add(mapRowToAnimal(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listing animals", e);
        }
        return animals;
    }

    public void deleteAnimal(String id) {
        String sql = "DELETE FROM animals WHERE id = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting animal", e);
        }
    }

    // --- Utility: Fill PreparedStatement ---
    private void fillAnimalStatement(PreparedStatement ps, RescueAnimal a) throws SQLException {
        ps.setString(1, a.getUniqueId());
        ps.setString(2, (a instanceof Dog) ? "dog" : (a instanceof Monkey) ? "monkey" : "rescue");
        ps.setString(3, a.getName());
        ps.setString(4, (a instanceof Dog d) ? d.getBreed() : null);
        ps.setString(5, (a instanceof Monkey m) ? m.getSpecies() : null);
        ps.setString(6, a.getGender());
        ps.setString(7, a.getAge());
        ps.setString(8, a.getWeight());
        ps.setString(9, a.getAcquisitionDate());
        ps.setString(10, a.getAcquisitionCountry());
        ps.setString(11, a.getTrainingStatus());
        ps.setInt(12, a.isReserved() ? 1 : 0);
        ps.setString(13, a.getInServiceCountry());
        ps.setObject(14, (a instanceof Monkey m) ? m.getTailLength() : null);
        ps.setObject(15, (a instanceof Monkey m) ? m.getHeight() : null);
        ps.setObject(16, (a instanceof Monkey m) ? m.getBodyLength() : null);
    }

    // --- Utility: Map ResultSet to RescueAnimal ---
    private RescueAnimal mapRowToAnimal(ResultSet rs) throws SQLException {
        String type = rs.getString("type");
        if ("dog".equalsIgnoreCase(type)) {
            return new Dog(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("breed"),
                    rs.getString("gender"),
                    rs.getString("age"),
                    rs.getString("weight"),
                    rs.getString("acquisitionDate"),
                    rs.getString("acquisitionCountry"),
                    rs.getString("trainingStatus"),
                    rs.getInt("reserved") == 1,
                    rs.getString("inServiceCountry")
            );
        } else if ("monkey".equalsIgnoreCase(type)) {
            return new Monkey(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("species"),
                    rs.getString("gender"),
                    rs.getString("age"),
                    rs.getString("weight"),
                    rs.getString("acquisitionDate"),
                    rs.getString("acquisitionCountry"),
                    rs.getString("trainingStatus"),
                    rs.getInt("reserved") == 1,
                    rs.getString("inServiceCountry"),
                    rs.getDouble("tailLength"),
                    rs.getDouble("height"),
                    rs.getDouble("bodyLength")
            );
        }
        return null;
    }
}
