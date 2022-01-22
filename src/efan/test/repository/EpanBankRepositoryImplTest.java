package efan.test.repository;

import efan.DBUtils;
import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;

public class EpanBankRepositoryImplTest {
    public static void main(String[] args) {
        testAccountCheck();
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

    public static void testAccountCheck(){
        DBUtils dataSource = new DBUtils();
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl(dataSource);

        System.out.println(epanBankRepository.loginAccount("yantok", "yantok1"));

    }

}
