package com.matthew.animalapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnector handles SQLite connections.
 * Can connect to a production DB file or an in-memory DB for tests.
 */
public class DatabaseConnector {
    private final String url;

    // Default: production file DB
    public DatabaseConnector() {
        this("jdbc:sqlite:animals.db");
    }

    // Allow custom URL (e.g., in-memory for tests)
    public DatabaseConnector(String url) {
        this.url = url;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to DB: " + url, e);
        }
    }

    public static DatabaseConnector forTest() {
        return new DatabaseConnector("jdbc:sqlite:file:testdb?mode=memory&cache=shared");
    }

}
