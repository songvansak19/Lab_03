
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Exercise5 {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd | HH:mm");

    public static String getCurrentTime() {
        return LocalDateTime.now().format(formatter);
    }

    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
    }

    public static void main(String[] args) {

        Account current_acc;

        AuthService authService = new AuthService();

        while (true) {
            Scanner input = new Scanner(System.in);
            String string;
            while (true) { 
                System.out.println("Enter an option below: ");
                System.out.println("1 - Sign in");
                System.out.println("2 - Log in");
                System.out.println("3 - Exit");
                System.out.print("Your Option: ");
                string = input.nextLine();
                if (authService.doubleCheck(string) >= 0 && authService.doubleCheck(string) <= 3) {
                    break;
                } else {clearScreen(); System.out.println("Invalid Option!!\n" + "=".repeat(30));}
            }
            int option = (int) authService.doubleCheck(string);
            switch (option) {
                case 1 -> authService.SignIn(getCurrentTime());
                case 2 -> {
                    current_acc = authService.LogIn();
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
                                System.out.println("4 - History");
                                System.out.println("5 - Log out");
                                System.out.print("Your Option: ");
                                string = input.nextLine();
                                if (option >= 0 && option <= 5) {
                                    break;
                                } else {clearScreen(); System.out.println("Invalid Option!!\n" + "=".repeat(30));}
                            }
                            option = (int) authService.doubleCheck(string);
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
                                        else if (authService.doubleCheck(str) != 0) {
                                            double amount = authService.doubleCheck(str);
                                            if (current_acc.Withdraw(amount, getCurrentTime())) {
                                                authService.updateBalance();
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
                                        else if (authService.doubleCheck(str) != 0) {
                                            double amount = authService.doubleCheck(str);
                                            if (current_acc.Deposit(amount, getCurrentTime())) {
                                                authService.updateBalance();
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
                                case 5 -> {
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
