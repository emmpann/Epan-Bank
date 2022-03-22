package efan.model;

public class Account {
    
    private int id;

    private String fullName;

    private String address;

    private String accountNumber;
    
    private int balance;

    private int pin;

    private int userId;

    private static Account accountInstance;

    public Account(){};

    public Account(int id, String fullName, String address, int pin, String accountNumber, int balance, int userId){
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.pin = pin;
        this.accountNumber = accountNumber;
        this.userId = userId;
        this.balance = balance;
    }
    
    public Account(String fullName, String address, int pin, String accountNumber, int userId){
        this.fullName = fullName;
        this.address = address;
        this.pin = pin;
        this.accountNumber = accountNumber;
        this.userId = userId;
    }

    public void clearAccount(){
        accountInstance = null;
    }

    public void takeAccount(){
        accountInstance = this;
    }

    public static Account getAccountInstance(){
        return accountInstance;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPin() {
        return this.pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
