// Ledger.java
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Ledger {
    private final String filePath = "transactions.csv";
    private final Scanner in = new Scanner(System.in);

    // Read all transactions
    public ArrayList<Transaction> readTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]).withNano(0);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                transactions.add(new Transaction(date, time, description, vendor, amount));
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        transactions.sort(Comparator.comparing(Transaction::getDateTime).reversed());
        return transactions;
    }

    // Write a transaction to file
    public void writeTransaction(Transaction t) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(t.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    // Add transaction
    public void addTransaction(boolean isDeposit) {
        System.out.print("Description: ");
        String desc = in.nextLine();
        System.out.print("Vendor: ");
        String vendor = in.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(in.nextLine());
        if (!isDeposit) amount = -amount;

        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), desc, vendor, amount);
        writeTransaction(t);
        System.out.println("Transaction added.");
    }

    // Display helpers
    public void displayAll() {
        for (Transaction t : readTransactions()) {
            System.out.println(t);
        }
    }

    public void displayDeposits() {
        for (Transaction t : readTransactions()) {
            if (t.getAmount() > 0) System.out.println(t);
        }
    }

    public void displayPayments() {
        for (Transaction t : readTransactions()) {
            if (t.getAmount() < 0) System.out.println(t);
        }
    }
//    Ledger ledger = new Ledger();
//    Scanner in = new Scanner(System.in);
//    String choice;
//
//            System.out.print("""
//                    ------------------- Main Menu -------------------
//                    [D] Add Deposit
//                    [P] Make Payment
//                    [A] Display All
//                    [L] Display Deposits
//                    [O] Display Payments
//                    [X] Exit
//                    Enter choice:
//                    """);
//    choice = in.nextLine().trim().toUpperCase();
//
//            switch (choice)
//
//    {
//        case "D" -> ledger.addTransaction(true);
//        case "P" -> ledger.addTransaction(false);
//        case "A" -> ledger.displayAll();
//        case "L" -> ledger.displayDeposits();
//        case "O" -> ledger.displayPayments();
//        case "X" -> System.out.println("Goodbye!");
//        default -> System.out.println("Invalid choice.");
//    }
}
