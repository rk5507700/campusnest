package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DatabaseHelper;

public class RegistrationService {

    private DatabaseHelper dbHelper;

    public RegistrationService() {
        dbHelper = new DatabaseHelper();
    }

    public boolean registerUser(String name, String email, String password) {
        // Normalize email by trimming whitespace and converting to lowercase
        email = email.toLowerCase(); 

        try (Connection conn = dbHelper.getConnection()) {
            // Check if email already exists
            String checkQuery = "SELECT * FROM users WHERE email = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, email);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Email already exists in the database: " + email);
                    return false; // Email already exists
                }
            }

            // Insert new user if email is unique
            String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, password);

                int rowsInserted = stmt.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            return false;
        }
    }

    public boolean registerPropertyOwner(String name, String email, String password, String propertyDetails) {
        email = email.trim().toLowerCase(); // Normalize email
        try (Connection conn = dbHelper.getConnection()) {
            String query = "INSERT INTO property_owners (name, email, password, property_details) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, password);
                stmt.setString(4, propertyDetails);

                int rowsInserted = stmt.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            return false;
        }
    }

    public boolean loginUser(String email, String password) {
        email = email.trim().toLowerCase(); // Normalize email
        try (Connection conn = dbHelper.getConnection()) {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();
                return rs.next(); // Returns true if a match is found, false otherwise
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            return false;
        }
    }
}