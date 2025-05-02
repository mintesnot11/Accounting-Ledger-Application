import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class LedgerMethods {

    private static final String CSV_FILE = "transactions.csv";

    public static void displayTransactions(List<Transaction> transactions) {
        // Display header
        System.out.printf("\n%-12s %-8s %-25s %-15s %10s\n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-".repeat(80));

        // Display transactions
        if (transactions.isEmpty()) {
            System.out.println("No transactions found");
        } else {
            // Sort using the comparator from Transaction class
            transactions.sort(new Transaction().newestFirstComparator);

            transactions.forEach(t ->
                    System.out.printf("%-12s %-8s %-25s %-15s %10.2f\n",
                            t.getDate(),
                            t.getTime(),
                            t.getDescription(),
                            t.getVendor(),
                            t.getAmount()));
        }
    }
    // 2025-04-30|22:13:14|test|test|34
    // [2025-04-30, 22:13:14, test, test, 34]

    public static List<Transaction> loadTransactions() throws IOException {
        return Files.lines(Paths.get(CSV_FILE))
                .skip(1) // Skip header row
                .filter(line -> !line.trim().isEmpty())
                .map(line -> line.split("\\|"))
                .map(parts -> new Transaction(
                        LocalDate.parse(parts[0]),
                        LocalTime.parse(parts[1]),
                        parts[2],
                        parts[3],
                        Double.parseDouble(parts[4])
                ))
                .sorted(new Transaction().newestFirstComparator) // Sort newest first
                .collect(Collectors.toList());
    }

    public void displayAllEntries(){
        System.out.println("\n=== ALL TRANSACTIONS ===");
        List<Transaction> transactions = null;
        try {
            transactions = loadTransactions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        displayTransactions(transactions);
    }
    public void displayDeposit(){
        List<Transaction> transactions = null;
        try {
            transactions = loadTransactions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n=== DEPOSITS ===");
        List<Transaction> deposits = transactions.stream()
                .filter(t -> t.getAmount() > 0)
                .collect(Collectors.toList());
        displayTransactions(deposits);
    }
    public void displayPayments(){
        List<Transaction> transactions = null;
        try {
            transactions = loadTransactions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n=== PAYMENTS ===");
        List<Transaction> payments = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.toList());
        displayTransactions(payments);
    }

    public void displayMonthToDate(List<Transaction> transactions) {
        LocalDate now = LocalDate.now();
        List<Transaction> filtered = transactions.stream()
                .filter(t -> t.getDate().getMonth() == now.getMonth() &&
                        t.getDate().getYear() == now.getYear())
                .collect(Collectors.toList());

        System.out.println("\n=== MONTH TO DATE ===");
        displayTransactions(filtered);
    }

    public void displayPreviousMonth(List<Transaction> transactions) {
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        List<Transaction> filtered = transactions.stream()
                .filter(t -> t.getDate().getMonth() == lastMonth.getMonth() &&
                        t.getDate().getYear() == lastMonth.getYear())
                .collect(Collectors.toList());

        System.out.println("\n=== PREVIOUS MONTH ===");
        displayTransactions(filtered);
    }

    public void displayYearToDate(List<Transaction> transactions) {
        int currentYear = LocalDate.now().getYear();
        List<Transaction> filtered = transactions.stream()
                .filter(t -> t.getDate().getYear() == currentYear)
                .collect(Collectors.toList());

        System.out.println("\n=== YEAR TO DATE ===");
        displayTransactions(filtered);
    }

    public void displayPreviousYear(List<Transaction> transactions) {
        int previousYear = LocalDate.now().getYear() - 1;
        List<Transaction> filtered = transactions.stream()
                .filter(t -> t.getDate().getYear() == previousYear)
                .collect(Collectors.toList());

        System.out.println("\n=== PREVIOUS YEAR ===");
        displayTransactions(filtered);
    }

    public void searchByVendor(List<Transaction> transactions) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter vendor name to search: ");
        String searchTerm = scanner.nextLine().trim().toLowerCase(); // Convert to lowercase for case-insensitive comparison

        List<Transaction> filtered = transactions.stream()
                .filter(t -> t.getVendor().toLowerCase().contains(searchTerm)) // Partial match
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("\nNo transactions found containing: " + searchTerm);
        } else {
            System.out.println("\n=== TRANSACTIONS CONTAINING VENDOR: '" + searchTerm + "' ===");
            displayTransactions(filtered);
        }
    }
}
