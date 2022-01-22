package efan.repository;

import efan.model.User;

public interface EpanBankRepository {
    
    boolean addAccount(User newUser);
    
    void addMoney();

    void removeMoney();

    void update();

    int getMoney();

    // mengecek apakah ada accountnya di database

    boolean loginAccount(String username, String password);

    boolean availableAccount(User newUser);

    //controlcheck

}