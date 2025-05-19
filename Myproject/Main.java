package Myproject;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n💼 GetItDone ");
            System.out.println("1️⃣ Register");
            System.out.println("2️⃣ Login");
            System.out.println("3️⃣ Exit");
            System.out.print("\nEnter Choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: new Registration().register(); break;
                case 2: new Login().authenticate(); break;
                case 3: System.out.println("\n🚪 Exiting..."); System.exit(0);
                default: System.out.println("Invalid Choice!");
            }
        }
    }
}