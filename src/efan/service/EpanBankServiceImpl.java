package efan.service;

import efan.repository.EpanBankRepository;
import efan.session.UserSession;

public class EpanBankServiceImpl implements EpanBankService {

    EpanBankRepository epanBankRepository;

    public EpanBankServiceImpl(EpanBankRepository epanBankRepository){
        this.epanBankRepository = epanBankRepository; 
    }

    @Override
    public boolean createAccount(String username) {
        
        if(!accountCheck(username)){
            epanBankRepository.addAccount(username);
            return true;
        }
        return false;
    }

    @Override
    public void withdrawMoney() {
        epanBankRepository.removeMoney();
    }

    @Override
    public void depositMoney() {
        epanBankRepository.addMoney();
    }
    /**
     * this Method overriding is used for check new account
     */
    public boolean accountCheck(String username) {
        return epanBankRepository.checkUser(username);
        
    }

    @Override
    public boolean accountCheck() {
        return epanBankRepository.checkUser(getUsernameUserSession());
        
    }
    
    @Override
    public void setUserSession(String username) {
        UserSession.setUsername(username);
    }

    @Override
    public String getUsernameUserSession(){
        return UserSession.getUsername();
    }

    @Override
    public void setUserSessionMoney(int money) {
        UserSession.setMoney(money);
        
    }

    @Override
    public int getUserSessionMoney() {
        return UserSession.getMoney();
    }

    // public int getAmountOfMoneyAdded() {                USER SESSION METHOD
    //     return this.amountOfMoneyAdded;
    // }

    // public void setAmountOfMoneyAdded(int amountOfMoneyAdded) {
    //     this.amountOfMoneyAdded = amountOfMoneyAdded;
    // }

    // public int getAmountOfMoneyWithdraw() {
    //     return this.amountOfMoneyWithdraw;
    // }

    // public void setAmountOfMoneyWithdraw(int amountOfMoneyWithdraw) {
    //     this.amountOfMoneyWithdraw = amountOfMoneyWithdraw;
    // }

    @Override
    public boolean isEmptyTextField(String username) {
        if(username.equals("")){
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmptyUsernameRegister() {
        // TODO Auto-generated method stub
        return false;
    }
}
