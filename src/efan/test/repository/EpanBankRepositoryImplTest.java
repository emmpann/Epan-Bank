package efan.test.repository;

import java.util.Random;

import efan.model.Account;
import efan.model.User;
import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;

public class EpanBankRepositoryImplTest {


    public static void main(String[] args) {

        

        //testIsAvailableAccount();

        // new User("minyak", "tmpiq", "email", 29).login();
        // User a = User.getUserInstance();
        // System.out.println(a.getUsername());

        //testOpenAccount();

        // Random randNumber = new Random();
        // String accountNumber = Integer.toString(randNumber.nextInt(10));
        
        // for (int i = 0; i < 9; i++) {
        //     accountNumber += Integer.toString(randNumber.nextInt(10));
        // }
        // System.out.println(accountNumber);


        //testAddAccount();
        //testUpdate();
        //testGetMoney();
    }

    // public static void testAddAccount(){
    //     EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
    //     epanBankRepository.addAccount("efan");
    // }

    // // public static void testCheckUser(){
    // //     EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
    // //     System.out.println(epanBankRepository.checkUser("seppler"));
    // // }

    // public static void testUpdate(){
    //     EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
    //     UserSession.setMoney(4500);
    //     UserSession.setUsername("yantoks");
    //     epanBankRepository.update();
    // }

    // public static void testGetMoney(){
    //     EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
    //     UserSession.setUsername("yantok");
    //     epanBankRepository.getMoney();
    // }

    public static void testTransfer(){
        
        Account target = new Account();
    }

    // public static void testIsAvailableAccount(){
    //     DBUtils dataSource = new DBUtils();
    //     EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl(dataSource);
    //     new User("minyak", "tmpiq", "email", 29).login();
    //     System.out.println(epanBankRepository.isAvailableAccount());

    // }

    // public static void testOpenAccount(){
    //     DBUtils dataSource = new DBUtils();
    //     EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl(dataSource);
    //     Account newAcc = new Account("Kiagus M Efan Fitriyan", "jl rama raya rt 04 no 09", 123, "29", 123);
    //     epanBankRepository.openAccount(newAcc);
    // }

}
