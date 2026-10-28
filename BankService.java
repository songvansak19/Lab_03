
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BankService {

    private Scanner input;
    private Account current_acc;
    private Path path = Path.of("src/Users.json");
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private JsonArray arr = new JsonArray();

    public BankService(Scanner input) {
        this.input = input;
        try {
            if (!Files.exists(path)) {
                Files.writeString(path, gson.toJson(arr));
            } else {
                String reader = Files.readString(path);
                JsonElement element = JsonParser.parseString(reader);
                arr = element.getAsJsonArray();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " [Hint: AuthService.java]");
        }
    }

    public void clearScreen() {
        System.out.println("\033[H\033[2J");
    }

    public void SignIn(String currentTime) {
        clearScreen(); 
        System.out.print("Enter your name (Enter 'q' to go back): ");
        String name = input.nextLine();
        if (name.equals("q") || name.equals("Q")) {clearScreen(); return;}

        String str;
        clearScreen();
        while (true) {
            System.out.print("Enter your Account Number (Enter 'q' to go back): ");
            str = input.nextLine();
            if (str.equals("q") || str.equals("Q")) {clearScreen(); return;}
            else if (longCheck(str) != 0) break;
        }
        long accountNumber = longCheck(str);

        for (JsonElement element : arr) {
            JsonObject obj = element.getAsJsonObject();
            if (obj.get("name").getAsString().equals(name) || obj.get("account_num").getAsLong() == accountNumber) {
                clearScreen();
                System.out.println("This account is already existed!");
                System.out.println("=".repeat(30));
                return;
            }
        }

        clearScreen();
        while (true) { 
            System.out.print("Enter your Balance (Enter 'q' to go back): ");
            str = input.nextLine();
            if (str.equals("q") || str.equals("Q")) {clearScreen(); return;}
            else if (doubleCheck(str) != 0 || str.equals("0")) break;
        }
        double balance = Double.parseDouble(str);
        
        try {
            arr.add(gson.toJsonTree(new Account(name, accountNumber, balance, currentTime)));
            Files.writeString(path, gson.toJson(arr));
        } catch (Exception e) {
            System.out.println(e.getMessage() + " [Hint: AuthService.java]");
        }        

        clearScreen();
        System.out.println("Please log in to your account!");
        System.out.println("=".repeat(30));
    }

    public Account LogIn() {
        clearScreen();

        System.out.print("Enter your name (Enter 'q' to go back): ");
        String name = input.nextLine();
        if (name.equals("q") || name.equals("Q")) {clearScreen(); return null;}

        String str;
        clearScreen();
        while (true) {
            System.out.print("Enter your Account Number (Enter 'q' to go back): ");
            str = input.nextLine();
            if (str.equals("q") || str.equals("Q")) {clearScreen(); return null;}
            else if (longCheck(str) != 0) break;
        }
        long accountNumber = longCheck(str);

        for (JsonElement element : arr) {
            JsonObject obj = element.getAsJsonObject();
            if (obj.get("name").getAsString().equals(name) && obj.get("account_num").getAsLong() == accountNumber) {
                current_acc = gson.fromJson(obj, Account.class);
                return current_acc;
            }
        }

        clearScreen();
        System.out.println("Sorry! Invalid name or account number!");
        System.out.println("=".repeat(30));
        return null;
    }

    public void updateBalance() {
        for (JsonElement element : arr) {
            JsonObject obj = element.getAsJsonObject();
            if (obj.get("account_num").getAsLong() == current_acc.getAccountNumber()) {
                obj.addProperty("balance", current_acc.getBalance());
                obj.add("histories", gson.toJsonTree(current_acc.getHistory()));
                break;
            }
        }

        try {
            Files.writeString(path, gson.toJson(arr));
        } catch (Exception e) {
            System.out.println(e.getMessage() + " [Hint: AuthService.java]");
        }
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
            System.out.println("Invalid Input!");
            System.out.println("=".repeat(30));
            return 0;
        }
    }
}
