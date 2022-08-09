import java.util.Date;

public class Deposit {
    private double amount;
    private Date date;
    private String account;

    Deposit(double amount, Date date, String account){
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    //Requires:
    //Modifies:
    //Effects: Returns a string of the amount, date and account of the deposit in a specific format
    public String toString(){
        return "Deposit of: $"+this.amount+" Date: "+this.date+" into account: "+this.account;
    }
}
