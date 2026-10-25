
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class AuthService {
    private ArrayList <Account> accounts = new ArrayList<>();

    // ========================================================== //
    Path path = Path.of("Users.json"); // Find Users.json file's path.
    Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Define the structure of Json.
    Type type = new TypeToken<ArrayList<Account>>() {}.getType(); // Tells java the Target type (ArrayList<Account> is a Class)
    // ========================================================== //

    public AuthService() {
        try {
            if (!Files.exists(path)) {
                Files.writeString(path, gson.toJson(accounts));
            } else {
                String json = Files.readString(path);
                accounts = gson.fromJson(json, type);
                Files.writeString(path, gson.toJson(accounts));
            }
        } catch (Exception e) {
            System.out.println("ERROR from AuthService.java: Error occurred!!");
        }
    }

    public void clearScreen() {
        System.out.println("\033[H\033[2J");
    }

    public void SignIn() {
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.print("Enter your Account Number: ");
        int account_num = input.nextInt();
        System.out.print("Enter your Balance: ");
        double balance = input.nextDouble();
        accounts.add(new Account(name, account_num, balance));

        try {
            Files.writeString(path, gson.toJson(accounts));
        } catch (Exception e) {
            System.out.println("ERROR from AuthService.java: Unable to 'write/update file");
        }

        clearScreen();
        System.out.println("Please log in to your account!");
        System.out.println("=".repeat(30));
    }

    public Account LogIn() {
        clearScreen();

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.print("Enter your Account Number: ");
        int account_num = input.nextInt();

        for (Account account : accounts) {
            if (account.getAccountNumber() == account_num && account.getName().equals(name)) {
                return account;
            }
        }

        clearScreen();
        System.out.println("Sorry! Invalid name or account number!");
        System.out.println("=".repeat(30));
        return null;
    }
}
