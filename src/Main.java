import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Accounting Ledger Application ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    System.out.println("You chose to Add a Deposit.");
                    System.out.print("Enter description: ");
                    String depositDescription = scanner.nextLine();
                    System.out.print("Enter vendor: ");
                    String depositVendor = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());

                    LocalDateTime now = LocalDateTime.now();
                    String depositDate = now.toLocalDate().toString();
                    String depositTime = now.toLocalTime().withNano(0).toString();

                    Transaction deposit = new Transaction(depositDate, depositTime, depositDescription, depositVendor, depositAmount);
                    try {
                        Files.write(Paths.get("transactions.csv"),
                                Arrays.asList(deposit.toCSV()),
                                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        System.out.println("Deposit saved!");
                    } catch (Exception e) {
                        System.out.println("Error saving deposit: " + e.getMessage());
                    }
                    break;
                case "P":
                    System.out.println("You chose to Make a Payment.");
                    // TODO: Add payment code here
                    break;
                case "L":
                    System.out.println("You chose to View the Ledger.");
                    // TODO: Show ledger here
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
