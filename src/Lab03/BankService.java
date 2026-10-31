package Lab03;

import java.nio.file.Path;
import java.util.Scanner;

public class BankService {

    private final Scanner input;
    private final Path path = Path.of("Users.json");
    private final AccountRepo accountRepo = new AccountRepo(path);

    public BankService(Scanner input) {
        this.input = input;
    }

    public void clearScreen() {
        System.out.println("\033[H\033[2J");
    }

    public void Register(String currentTime) {
        String name, password;
        long accountNumber;
        double balance;
        String str;

        clearScreen(); 
        while (true) {
        	System.out.print("Enter your name (Enter 'q' to go back): ");
        	name = input.nextLine();
        	if (name.equals("q") || name.equals("Q")) {clearScreen(); return;}
        	else if (name.trim().isEmpty()) {clearScreen(); System.out.println("Empty Input!\n" + "=".repeat(30));}
        	else break;
        }
        
        clearScreen();
        while (true) {
            System.out.print("Enter your Account Number (Enter 'q' to go back): ");
            str = input.nextLine();
            accountNumber = longCheck(str);
            if (str.equals("q") || str.equals("Q")) {clearScreen(); return;}
            else if (accountNumber != 0) break;
            else {clearScreen(); System.out.println("Invalid Input!\n" + "=".repeat(30));}
        }
        
        if (accountRepo.findAccountNumber(accountNumber) || accountRepo.findName(name)) {
            clearScreen();
            System.out.println("This account is already existed!");
            System.out.println("=".repeat(30));
            return;
        }

        clearScreen();
        while (true) {        	
        	System.out.print("Enter New password (Enter 'q' to go back): ");
        	password = input.nextLine();
        	if (password.equals("q") || password.equals("Q")) {clearScreen(); return;}
        	else if (password.trim().isEmpty()) {clearScreen(); System.out.println("Empty Input!\n" + "=".repeat(30));}
        	else break;
        }

        clearScreen();
        while (true) { 
            System.out.print("Enter your Balance (Enter 'q' to go back): ");
            str = input.nextLine();
            balance = doubleCheck(str);
            if (str.equals("0")) break;
            else if (str.equals("q") || str.equals("Q")) {clearScreen(); return;}
            if (balance < 0 || balance > 10e6) {clearScreen(); System.out.println("Invalid Amount!\n" + "=".repeat(30));}
            else if (balance == 0) {clearScreen(); System.out.println("Invalid Input!\n" + "=".repeat(30));}
            else break;
        }
        
        accountRepo.addNew(new Account(name, accountNumber, balance, currentTime));

        clearScreen();
        System.out.println("Please log in to your account!");
        System.out.println("=".repeat(30));
    }

    public Account LogIn() {
        long accountNumber;
        String str, password;

        clearScreen();
        while (true) {
            System.out.print("Enter your Account Number (Enter 'q' to go back): ");
            str = input.nextLine();
            accountNumber = longCheck(str);
            if (str.equals("q") || str.equals("Q")) {clearScreen(); return null;}
            else if (accountNumber != 0) break;
            else {clearScreen(); System.out.println("Invalid Input!\n" + "=".repeat(30));}
        }

        clearScreen();
        while (true) {        	
        	System.out.print("Enter your password (Enter 'q' to go back): ");
        	password = input.nextLine();
        	if (password.equals("q") || password.equals("Q")) {clearScreen(); return null;}
        	else if (password.trim().isEmpty()) {clearScreen(); System.out.println("Empty Input!\n" + "=".repeat(30));}
        	else break;
        }

        Account account = accountRepo.getAccount(accountNumber, password);
        if (account != null) return account;

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

    public boolean Transfer(Account accountA, long account_num, double amount, String currentTime) {
        if (account_num != accountA.getAccountNumber() && accountRepo.findAccountNumber(account_num)) { // Transfer from Account A (You)
            Account accountB = accountRepo.getAccount(account_num); // The receiver (Account B)
            if (accountA.Transfer(accountB.getName(), account_num, amount, currentTime)) {
                accountRepo.update(accountA); // Update Account A Transaction history and Current balance.

                accountB.Receive(accountA.getName(), accountA.getAccountNumber(), amount, currentTime);
                accountRepo.update(accountB); // Update Account B Transaction history and Current balance.
                return true;
            }
        }
        return false;
    }

    public void changeName(Account account, String new_name) {
        account.changeName(new_name);
        accountRepo.update(account);
    }

    public void changePassword(Account account, String new_password) {
        account.changePassword(new_password);
        accountRepo.update(account);
    }

    public long longCheck(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return 0;
        }
    }

    public double doubleCheck(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            clearScreen();
            return 0;
        }
    }
}

