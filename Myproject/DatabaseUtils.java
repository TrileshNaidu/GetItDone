package Myproject;

import java.sql.*;

public class DatabaseUtils {

    public static boolean testConnection() {
        Connection conn = null;
        try {
            // Try to connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "Ram@123");

            // If the connection is successful
            System.out.println("Connection successful!");
            return true;

        } catch (SQLException e) {
            // If there is any issue with the connection
            System.out.println("Connection failed: " + e.getMessage());
            return false;

        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkIfUserExists(String username) {
        Connection conn = null;
        boolean userExists = false;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "Ram@123");
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userExists = true;
                System.out.println("User exists: " + rs.getString("username"));
            } else {
                System.out.println("User not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userExists;
    }

    public static void main(String[] args) {
        // Test the connection
        boolean isConnectionSuccessful = testConnection();

        if (isConnectionSuccessful) {
            // Check if a user with a specific username exists in the database
            boolean userExists = checkIfUserExists("john_doe"); // Replace with the username you want to check

            if (userExists) {
                System.out.println("User exists in the database.");
            } else {
                System.out.println("User does not exist in the database.");
            }
        }
    }
}