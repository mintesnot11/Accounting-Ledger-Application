import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ReportHomeScreen {
    public void reportDisplayOption() {
        Scanner scanner = new Scanner(System.in);
        String chose = "";
        LedgerMethods ledgerMethods = new LedgerMethods();
        while (!chose.equalsIgnoreCase("0")) {
            System.out.print("""
                     ------------------- Report Screen -------------------
                     [1] Month To Date
                     [2] Previous Month
                     [3] Year To Date
                     [4] Previous Year
                     [5] Search by Vendor 
                     [0] Back
                     Enter choice: 
                    """);
            chose = scanner.nextLine().toUpperCase();
            List<Transaction> transactions = null;
            try {
                transactions = ledgerMethods.loadTransactions();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (chose.equals("1")) {
                // transactionMethods.addDeposit();
                ledgerMethods.displayMonthToDate(transactions);
            } else if (chose.equals("2")) {
                ledgerMethods.displayPreviousMonth(transactions);
            } else if (chose.equals("3")) {
                ledgerMethods.displayYearToDate(transactions);
            } else if (chose.equals("4")) {
                ledgerMethods.displayPreviousYear(transactions);
            } else if (chose.equals("5")) {
                ledgerMethods.searchByVendor(transactions);
            } else if (chose.equals("0")){
                LedgerHomeScreen ledgerHomeScreen = new LedgerHomeScreen();
                ledgerHomeScreen.ledgerOption();
            } else {
                System.out.println("Invalid input");

            }
        }
    }
}