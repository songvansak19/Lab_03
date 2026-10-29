

import java.util.ArrayList;

public class AuthService {
    private ArrayList <Account> accounts = new ArrayList<>();
    public AuthService(ArrayList <Account> accounts) {
        this.accounts = accounts;
    }

    public Account Authenticate(String name, int account_num) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == account_num && account.getName().equals(name)) {
                return account;
            }
        }
        return null;
    }
}
