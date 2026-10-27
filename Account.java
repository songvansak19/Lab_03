public class Account { // This is a individual user account 'Class'.
    private String name;
    private int account_num;
    private double balance;

    public Account(String name, int account_num, double balance) {
        this.name = name;
        this.account_num = account_num;
        this.balance = balance;
    }

    public boolean Deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean Withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public int getAccountNumber() {
        return account_num;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
}
