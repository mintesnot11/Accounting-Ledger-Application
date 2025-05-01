import java.util.Scanner;

//A) All - Display all entries
//o D) Deposits - Display only the entries that are deposits into the
//        account
//o P) Payments - Display only the negative entries (or payments)
//o R) Reports
public class LedgerHomeScreen {
    LedgerMethods ledgerMethods = new LedgerMethods();

    public void ledgerOption() {
        Scanner scanner = new Scanner(System.in);
        String chose = "";
        while (!chose.equalsIgnoreCase("H")) {
            System.out.print("""
                     ------------------- Ledger Screen -------------------
                     [A] Display all entries
                     [D] Display only deposits
                     [P] Display only payment              
                     [R] Reports 
                     [H] Home 
                     Enter choice: 
                    """);
            chose = scanner.nextLine().toUpperCase();
            if (chose.equals("A")) {
             ledgerMethods.displayAllEntries();
               // transactionMethods.addDeposit();
            } else if (chose.equals("D")) {
              ledgerMethods.displayDeposit();
            } else if (chose.equals("P")) {
              ledgerMethods.displayPayments();
            } else if (chose.equals("R")) {
ReportHomeScreen reportHomeScreen = new ReportHomeScreen();
reportHomeScreen.reportDisplayOption();

            } else if (chose.equals("H")) {
                HomeScreen homeScreen = new HomeScreen();
                homeScreen.homeScreenOption();
            } else {
                System.out.println("Invalid input");

            }


        }


    }
}
