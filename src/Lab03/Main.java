package Lab03;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd | HH:mm");

    public static String getCurrentTime() {
        return LocalDateTime.now().format(formatter);
    }

    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
    }

    public static void main(String[] args) {
        Account current_acc;
        Scanner input = new Scanner(System.in);
        BankService bankService = new BankService(input);

        while (true) {
            String string;
            while (true) { 
                System.out.println("Enter an option below: ");
                System.out.println("1 - Sign in");
                System.out.println("2 - Log in");
                System.out.println("3 - Exit");
                System.out.print("Your Option: ");
                string = input.nextLine();
                if (bankService.longCheck(string) >= 1 && bankService.longCheck(string) <= 3) {
                    break;
                } else {clearScreen(); System.out.println("Invalid Option!!\n" + "=".repeat(30));}
            }
            int option = (int) bankService.longCheck(string);
            switch (option) {
                case 1 -> bankService.Register(getCurrentTime());
                case 2 -> {
                    current_acc = bankService.LogIn();
                    if (current_acc != null) {
                        clearScreen();
                        System.out.println("Welcome! " + current_acc.getName());
                        System.out.println("=".repeat(30));
                        boolean running = true;
                        while (running) {
                            while (true) {
                                System.out.println("Enter an Option below: ");
                                System.out.println("1 - Check Balance");
                                System.out.println("2 - Withdraw Money");
                                System.out.println("3 - Deposit Money");
                                System.out.println("4 - Transfer Money");
                                System.out.println("5 - History");
                                System.out.println("6 - Log out");
                                System.out.print("Your Option: ");
                                string = input.nextLine();
                                if (bankService.longCheck(string) >= 1 && bankService.longCheck(string) <= 6) {
                                    break;
                                } else {clearScreen(); System.out.println("Invalid Option!!\n" + "=".repeat(30));}
                            }
                            option = (int) bankService.longCheck(string);
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
                                        System.out.print("Enter Amount to Withdraw (Enter 'q' to Exit): ");
                                        String str = input.nextLine();
                                        if (str.equals("q") || str.equals("Q")) {clearScreen(); break;}
                                        else if (bankService.longCheck(str) != 0) {
                                            double amount = bankService.doubleCheck(str);
                                            if (bankService.Withdraw(current_acc, amount, getCurrentTime())) {
                                                clearScreen();
                                                System.out.println("Withdraw Successful!!");
                                                System.out.println("=".repeat(30));
                                                break;
                                            } else {clearScreen(); System.out.println("Invalid Amount!");}
                                        } else {clearScreen();System.out.println("Invalid Input!");}
                                    }
                                    break;
                                }
                                case 3 -> {
                                    clearScreen();
                                    while (true) {
                                        System.out.println("=".repeat(30));
                                        System.out.println("Your Balance: " + current_acc.getBalance());
                                        System.out.print("Enter Amount to Deposit (Enter 'q' to Exit): ");
                                        String str = input.nextLine();
                                        if (str.equals("q") || str.equals("Q")) {clearScreen(); break;}
                                        else if (bankService.longCheck(str) != 0) {
                                            double amount = bankService.doubleCheck(str);
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
                                case 4 -> {
                                    long account_num;
                                    double amount;
                                    String str;

                                    clearScreen();
                                    while (true) {
                                        System.out.print("Enter Receiver Account Number (Enter 'q' to go back): ");
                                        str = input.nextLine();
                                        account_num = bankService.longCheck(str);
                                        if (str.equals("q") || str.equals("Q")) {clearScreen(); break;}
                                        else {clearScreen(); System.out.println("Invalid Input!\n" + "=".repeat(30));}
                                    
                                        clearScreen();
                                        System.out.println("=".repeat(30));
                                        System.out.println("Your Balance: " + current_acc.getBalance());
                                        System.out.print("Enter Amount to Transfer (Enter 'q' to Exit): ");
                                        str = input.nextLine();
                                        amount = bankService.doubleCheck(str);
                                        if (str.equals("q") || str.equals("Q")) {clearScreen(); break;}
                                        else if (bankService.Transfer(current_acc, account_num, amount, getCurrentTime())) {
                                            clearScreen();
                                            System.out.println("Transfer Successful!!");
                                            System.out.println("=".repeat(30));
                                            break;
                                        }
                                        else {clearScreen(); System.out.println("Transfer Failed! Please Check the Input Again!\n" + "=".repeat(30));}
                                    }

                                }
                                case 5 -> {
                                    clearScreen();
                                    System.out.println("=".repeat(60));
                                    System.out.println("Your History:");
                                    ArrayList <String> histories = current_acc.getHistory();
                                    for (String history : histories) {
                                        System.out.println(" - " + history);
                                    }
                                    System.out.println("=".repeat(60));
                                    break;
                                }
                                case 6 -> {
                                    clearScreen();
                                    System.out.println("Logged out!!");
                                    System.out.println("=".repeat(30));
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
