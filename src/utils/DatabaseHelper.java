package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/campus_nest"; // Update with your DB URL
    private static final String USERNAME = "root"; // Update with your DB username
    private static final String PASSWORD = "password";    // Update with your DB password

    public Connection getConnection() throws SQLException {
        // Create and return a connection to the database
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}