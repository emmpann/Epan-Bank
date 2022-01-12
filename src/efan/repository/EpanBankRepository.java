package efan.repository;

public interface EpanBankRepository {
    
    void add(String username);

    void remove();

    void update();

    // mengecek apakah ada usernamenya di database
    boolean checkUser(String username);
}
