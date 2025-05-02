import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;

public class Transaction {
    // Fields
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    // Default constructor
    public Transaction() {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.description = "";
        this.vendor = "";
        this.amount = 0.0;
    }

    // Constructor with arguments
    public Transaction(
            LocalDate date,
            LocalTime time,
            String description,
            String vendor,
            double amount
    ){
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Methods
    public LocalDate getDate(){return this.date;}
    public LocalTime getTime(){return this.time;}
    public LocalDateTime getDateTime(){return LocalDateTime.of(this.date,this.time);}
    public String getDescription(){return this.description;}
    public String getVendor(){return this.vendor;}
    public double getAmount(){return this.amount;}

    // t1 = 01-04-2025 9:00 AM desc vendor amount
    // t2 = 02-03-2025 8:00 AM desc vendor amount

    //What is this doing//
    Comparator<Transaction> newestFirstComparator = (t1, t2) -> {
        // First compare dates (newest first)
        // if t1 and t2 are the same dateCompare = 0
        int dateCompare = t2.getDate().compareTo(t1.getDate());
        if (dateCompare != 0) {
            return dateCompare;
        }
        // If dates are equal, compare times (newest first)
        return t2.getTime().compareTo(t1.getTime());
    };

    public String toString(){
        return String.format(
                "\n%s|%s|%s|%s|%.2f",
                this.date,
                this.time,
                this.description,
                this.vendor,
                this.amount
        );
    }
}
