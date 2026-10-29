
import java.util.ArrayList;

public class Account { // This is a individual user account 'Class'.

    private String name;
    private long account_num;
    private String password;
    private double balance;
    private ArrayList <String> history = new ArrayList<>();
    
    public Account(String name,
        long account_num,
        String password,
        double balance,
        String currentTime
    ) {
        this.name = name;
        this.account_num = account_num;
        this.password = password;
        this.balance = balance;
        history.add("Account Creation Date: " + currentTime);
        history.add("Initial Balance: " + this.balance + "\n");
    }

    public boolean Deposit(double amount,String currentTime) {
        if (amount > 0 && amount <= 10e6) {
            balance += amount;
            history.add("Deposit: " + amount + "      Date: " + currentTime);
            return true;
        }
        return false;
    }

    public boolean Withdraw(double amount, String currentTime) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            history.add("Withdraw: " + amount + "      Date: " + currentTime);
            return true;
        }
        return false;
    }

    public boolean Transfer(String name, long account_num, double amount, String currentTime) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            history.add("Transfer: " + amount + 
            "      To Account: " + account_num + " (" + name + ")" + 
            "      Date: " + currentTime);
            return true;
        }
        return false;
    }

    public void Receive(String name, long account_num, double amount, String currentTime) {
        balance += amount;
        history.add("Receive: " + amount + 
        "      From Account: " + account_num + " (" + name + ")" +
        "      Date: " + currentTime);
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

    public String getPassword() {
        return password;
    }

    public ArrayList <String> getHistory() {
        return history;
    }
}