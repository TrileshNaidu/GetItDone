package Myproject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Login {
    public void authenticate() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("\n✅ Login Successful!\n");
                showDashboard();
            } else {
                System.out.println("\n❌ Invalid Credentials!\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDashboard() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n📌 DASHBOARD");
            System.out.println("1️⃣ Add Skill");
            System.out.println("2️⃣ Search for Service Provider");
            System.out.println("3️⃣ Show Work History");
            System.out.println("4️⃣ Logout");
            System.out.print("\nEnter Choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: new AddSkill().addSkill(); break;
                case 2: new SearchService().searchService(); break;
                case 3: System.out.println("Showing Work History..."); break;
                case 4: System.out.println("\n🔒 Logged Out!\n"); return;
                default: System.out.println("Invalid Choice!");
            }
        }
    }
}