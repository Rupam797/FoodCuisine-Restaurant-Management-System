package com.rms.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Centralized database utility class.
 * Provides MySQL connections using configuration from db.properties.
 * Replaces all scattered Oracle connection code across the project.
 */
public class DatabaseUtil {

    private static final Logger LOGGER = Logger.getLogger(DatabaseUtil.class.getName());
    private static final Properties props = new Properties();
    private static boolean driverLoaded = false;

    // Load properties once when the class is first used
    static {
        try (InputStream input = DatabaseUtil.class.getResourceAsStream("/com/rms/util/db.properties")) {
            if (input == null) {
                LOGGER.severe("db.properties not found in classpath!");
            } else {
                props.load(input);
                LOGGER.info("db.properties loaded successfully.");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading db.properties", e);
        }
    }

    /**
     * Gets a new MySQL database connection.
     * Each caller is responsible for closing the connection (use try-with-resources).
     *
     * @return a new Connection to the MySQL database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        if (!driverLoaded) {
            try {
                Class.forName(getProperty("db.driver"));
                driverLoaded = true;
            } catch (ClassNotFoundException e) {
                LOGGER.log(Level.SEVERE, "MySQL JDBC Driver not found!", e);
                throw new SQLException("MySQL JDBC Driver not found", e);
            }
        }

        String url = getProperty("db.url");
        String username = getProperty("db.username");
        String password = getProperty("db.password");

        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Gets a property value from the loaded configuration.
     * Checks Environment Variables first, e.g. db.url -> DB_URL.
     *
     * @param key the property key
     * @return the property value, or null if not found
     */
    public static String getProperty(String key) {
        String envKey = key.replace('.', '_').toUpperCase();
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.trim().isEmpty()) {
            return envValue;
        }
        return props.getProperty(key);
    }
}
