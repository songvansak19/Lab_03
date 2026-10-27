
import java.util.Scanner;

public class Exercise5 {
    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
    }

    public static void main(String[] args) {

        Account current_acc;

        AuthService authService = new AuthService();

        while (true) {
            System.out.println("Enter an option below: ");
            System.out.println("1 - Sign in");
            System.out.println("2 - Log in");
            System.out.println("3 - Exit");
            System.out.print("Your Option: ");
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            switch (option) {
                case 1 ->
                    authService.SignIn();
                case 2 -> {
                    current_acc = authService.LogIn();
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
                                        System.out.print("Enter Amount to Withdraw (Enter '0' to Exit): ");
                                        double amount = input.nextDouble();
                                        if (current_acc.Withdraw(amount)) {
                                            authService.updateBalance();
                                            clearScreen();
                                            System.out.println("Withdraw Successful!!");
                                            break;
                                        } else if (amount == 0) break;
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
                                        System.out.print("Enter Amount to Deposit (Enter '0' to Exit): ");
                                        double amount = input.nextDouble();
                                        if (current_acc.Deposit(amount)) {
                                            authService.updateBalance();
                                            clearScreen();
                                            System.out.println("Deposit Successful!!");
                                            break;
                                        } else if (amount == 0) break;
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
