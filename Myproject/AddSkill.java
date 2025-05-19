package Myproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddSkill {
    public void addSkill() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter Your Registered Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Your Registered Mobile Number (10 digits): ");
        String mobileNumber = scanner.nextLine();

        if (!mobileNumber.matches("\\d{10}")) {
            System.out.println("Invalid Mobile Number! Must be 10 digits.");
            return;
        }

        System.out.print("Enter Your UPI ID or Online Payment Mobile Number: ");
        String upiIdOrMobile = scanner.nextLine();

        System.out.print("Enter Skill (e.g., Web Development, Plumbing): ");
        String skill = scanner.nextLine();

        System.out.print("Enter Work Location: ");
        String location = scanner.nextLine();

        System.out.print("Is this service Online or Offline? (online/offline): ");
        String workMode = scanner.next().toLowerCase();

        double pricePerHour = 0;
        double pricePerProjectOrDay = 0;

        if (workMode.equals("online")) {
            System.out.print("Enter Price Per Hour: ₹");
            pricePerHour = scanner.nextDouble();
            System.out.print("Enter Price Per Project: ₹");
            pricePerProjectOrDay = scanner.nextDouble();
        } else if (workMode.equals("offline")) {
            System.out.print("Enter Price Per Hour: ₹");
            pricePerHour = scanner.nextDouble();
            System.out.print("Enter Price Per Day: ₹");
            pricePerProjectOrDay = scanner.nextDouble();
        } else {
            System.out.println("Invalid Work Mode! Must be 'online' or 'offline'.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO skills (name, mobile_number, upi_id_or_mobile, skill, location, work_mode, price_per_hour, price_per_project_or_day) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, mobileNumber);
            stmt.setString(3, upiIdOrMobile);
            stmt.setString(4, skill);
            stmt.setString(5, location);
            stmt.setString(6, workMode);
            stmt.setDouble(7, pricePerHour);
            stmt.setDouble(8, pricePerProjectOrDay);

            stmt.executeUpdate();
            System.out.println("\n✅ Skill Added Successfully!\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
