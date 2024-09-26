package services;

import utils.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PropertyService {
    private DatabaseHelper dbHelper;

    public PropertyService() {
        dbHelper = new DatabaseHelper();
    }

    public List<String> getAllProperties() {
        List<String> properties = new ArrayList<>();
        try (Connection conn = dbHelper.getConnection()) {
            String query = "SELECT * FROM property_owners";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String property = rs.getString("property_details");
                    properties.add(property);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public void addToFavorites(String email, String property) {
        try (Connection conn = dbHelper.getConnection()) {
            String query = "INSERT INTO favorites (email, property_details) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, property);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getFavoriteProperties(String email) {
        List<String> favorites = new ArrayList<>();
        try (Connection conn = dbHelper.getConnection()) {
            String query = "SELECT property_details FROM favorites WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String property = rs.getString("property_details");
                    favorites.add(property);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return favorites;
    }
}