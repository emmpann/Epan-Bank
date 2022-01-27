package efan.model;

import javafx.scene.image.Image;

public class User {
    
    private String username;

    private String password;

    private String email;

    private Image profilePicture;

    private int money;

    private int amountOfMoneyAdded;

    private int amountOfMoneyWithdraw;

    private static User userInstance;

    private int id;

    public static void print(){
        System.out.println(userInstance.getUsername());
        System.out.println(userInstance.getPassword());
        System.out.println(userInstance.getEmail());
    }

    public void login(){
        userInstance = this;
    }

    public static User getUserInstance(){
        return userInstance;
    }

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, int id){
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    public Image getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
