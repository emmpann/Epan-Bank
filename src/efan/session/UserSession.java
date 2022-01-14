package efan.session;

import efan.entity.User;

public class UserSession {
    
    static User user = new User();

    public static void setUsername(String username){
        user.setUsername(username);
    }

    public static String getUsername(){
        return user.getUsername();
    }

    public static void setMoney(int money){
        user.setMoney(money);
    }

    public static int getMoney(){
        return user.getMoney();
    }

    public static void setAmountOfMoneyAdded(int moneyDeposited){
        user.setAmountOfMoneyAdded(moneyDeposited);
    }

    public static int getAmountOfMoneyAdded(){
        return user.getAmountOfMoneyAdded();
    }

    public static void setAmountOfMoneyWithdraw(int moneyWitdrawn){
        user.setAmountOfMoneyWithdraw(moneyWitdrawn);
    }

    public static int getAmountOfMoneyWithdraw(){
        return user.getAmountOfMoneyWithdraw();
    }
}
