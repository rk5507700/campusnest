package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/campusnest";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}