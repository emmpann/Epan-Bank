package efan.repository;

import efan.DB.DBTask;
import efan.model.Account;
import efan.model.User;

public interface EpanBankRepository {
    
    public static class inner{
        final int umur = 12;
    }

    boolean addCustomer(User newUser);
    
    boolean addMoney(int balanceAdded, Account account);

    boolean removeMoney(int balanceRemoved, Account account);

    boolean transferMoney(int money, Account account, Account a);

    void update(); 

    int getBalance();

    // mengecek apakah ada accountnya di database

    boolean loginCustomer(String username, String password);

    boolean isAvailableCustomer(User newUser);

    boolean isAvailableAccount();

    boolean openAccount(Account newAccount);

    Account getAccountInfo(int id);

    //controlcheck

}