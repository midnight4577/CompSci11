import java.util.Date;

public class Withdraw {
    private double amount;
    private Date date;
    private String account;

    Withdraw(double amount, Date date, String account){
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    //Requires:
    //Modifies:
    //Effects: Returns a string of the amount, date and account of the withdrawal in a specific format
    public String toString(){
        return "Withdrawal of: $"+this.amount+" Date: "+this.date+" from account: "+this.account;
    }
}
