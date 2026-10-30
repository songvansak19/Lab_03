

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise5 {
	public static class Account { // This is a individual user account 'Class'.
	    private String name;
	    private long account_num;
	    private double balance;

	    public Account(String name, long account_num, double balance) {
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

	    public long getAccountNumber() {
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
	    private Scanner input;
	    public AuthService(ArrayList <Account> accounts, Scanner input) {
	        this.accounts = accounts;
	        this.input = input;
	    }

		public Account Authenticate(long account_num) {
	        for (Account account : accounts) {
	            if (account.getAccountNumber() == account_num) {
	                return account;
	            }
	        }
	        return null;
	    }
		
	    public void Register(ArrayList <Account> account) {
	        clearScreen();
	        String name;
	        String str;
	        Long account_num;
	        double balance;
	        
	        input.nextLine();
	        while (true) {
	        	System.out.print("Enter your name: ");
	        	name = input.nextLine();
	        	if (name.trim().isEmpty()) {
	        		clearScreen();
	        		System.out.println("Invalid Name!");
	        		System.out.println("=".repeat(30));
	        	} else break;
	        }
	        
	        while (true) {
	        	System.out.print("Enter your Account Number: ");
	        	str = input.nextLine();
	        	
	        	if (str.length() == 14 && LongParse(str)) {
	        		account_num = Long.parseLong(str);
	        		break;
	        	} else {
	        		clearScreen();
	        		System.out.println("Invalid Input!");
	        		System.out.println("=".repeat(30));
	        	}
	        }
	        	
	        while (true) {
	        	System.out.print("Enter your Balance: ");
	        	str = input.nextLine();
	        	if (DoubleParse(str)) {
	        		balance = Double.parseDouble(str);
	        		if (balance >= 0 && balance <= 10e6) break;
	        		else {
	        			clearScreen();
		        		System.out.println("Invlaid Amount!");
		        		System.out.println("=".repeat(30));
	        		}
	        	}
	        	else {
	        		clearScreen();
	        		System.out.println("Invlaid Input!");
	        		System.out.println("=".repeat(30));
	        	}
	        }
	        	
        	account.add(new Account(name, account_num, balance));
	        clearScreen();
	        System.out.println("Please log in to your account!");
	        System.out.println("=".repeat(30));
	    }
	    
	    public Account LogIn(ArrayList <Account> accounts) {
	        clearScreen();
	        input.nextLine();
	        String str;
	        long account_number;	
	        
        	System.out.print("Enter your Account Number: ");
        	str = input.nextLine();
        	if (LongParse(str)) {	        		
        		account_number = Long.parseLong(str);
        		for (Account account : accounts) {
        			if (account.getAccountNumber() == account_number) return Authenticate(account.getAccountNumber());
        		}
        	}
        	clearScreen();
        	System.out.println("Sorry! Invalid account number or input!");
        	System.out.println("=".repeat(30));
	        return null;
	    }
	    
	    public boolean LongParse(String str) {
	    	try {Long.parseLong(str);} 
	    	catch (Exception e) {return false;}
	    	return true;
	    }
	    
	    public boolean DoubleParse(String str) {
	    	try {Double.parseDouble(str);} 
	    	catch (Exception e) {return false;}
	    	return true;
	    }
	}

    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
    }

    public static void main(String[] args) { // Main class, where all the code run.

    	Scanner input = new Scanner(System.in);
        ArrayList <Account> accounts = new ArrayList<>();
        Account current_acc;
        AuthService authService = new AuthService(accounts, input);

        while (true) { 
            System.out.println("Enter an option below: ");
            System.out.println("1 - Sign in");
            System.out.println("2 - Log in");
            System.out.println("3 - Exit");
            System.out.print("Your Option: ");
            int option = input.nextInt();
            switch (option) {
                case 1 -> authService.Register(accounts);
                case 2 -> {
                    current_acc = authService.LogIn(accounts);
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
                                    	double amount;
                                        System.out.println("=".repeat(30));
                                        System.out.println("Your Balance: " + current_acc.getBalance());
                                        System.out.print("Enter Amount to Deposit: ");
                                        amount = input.nextDouble();
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