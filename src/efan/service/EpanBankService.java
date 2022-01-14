package efan.service;

public interface EpanBankService {
    
    boolean createAccount(String username);

    void depositMoney();

    void withdrawMoney();

    boolean accountCheck(String username);

    boolean accountCheck();

    void setUserSession(String username);

    String getUsernameUserSession();

    void setUserSessionMoney(int money);

    int getUserSessionMoney();

    boolean isEmptyTextField(String username);
    
    boolean isEmptyUsernameRegister();

    // static boolean checkUser(){
    //     return false;
    // }
}
