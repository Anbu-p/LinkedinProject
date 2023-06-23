package com.meta.dao;

import com.meta.exception.DataInvalidException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <p>
 * Provides some services for User and Product objects.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class DatabaseConnection {

    private static DatabaseConnection databaseConnection;

    private DatabaseConnection() {
    }

    /**
     * <p>
     * Gets the {@link DatabaseConnection} instance.
     * </p>
     *
     * @return Database connection instance.
     */
    public static DatabaseConnection getDatabaseConnectionInstance() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }

        return databaseConnection;
    }

    /**
     * <p>
     * Gets the {@link Connection} instance.
     * </p>
     *
     * @return connection properties.
     */
    public Connection getConnection() throws SQLException, IOException {
        final Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\V Karthick\\IdeaProjects\\linkedin\\resources\\connection.properties")) {
            properties.load(fileInputStream);

            return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("name"), properties.getProperty("password"));
        }
    }

    public void driverConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException classNotFoundException) {
            throw new DataInvalidException("Driver connection error");
        }
    }
}
