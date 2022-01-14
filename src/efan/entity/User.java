package efan.entity;

public class User {
    
    private String username;

    private int money;

    private int amountOfMoneyAdded;

    private int amountOfMoneyWithdraw;

    public User(){
    }

    public User(String username){
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    public int getAmountOfMoneyAdded() {
        return this.amountOfMoneyAdded;
    }

    public void setAmountOfMoneyAdded(int amountOfMoneyAdded) {
        this.amountOfMoneyAdded = amountOfMoneyAdded;
    }

    public int getAmountOfMoneyWithdraw() {
        return this.amountOfMoneyWithdraw;
    }

    public void setAmountOfMoneyWithdraw(int amountOfMoneyWithdraw) {
        this.amountOfMoneyWithdraw = amountOfMoneyWithdraw;
    }
}
