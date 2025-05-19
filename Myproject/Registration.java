package Myproject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Registration {
    public void register() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        if (!name.matches("[a-zA-Z ]+")) {
            System.out.println("Invalid name! Only alphabets and spaces allowed.");
            return;
        }

        System.out.print("Create Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Gender (male/female/transgender): ");
        String gender = scanner.next().toLowerCase();
        if (!gender.matches("male|female|transgender")) {
            System.out.println("Invalid gender!");
            return;
        }

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        if (age < 18) {
            System.out.println("You cannot work. Age must be 18 or above.");
            return;
        }

        System.out.print("Enter Mobile Number: ");
        String mobileNumber = scanner.next();
        if (!mobileNumber.matches("\\d{10}")) {
            System.out.println("Invalid Mobile Number! It should be 10 digits.");
            return;
        }

        System.out.print("Enter Location: ");
        scanner.nextLine();
        String location = scanner.nextLine();

        System.out.print("Enter Pin Code (Optional): ");
        String pinCode = scanner.nextLine();

        System.out.print("Enter UPI ID or Mobile Number for Online Payment: ");
        String upiIdOrMobile = scanner.nextLine();

        System.out.print("Are you a Service Provider? (yes/no): ");
        boolean isServiceProvider = scanner.next().equalsIgnoreCase("yes");

        String userId = generateUserId(name, mobileNumber);
        System.out.println("\n✅ Your User ID: " + userId);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO users (id, name, password, gender, age, mobile_number, location, pin_code, upi_id_or_mobile_number, is_service_provider) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.setString(4, gender);
            stmt.setInt(5, age);
            stmt.setString(6, mobileNumber);
            stmt.setString(7, location);
            stmt.setString(8, pinCode);
            stmt.setString(9, upiIdOrMobile);
            stmt.setBoolean(10, isServiceProvider);

            stmt.executeUpdate();
            System.out.println("\n✅ Successfully Registered!\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateUserId(String name, String mobile) {
        String namePrefix = name.length() >= 2 ? name.substring(0, 2).toUpperCase() : name.toUpperCase();
        return namePrefix + mobile.substring(mobile.length() - 4);
    }
}