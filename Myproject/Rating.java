package Myproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Rating {
    public void rateService() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter Service Provider's Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Your Rating (1 to 5): ");
        double rating = scanner.nextDouble();

        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating! Please enter a value between 1 and 5.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE skills SET rating = (rating + ?) / 2 WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, rating);
            stmt.setString(2, name);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("\n✅ Rating Submitted Successfully!\n");
            } else {
                System.out.println("\n❌ No Service Provider Found with the given name.\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}