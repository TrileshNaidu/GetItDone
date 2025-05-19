package Myproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SearchService {
    public void searchService() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter the Skill You Need (e.g., Web Development, Plumbing): ");
        String skill = scanner.nextLine();

        System.out.print("Enter Your Work Location: ");
        String location = scanner.nextLine();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT name, rating FROM skills WHERE skill = ? AND location = ? ORDER BY rating DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, skill);
            stmt.setString(2, location);

            ResultSet rs = stmt.executeQuery();
            boolean found = false;

            while (rs.next()) {
                found = true;
                String name = rs.getString("name");
                double rating = rs.getDouble("rating");
                System.out.println("\n👤 Name: " + name + " | ⭐ Rating: " + (rating > 0 ? rating : "No ratings yet"));

                System.out.print("\nDo you want to contact this service provider? (yes/no): ");
                String choice = scanner.next().toLowerCase();

                if (choice.equals("yes")) {
                    showProviderDetails(name);
                }
            }

            if (!found) {
                System.out.println("\n❌ No service providers found for the given skill and location.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showProviderDetails(String providerName) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM skills WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, providerName);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("\n📞 Contact Details:");
                System.out.println("👤 Name: " + rs.getString("name"));
                System.out.println("📱 Mobile: " + rs.getString("mobile_number"));
                System.out.println("💳 UPI ID / Mobile for Online Payment: " + rs.getString("upi_id_or_mobile"));
                System.out.println("🔧 Experience: " + (rs.getInt("experience") > 0 ? rs.getInt("experience") + " years" : "No experience added"));
                System.out.println("⭐ Rating: " + (rs.getDouble("rating") > 0 ? rs.getDouble("rating") : "No ratings yet"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}