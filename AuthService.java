
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class AuthService {
    private ArrayList <Account> accounts = new ArrayList<>();

    public ArrayList <Account> loadAccounts() {
        Path path = Path.of("Users.json"); // Find Users.json file's path.
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Define the structure of Json.
        Type type = new TypeToken<ArrayList<Account>>() {}.getType(); // Tells java teh Target type (ArrayList<Account> is a Class)

        try {
            if (!Files.exists(path)) {
                Files.writeString(path, gson.toJson(accounts));
            } else {
                String json = Files.readString(path);
                accounts = gson.fromJson(json, type);
                Files.writeString(path, gson.toJson(accounts));
                return accounts;
            }
        } catch (Exception e) {
            System.out.println("Error occurred!!");
        }
        return null;
    }

    public Account Authenticate(String name, int account_num) {
        Path path = Path.of("Users.json");
        String Users;

        try {
            Users = Files.readString(path);
            System.out.println(Users);
        } catch (Exception e) {
            System.out.println("File not Found!");
        }
        for (Account account : accounts) {
            if (account.getAccountNumber() == account_num && account.getName().equals(name)) {
                return account;
            }
        }
        return null;
    }
}
