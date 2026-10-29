
import java.nio.file.Path;
import java.util.Scanner;

public class BankService {

    private Scanner input;
    private Path path = Path.of("Users.json");
    private AccountRepo accountRepo = new AccountRepo(path);

    public BankService(Scanner input) {
        this.input = input;
    }

    public void clearScreen() {
        System.out.println("\033[H\033[2J");
    }

    public void Register(String currentTime) {
        String name;
        long accountNumber;
        double balance;
        String str;

        clearScreen(); 
        System.out.print("Enter your name (Enter 'q' to go back): ");
        name = input.nextLine();
        if (name.equals("q") || name.equals("Q")) {clearScreen(); return;}
        
        clearScreen();
        while (true) {
            System.out.print("Enter your Account Number (Enter 'q' to go back): ");
            str = input.nextLine();
            accountNumber = longCheck(str);
            if (str.equals("q") || str.equals("Q")) {clearScreen(); return;}
            else if (accountNumber != 0) break;
        }
        
        if (accountRepo.findAccountNumber(accountNumber) || accountRepo.findName(name)) {
            clearScreen();
            System.out.println("This account is already existed!");
            System.out.println("=".repeat(30));
            return;
        }

        clearScreen();
        while (true) { 
            System.out.print("Enter your Balance (Enter 'q' to go back): ");
            str = input.nextLine();
            balance = doubleCheck(str);
            if (str.equals("0")) break;
            else if (str.equals("q") || str.equals("Q")) {clearScreen(); return;}
            if (balance < 0 || balance > 10e6) {clearScreen(); System.out.println("Invalid Amount!\n" + "=".repeat(30));}
        }
        
        accountRepo.addNew(new Account(name, accountNumber, balance, currentTime));

        clearScreen();
        System.out.println("Please log in to your account!");
        System.out.println("=".repeat(30));
    }

    public Account LogIn() {
        long accountNumber;
        String str;

        clearScreen();
        while (true) {
            System.out.print("Enter your Account Number (Enter 'q' to go back): ");
            str = input.nextLine();
            accountNumber = longCheck(str);
            if (str.equals("q") || str.equals("Q")) {clearScreen(); return null;}
            else if (accountNumber != 0) break;
        }

        if (accountRepo.findAccountNumber(accountNumber))
            return accountRepo.getAccount(accountNumber);

        clearScreen();
        System.out.println("Sorry! Invalid name or account number!");
        System.out.println("=".repeat(30));
        return null;
    }

    public boolean Deposit(Account current_acc, double amount,String currentTime) {
        if (amount > 0) {
            if (current_acc.Deposit(amount, currentTime)) {accountRepo.update(current_acc); return true;}
        }
        return false;
    }

    public boolean Withdraw(Account current_acc, double amount, String currentTime) {
        if (amount > 0 && amount <= current_acc.getBalance()) {
            if (current_acc.Withdraw(amount, currentTime)) {accountRepo.update(current_acc); return true;}
        }
        return false;
    }

    public boolean Transfer(Account current_acc, long account_num, double amount, String currentTime) {
        Account accountA = current_acc;
        if (accountRepo.findAccountNumber(account_num)) { // Transfer from Account A (You)
            if (accountA.Transfer(account_num, amount, currentTime)) {
                accountRepo.update(accountA); // Update Account A Transaction history and Current balance.

                Account accountB = accountRepo.getAccount(account_num); // The receiver (Account B)
                accountB.Receive(current_acc.getAccountNumber(), amount, currentTime);
                accountRepo.update(accountB); // Update Account B Transaction history and Current balance.
                return true;
            }
        }
        return false;
    }

    public long longCheck(String str) {
        try {
            long num = Long.parseLong(str);
            return num;
        } catch (Exception e) {
            clearScreen();
            System.out.println("Invalid Input!");
            System.out.println("=".repeat(30));
            return 0;
        }
    }

    public double doubleCheck(String str) {
        try {
            double num = Double.parseDouble(str);
            return num;
        } catch (Exception e) {
            clearScreen();
            System.out.println("Invalid Input!");
            System.out.println("=".repeat(30));
            return 0;
        }
    }
}
