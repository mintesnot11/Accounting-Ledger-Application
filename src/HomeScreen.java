import java.util.Scanner;


//o The home screen should give the user the following options. The
//application should continue to run until the user chooses to exit.
//§ D) Add Deposit - prompt user for the deposit information and
//save it to the csv file
//§ P) Make Payment (Debit) - prompt user for the debit
//information and save it to the csv file
//§ L) Ledger - display the ledger screen
//§ X)  exit
public class HomeScreen {
    TransactionMethods transactionMethods = new TransactionMethods();
    public void homeScreenOption() {

        Scanner scanner = new Scanner(System.in);
        String chose = "";
        while (!chose.equalsIgnoreCase("x")) {
            System.out.print("""
                     ------------------- Home Screen -------------------
                     [D] Add Deposit
                     [P] Make Payment
                     [L] Ledger                   
                     [X] Exit
                     Enter choice: 
                    """);
            chose = scanner.nextLine().toUpperCase();
            if (chose.equals("D")) {

                transactionMethods.addDeposit();
            } else if (chose.equals("P")) {
                transactionMethods.makePayment();
            } else if (chose.equals("L")) {
                System.out.println("you are printing l");
               LedgerHomeScreen ledgerHomeScreen = new LedgerHomeScreen();
               ledgerHomeScreen.ledgerOption();

            } else if(chose.equals("X")){
               System.out.println("Exiting from the application");
            } else {
                System.out.println("Invalid input");

            }


        }


    }


}
