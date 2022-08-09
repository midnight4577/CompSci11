import javax.security.sasl.SaslClient;
import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int accountNumber;
    private ArrayList<Deposit> deposits = new ArrayList<Deposit>();
    private ArrayList<Withdraw> withdraws = new ArrayList<Withdraw>();
    private double checkBalance;
    private double savingBalance;
    private double savingRate;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    private final int OVERDRAFT = -100;

    Customer(){
        this.name = "Unknown";
        this.accountNumber = -1;
        this.checkBalance = 0;
        this.savingBalance = 0;

    }

    Customer(String name, int accountNumber, double checkDeposit, double savingDeposit){
        this.name = name;
        this.accountNumber = accountNumber;
        this.checkBalance = checkDeposit;
        this.savingBalance = savingDeposit;
    }

    //Requires: double, Date, String
    //Modifies: this
    //Effects: adds a deposit to the account with a date and the account name then returns the account's new balance. If anything is invalid it returns 0
    public double deposit(double amt, Date date, String account){
        if (amt > 0){
            switch (account){
                case CHECKING:
                    deposits.add(new Deposit(amt, date, account)); //this is in here since  if the account is incorrect nothing will happen
                    checkBalance += amt;
                    return checkBalance;
                case SAVING:
                    deposits.add(new Deposit(amt, date, account)); //this is in here since  if the account is incorrect nothing will happen
                    savingBalance += amt;
                    return savingBalance;
            }
        }
        return 0.0;
    }

    //Requires: double, Date, String
    //Modifies: this
    //Effects: checks if the account has enough money then adds a withdrawal to the account with a date and the account name then returns the account's new balance. If anything is invalid it returns 0
    public double withdraw(double amt, Date date, String account){
        if (amt > 0){
            switch (account){
                case CHECKING:
                    if (!checkOverdraft(amt, account)) {
                        this.withdraws.add(new Withdraw(amt, date, account)); //this is in here since  if the account is incorrect nothing will happen
                        this.checkBalance -= amt;
                        return this.checkBalance;
                    }
                    return 0.0;
                case SAVING:
                    if (!checkOverdraft(amt, account)) {
                        this.withdraws.add(new Withdraw(amt, date, account)); //this is in here since  if the account is incorrect nothing will happen
                        this.savingBalance -= amt;
                        return this.savingBalance;
                    }
                    return 0.0;
            }
        }
        return 0.0;
    }

    private boolean checkOverdraft(double amt, String account){
        switch (account) {
            case CHECKING:
                return (checkBalance - OVERDRAFT < amt);
            case SAVING:
                return (savingBalance - OVERDRAFT < amt);
        }
        return false; //in case the account is invalid
    }

    //do not modify
    public void displayDeposits(){
        for(Deposit d : deposits){
            System.out.println(d);
        }
    }
    //do not modify
    public void displayWithdraws(){
        for(Withdraw w : withdraws){
            System.out.println(w);
        }
    }

}
