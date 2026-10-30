

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise5 {
	public static class Account { // This is a individual user account 'Class'.
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
	
	public static class AuthService { // This is AUthentication Service.
	    private ArrayList <Account> accounts = new ArrayList<>();
	    public AuthService(ArrayList <Account> accounts) {
	        this.accounts = accounts;
	    }

		public Account Authenticate(String name, int account_num) {
	        for (Account account : accounts) {
	            if (account.getAccountNumber() == account_num && account.getName().equals(name)) {
	                return account;
	            }
	        }
	        return null;
	    }
	    public void SignIn(ArrayList <Account> account) {
	        clearScreen();
	        Scanner input = new Scanner(System.in);
	        System.out.print("Enter your name: ");
	        String name = input.nextLine();
	        System.out.print("Enter your Account Number: ");
	        int account_num = input.nextInt();
	        System.out.print("Enter your Balance: ");
	        double balance = input.nextDouble();
	        account.add(new Account(name, account_num, balance));
	        clearScreen();
	        System.out.println("Please log in to your account!");
	        System.out.println("=".repeat(30));
	    }
	    public Account LogIn(ArrayList <Account> accounts) {
	        clearScreen();

	        Scanner input = new Scanner(System.in);
	        System.out.print("Enter your name: ");
	        String name = input.nextLine();
	        System.out.print("Enter your Account Number: ");
	        int account_number = input.nextInt();

	        Account account = Authenticate(name, account_number);

	        if (account == null) {
	            clearScreen();
	            System.out.println("Sorry! Invalid name or account number!");
	            System.out.println("=".repeat(30));
	        } else {
	            return account;
	        }
	        return null;
	    }
	}

    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
    }

    public static void main(String[] args) { // Main class, where all the code run.

        ArrayList <Account> accounts = new ArrayList<>();
        Account current_acc;
        AuthService authService = new AuthService(accounts);

        while (true) { 
            System.out.println("Enter an option below: ");
            System.out.println("1 - Sign in");
            System.out.println("2 - Log in");
            System.out.println("3 - Exit");
            System.out.print("Your Option: ");
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            switch (option) {
                case 1 -> SignIn(accounts);
                case 2 -> {
                    current_acc = LogIn(accounts);
                    if (current_acc != null) {
                        input.nextLine();
                        clearScreen();
                        System.out.println("Welcome! " + current_acc.getName());
                        System.out.println("=".repeat(30));
                        boolean running = true;
                        while (running) { 
                            System.out.println("Enter an Option below: ");
                            System.out.println("1 - Check Balance");
                            System.out.println("2 - Withdraw Money");
                            System.out.println("3 - Deposit Money");
                            System.out.println("4 - Log out");
                            System.out.print("Your Option: ");
                            option = input.nextInt();
                            switch (option) {
                                case 1 -> {
                                    clearScreen();
                                    System.out.println("=".repeat(30));
                                    System.out.println("Your Current Balance: " + current_acc.getBalance());
                                    System.out.println("=".repeat(30));
                                    break;
                                }
                                case 2 -> {
                                    clearScreen();
                                    while (true) { 
                                        System.out.println("=".repeat(30));
                                        System.out.println("Your Balance: " + current_acc.getBalance());
                                        System.out.print("Enter Amount to Withdraw: ");
                                        double amount = input.nextDouble();
                                        if (current_acc.Withdraw(amount)) {
                                            clearScreen();
                                            System.out.println("Withdraw Successful!!");
                                            break;
                                        }
                                        else {
                                            clearScreen();
                                            System.out.println("Invalid Amount!");
                                        }
                                    }
                                    System.out.println("=".repeat(30));
                                    break;
                                }
                                case 3 -> {
                                    clearScreen();
                                    while (true) {
                                        System.out.println("=".repeat(30));
                                        System.out.println("Your Balance: " + current_acc.getBalance());
                                        System.out.print("Enter Amount to Deposit: ");
                                        double amount = input.nextDouble();
                                        if (current_acc.Deposit(amount)) {
                                            clearScreen();
                                            System.out.println("Deposit Successful!!");
                                            System.out.println("=".repeat(30));
                                            break;
                                        }
                                        else {
                                            clearScreen();
                                            System.out.println("Invalid Amount!!");
                                        } 
                                    }
                                }
                                case 4 -> {
                                    clearScreen();
                                    System.out.println("Logged out!!");
                                    System.out.println("=".repeat(30));
                                    input.nextLine();
                                    running = false;
                                    break;
                                }
                                default -> {
                                    clearScreen();
                                    System.out.println("Invalid Option!!");
                                    System.out.println("=".repeat(30));
                                }
                            }
                        }
                    }
                    break;
                }
                case 3 -> {
                    clearScreen();
                    System.out.println("Goodbye!!");
                    input.close();
                    return;
                }
                default -> {
                    clearScreen();
                    System.out.println("Invalid Option!!");
                    System.out.println("=".repeat(30));
                }
            }
        }
    }
}