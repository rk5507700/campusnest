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
            String query = "SELECT * FROM properties WHERE is_visible = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setBoolean(1, true);
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
            int property_id;
            String query = "INSERT INTO favorites (property_id, email, property_details ) VALUES (?, ?, ?)";
            String query_to_get_id = "SELECT property_id FROM properties WHERE property_details = ?";
            try(PreparedStatement stmt = conn.prepareStatement(query_to_get_id)){
                stmt.setString(1, property);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                property_id = rs.getInt("property_id");
            }
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, property_id);
                stmt.setString(2, email);
                stmt.setString(3, property);
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

    public List<String> getPropertiesByOwner(String email){
        List<String> propertyByOwner = new ArrayList<>();
        try (Connection conn = dbHelper.getConnection()){
            String query = "SELECT property_details FROM properties WHERE owner_email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String add_property = rs.getString("property_details");
                    propertyByOwner.add(add_property);
                }
                
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return propertyByOwner;
    }

    public boolean updateVisibility(String property){
        boolean status = false;
        try(Connection conn = dbHelper.getConnection()) {
            String visible_status_query = "SELECT is_visible FROM properties WHERE property_details = ?";
            try(PreparedStatement stmt = conn.prepareStatement(visible_status_query)){
                stmt.setString(1, property);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                boolean visibility_status = rs.getBoolean("is_visible");

                String update_query = "UPDATE properties SET is_visible = ? WHERE property_details = ?";
                try(PreparedStatement statement_for_update = conn.prepareStatement(update_query)){
                    statement_for_update.setBoolean(1, !visibility_status);
                    statement_for_update.setString(2, property);
                    statement_for_update.executeUpdate();
                    status = !visibility_status;
                }
            }
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return status;
    }

    public void deleteProperty(String property){
        try(Connection conn = dbHelper.getConnection()) {
            String query = "DELETE FROM properties WHERE property_details = ?";
            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1, property);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void addNewProperty(String email, String property){
        try(Connection conn = dbHelper.getConnection()) {
            String query = "INSERT INTO properties (property_details, owner_email) VALUES(?, ?)";
            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1, property);
                stmt.setString(2, email);

                stmt.executeUpdate();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}