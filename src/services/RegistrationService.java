package services;

import utils.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegistrationService {
    private DatabaseHelper dbHelper;

    public RegistrationService() {
        dbHelper = new DatabaseHelper();
    }

    public boolean registerUser(String name, String email, String password) {
        try (Connection conn = dbHelper.getConnection()) {
            String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, password);
                stmt.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerPropertyOwner(String name, String email, String password, String propertyDetails) {
        try (Connection conn = dbHelper.getConnection()) {
            String query = "INSERT INTO property_owners (name, email, password, property_details) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, password);
                stmt.setString(4, propertyDetails);
                stmt.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean login(String email, String password, String userType) {
        try (Connection conn = dbHelper.getConnection()) {
            String query = userType.equals("user") ? "SELECT * FROM users WHERE email = ? AND password = ?"
                    : "SELECT * FROM property_owners WHERE email = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}