package Lab03;

import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.nio.file.Files;

public class AccountRepo {
    private final Path path;
    private JsonArray accounts = new JsonArray();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public AccountRepo(Path path) {
        this.path = path;
        try {
            if (!Files.exists(path)) {
                Files.writeString(path, gson.toJson(accounts));
            } else {
                String reader = Files.readString(path);
                JsonElement element = JsonParser.parseString(reader);
                accounts = element.getAsJsonArray();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " [Hint: AuthService.java]");
        }
    }

    public boolean findName(String name) {
        for (JsonElement element : accounts) {
            JsonObject obj = element.getAsJsonObject();
            if (obj.get("name").getAsString().equals(name)) return true;
        }
        return false;
    }

    public boolean findAccountNumber(long account_num) {
        for (JsonElement element : accounts) {
            JsonObject obj = element.getAsJsonObject();
            if (obj.get("account_num").getAsLong() == account_num) return true;
        }
        return false;
    }

    public Account getAccount(long account_num, String password) {
        for (JsonElement element : accounts) {
            JsonObject obj = element.getAsJsonObject();
            if (obj.get("account_num").getAsLong() == account_num &&
                obj.get("password").getAsString().equals(password)) 
                    return gson.fromJson(obj, Account.class);
        }
        return null;
    }

    public Account getAccount(long account_num) {
        for (JsonElement element : accounts) {
            JsonObject obj = element.getAsJsonObject();
            if (obj.get("account_num").getAsLong() == account_num) 
                return gson.fromJson(obj, Account.class);
        }
        return null;
    }

    public void addNew(Account new_account) {
        if (new_account != null) {
            accounts.add(gson.toJsonTree(new_account));
        }
        try {Files.writeString(path, gson.toJson(accounts));}
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void update(Account current_acc) { 
        for (JsonElement element : accounts) {
            JsonObject obj = element.getAsJsonObject();
            if (obj.get("account_num").getAsLong() == current_acc.getAccountNumber()) {
            	obj.add("history", gson.toJsonTree(current_acc.getHistory()));
            	obj.add("balance", gson.toJsonTree(current_acc.getBalance()));
                obj.addProperty("name", current_acc.getName());
                obj.addProperty("password", current_acc.getPassword());
                break;
            }
        }
        try {Files.writeString(path, gson.toJson(accounts));}
        catch (Exception e) {System.out.println(e.getMessage());}
    }
}
