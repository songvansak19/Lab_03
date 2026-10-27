
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AuthService {
    
    private Account current_acc;
    private Path path = Path.of("Users.json");
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private JsonArray arr = new JsonArray();

    public AuthService() {
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

    public void SignIn() {
        clearScreen(); 

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name (Enter 'q' to go back): ");
        String name = input.nextLine();
        if (name.equals("q") || name.equals("Q")) {clearScreen(); return;}
        System.out.print("Enter your Account Number (Enter '0' to go back): ");
        int account_num = input.nextInt();
        if (account_num == 0) {clearScreen(); return;}

        for (JsonElement element : arr) {
            JsonObject obj = element.getAsJsonObject();
            if (obj.get("name").getAsString().equals(name) || obj.get("account_num").getAsInt() == account_num) {
                clearScreen();
                System.out.println("This account is already existed!");
                System.out.println("=".repeat(30));
                return;
            }
        }

        System.out.print("Enter your Balance (Enter '0' to go back): ");
        double balance = input.nextDouble();
        if (balance == 0) {clearScreen(); return;}

        try {
            arr.add(gson.toJsonTree(new Account(name, account_num, balance)));
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

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name (Enter 'q' to go back): ");
        String name = input.nextLine();
        if (name.equals("q") || name.equals("Q")) {clearScreen(); return null;}
        System.out.print("Enter your Account Number (Enter '0' to go back): ");
        int account_num = input.nextInt();
        if (account_num == 0) {clearScreen(); return null;}

        for (JsonElement element : arr) {
            JsonObject obj = element.getAsJsonObject();
            if (obj.get("name").getAsString().equals(name) && obj.get("account_num").getAsInt() == account_num) {
                current_acc = new Account(name, account_num, obj.get("balance").getAsDouble());
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
            if (obj.get("account_num").getAsInt() == current_acc.getAccountNumber()) {
                obj.addProperty("balance", current_acc.getBalance());
                break;
            }
        }

        try {
            Files.writeString(path, gson.toJson(arr));
        } catch (Exception e) {
            System.out.println(e.getMessage() + " [Hint: AuthService.java]");
        }
    }
}
