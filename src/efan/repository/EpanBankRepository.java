package efan.repository;

import efan.model.Account;
import efan.model.User;

public interface EpanBankRepository {
    
    boolean addCustomer(User newUser);
    
    void addMoney();

    void removeMoney();

    void update();

    int getMoney();

    // mengecek apakah ada accountnya di database

    boolean loginCustomer(String username, String password);

    boolean isAvailableCustomer(User newUser);

    boolean isAvailableAccount();

    boolean openAccount(Account newAccount);

    //controlcheck

}