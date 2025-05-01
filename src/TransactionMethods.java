import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TransactionMethods {
    Scanner scanner = new Scanner(System.in);

    //Time and date formater//
    LocalDateTime currentDateTime = LocalDateTime.now();

    // Format date only
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = currentDateTime.format(dateFormatter);

    // Format time only
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    String formattedTime = currentDateTime.format(timeFormatter);

    public void addDeposit(){
        System.out.println("please enter vendor name");
        String vendorName = scanner.nextLine();

        System.out.println("please enter description");
        String description = scanner.nextLine();

        System.out.println("please enter amount");
        String amount = scanner.nextLine();
        try {
            FileWriter writer = new FileWriter("transactions.csv",true);
            writer.write("\n" + formattedDate + "|" + formattedTime + "|" + description + "|" + vendorName + "|"  + amount );
            writer.close();
            System.out.println("File written successfully.");
        } catch (Exception e) {
            System.out.println("Error writing to file.");
        }





    }
    public void makePayment(){
        System.out.println("please enter vendor name");
        String vendorName = scanner.nextLine();

        System.out.println("please enter description");
        String description = scanner.nextLine();

        System.out.println("please enter amount");
        String amount = scanner.nextLine();
        try {
            FileWriter writer = new FileWriter("transactions.csv",true);
            writer.write("\n" + formattedDate + "|" + formattedTime + "|" + description + "|" + vendorName + "|"  +"-"+ amount );
            writer.close();
            System.out.println("File written successfully.");
        } catch (Exception e) {
            System.out.println("Error writing to file.");
        }





    }





}
