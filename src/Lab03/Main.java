package Lab03;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class Main {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd | HH:mm");

    public static String getCurrentTime() {
        return LocalDateTime.now().format(formatter);
    }

    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
    }

    static int readKey() throws IOException {
        try (Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build()) {
            Attributes saved = terminal.enterRawMode();
            try {
                return terminal.reader().read();
            } finally {
                terminal.setAttributes(saved);
            }
        }
    }

    public static void historyDisplay(ArrayList <String> histories) throws IOException {
    	clearScreen();
    	int num = 0;
    	while (true) {
    		int key;

    		System.out.println("=".repeat(60));
    		System.out.println("Your History:");

    		if (histories.size() <= 5) {
    			for (String history : histories) {
    				System.out.println(" - " + history);
    			}
    			System.out.println("=".repeat(60));
    			System.out.println("Press 'q' to go back | press 'w/s' to navigate");

    			key = (char) readKey();
            } else {
    			for (int i = histories.size() - 1 - num; i > histories.size() - 6 - num; i --) {
    				if (i >= 0 && num <= histories.size() - 5) {
    					System.out.println(" - " + histories.get(i));
    				}
    			}
    			System.out.println("=".repeat(60));
    			System.out.println("Press 'q' to go back | press 'w/s' to navigate");

    			key = (char) readKey();
    			if (key == 'w') {
    				clearScreen();
    				num -= 1;
    				if (num < 0) num = 0;
    			}
    			else if (key == 's') {
    				clearScreen();
    				num += 1;
    				if (num > histories.size() - 6) num = histories.size() - 5;
    			}
            }
            clearScreen();
            if (key == 'q') break;
        }
    }

    public static void main(String[] args) throws IOException {
        Account current_acc;
        Scanner input = new Scanner(System.in);
        BankService bankService = new BankService(input);

        String str;
        int key;

        clearScreen();
        while (true) {
            while (true) {
                System.out.println("Enter an option below: ");
                System.out.println("1 - Sign in");
                System.out.println("2 - Log in");
                System.out.println("3 - Exit");
                System.out.print("Your Option: ");
                key = (char) readKey();
                if (key >= '1' && key <= '3') {
                    break;
                } else {clearScreen(); System.out.println("Invalid Option!!\n" + "=".repeat(30));}
            }
            switch (key) {
                case '1' -> bankService.Register(getCurrentTime());
                case '2' -> {
                    current_acc = bankService.LogIn();
                    if (current_acc != null) {
                        clearScreen();
                        System.out.println("Welcome! " + current_acc.getName());
                        System.out.println("=".repeat(30));
                        boolean running = true;
                        while (running) {
                            while (true) {
                                System.out.println("Enter an Option below: ");
                                System.out.println("0 - View Profile");
                                System.out.println("1 - Check Balance");
                                System.out.println("2 - Withdraw Money");
                                System.out.println("3 - Deposit Money");
                                System.out.println("4 - Transfer Money");
                                System.out.println("5 - History");
                                System.out.println("6 - Log out");
                                System.out.print("Your Option: ");
                                key = (char) readKey();
                                if (key >= '0' && key <= '6') {
                                    break;
                                } else {clearScreen(); System.out.println("Invalid Option!!\n" + "=".repeat(30));}
                            }
                            switch (key) {
                                case '0' -> {
                                    clearScreen();
                                    while (true) {
                                        System.out.println("Here are your information:");
                                        System.out.println("=".repeat(30));
                                        System.out.println("Name: " + current_acc.getName());
                                        System.out.println("Account Number: " + current_acc.getAccountNumber());
                                        System.out.println("Balance: " + current_acc.getBalance());
                                        System.out.println("Password: " + current_acc.getPassword());
                                        System.out.println("=".repeat(30));
                                        System.out.println("1 - Change Name");
                                        System.out.println("2 - Change Password");
                                        System.out.println("3 - Go back");
                                        System.out.print("Your Option: ");
                                        key = (char) readKey();
                                        switch (key) {
                                            case '1' -> {
                                                clearScreen();
                                                System.out.print("Enter Your New Name (Enter 'q' to Go back): ");
                                                str = input.nextLine();
                                                if (str.equals("q") || str.equals("Q")) break;

                                                bankService.changeName(current_acc, str);

                                                clearScreen();
                                                System.out.println("Your new name has been changed!");
                                                System.out.println("=".repeat(30));
                                            }
                                            case '2' -> {
                                                clearScreen();
                                                while  (true) {
                                                    System.out.print("Enter Your Current Password (Enter 'q' to Go back): ");
                                                    str = input.nextLine();
                                                    if  (str.equals("q") || str.equals("Q")) break;
                                                    else if (current_acc.getPassword().equals(str)) break;
                                                    else {
                                                        clearScreen();
                                                        System.out.println("Incorrect Password!!!");
                                                        System.out.println("=".repeat(30));
                                                    }
                                                }

                                                if (str.equals("q") || str.equals("Q")) break;

                                                clearScreen();
                                                System.out.println("Please Enter yourNew Password (Enter 'q' to Go back): ");
                                                str = input.nextLine();

                                                if (str.equals("q") || str.equals("Q")) break;

                                                bankService.changePassword(current_acc, str);

                                                clearScreen();
                                                System.out.println("Your current password has been changed!");
                                                System.out.println("=".repeat(30));
                                            }
                                            case '3' -> {clearScreen();}
                                            default -> System.out.println("Invalid Option!!\n" + "=".repeat(30));
                                        }
                                        clearScreen();
                                        break;
                                    }
                                }
                                case '1' -> {
                                    clearScreen();
                                    System.out.println("=".repeat(30));
                                    System.out.println("Your Current Balance: " + current_acc.getBalance());
                                    System.out.println("=".repeat(30));
                                }
                                case '2' -> {
                                	double amount;

                                    clearScreen();
                                    while (true) {
                                        System.out.println("=".repeat(30));
                                        System.out.println("Your Balance: " + current_acc.getBalance());
                                        System.out.print("Enter Amount to Withdraw (Enter 'q' to Exit): ");
                                        str = input.nextLine();
                                        if (str.equals("q") || str.equals("Q")) {clearScreen(); break;}
                                        else if (bankService.longCheck(str) != 0) {
                                        	amount = bankService.doubleCheck(str);
                                            if (bankService.Withdraw(current_acc, amount, getCurrentTime())) {
                                                clearScreen();
                                                System.out.println("Withdraw Successful!!");
                                                System.out.println("=".repeat(30));
                                                break;
                                            } else {clearScreen(); System.out.println("Invalid Amount!");}
                                        } else {clearScreen();System.out.println("Invalid Input!");}
                                    }
                                }
                                case '3' -> {
                                	double amount;

                                    clearScreen();
                                    while (true) {
                                        System.out.println("=".repeat(30));
                                        System.out.println("Your Balance: " + current_acc.getBalance());
                                        System.out.print("Enter Amount to Deposit (Enter 'q' to Exit): ");
                                        str = input.nextLine();
                                        if (str.equals("q") || str.equals("Q")) {clearScreen(); break;}
                                        else if (bankService.longCheck(str) != 0) {
                                            amount = bankService.doubleCheck(str);
                                            if (bankService.Deposit(current_acc, amount, getCurrentTime())) {
                                                clearScreen();
                                                System.out.println("Deposit Successful!!");
                                                System.out.println("=".repeat(30));
                                                break;
                                            } else {clearScreen(); System.out.println("Invalid Amount!");}
                                        }
                                        else {clearScreen(); System.out.println("Invalid Input!!");}
                                    }
                                }
                                case '4' -> {
                                    long account_num;
                                    double amount;

                                    clearScreen();
                                    while (true) {
                                        System.out.print("Enter Receiver Account Number (Enter 'q' to go back): ");
                                        str = input.nextLine();
                                        account_num = bankService.longCheck(str);
                                        if (str.equals("q") || str.equals("Q")) {
                                            clearScreen();
                                            break;
                                        } else {
                                            if (account_num == 0) {
                                                clearScreen();
                                                System.out.println("Invalid Input!\n" + "=".repeat(30));
                                            } else break;
                                        }
                                    }

                                    if (str.equals("q") || str.equals("Q")) break;

                                    clearScreen();
                                    while (true) {
                                        System.out.println("Your Balance: " + current_acc.getBalance());
                                        System.out.print("Enter Amount to Transfer (Enter 'q' to Exit): ");
                                        str = input.nextLine();
                                        amount = bankService.doubleCheck(str);
                                        if (str.equals("q") || str.equals("Q")) {
                                            clearScreen();
                                            break;
                                        } else {
                                            if (amount <= 0) {
                                                clearScreen();
                                                System.out.println("Invalid Input!\n" + "=".repeat(30));
                                            } else {
                                                break;
                                            }
                                        }
                                    }

                                    if (str.equals("q") || str.equals("Q")) break;

                                    if (bankService.Transfer(current_acc, account_num, amount, getCurrentTime())) {
                                        clearScreen();
                                        System.out.println("Transaction Confirmation:");
                                        System.out.println("=".repeat(30));
                                        System.out.println("Receiver Account Number: " + account_num);
                                        System.out.println("Transfer Amount: " + amount);
                                        System.out.println("=".repeat(30));
                                        System.out.print("Press 'Enter' to confirm...");
                                        input.nextLine();
                                        clearScreen();
                                    } else {
                                        clearScreen();
                                        System.out.println("Transfer Unsuccessful!! Please Try Again!");
                                        System.out.println("=".repeat(30));
                                    }
                                }
                                case '5' -> historyDisplay(current_acc.getHistory());
                                case '6' -> {
                                    clearScreen();
                                    System.out.println("Logged out!!");
                                    System.out.println("=".repeat(30));
                                    running = false;
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
                case '3' -> {
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
