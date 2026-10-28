
import java.util.ArrayList;

public class Account { // This is a individual user account 'Class'.

    private String name;
    private long account_num;
    private double balance;
    private ArrayList <String> histories = new ArrayList<>();
    
    public Account(String name,
        long account_num,
        double balance,
        String currentTime
    ) {
        this.name = name;
        this.account_num = account_num;
        this.balance = balance;
        histories.add("Account Creation Date: " + currentTime);
        histories.add("Initial Balance: " + this.balance + "\n");
    }

    public boolean Deposit(double amount,String currentTime) {
        if (amount > 0 && amount <= 10e6) {
            balance += amount;
            histories.add("Deposit:\t " + amount + "  \tDate: " + currentTime);
            return true;
        }
        return false;
    }

    public boolean Withdraw(double amount, String currentTime) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            histories.add("Withdraw:\t " + amount + "  \tDate: " + currentTime);
            return true;
        }
        return false;
    }

    public long getAccountNumber() {
        return account_num;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList <String> getHistory() {
        return histories;
    }
}