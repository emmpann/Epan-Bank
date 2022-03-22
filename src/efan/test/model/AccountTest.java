package efan.test.model;

import efan.model.Account;

public class AccountTest {
    public static void main(String[] args) {
        accountInstanceTest();
    }    

    public static void accountInstanceTest(){

        new Account("fullName", "address", 123456, "null", 1).takeAccount();

        //System.out.println(Account.getAccountInstance() == null);

        if(!(Account.getAccountInstance() == null)){
            System.out.println("instance is contains");
        }
    }

}