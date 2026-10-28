
import java.nio.file.Path;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.nio.file.Files;

public class AccountRepository {
    private Path path;
    private JsonArray accounts;

    public AccountRepository(Path path) {
        this.path = path;
        try {
            accounts = JsonParser.parseString(Files.readString(this.path)).getAsJsonArray();
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
}
