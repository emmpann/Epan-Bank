package efan.repository;

public interface EpanBankRepository {
    
    void addAccount(String username);
    
    void addMoney();

    void removeMoney();

    void update();

    int getMoney();

    // mengecek apakah ada usernamenya di database
    boolean checkUser(String username);
}
