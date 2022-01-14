package efan.test.repository;

import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import efan.session.UserSession;

public class EpanBankRepositoryImplTest {
    public static void main(String[] args) {
        //testAddAccount();
        //testUpdate();
        testGetMoney();
    }

    public static void testAddAccount(){
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
        epanBankRepository.addAccount("efan");
    }

    public static void testCheckUser(){
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
        System.out.println(epanBankRepository.checkUser("seppler"));
    }

    public static void testUpdate(){
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
        UserSession.setMoney(4500);
        UserSession.setUsername("yantoks");
        epanBankRepository.update();
    }

    public static void testGetMoney(){
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
        UserSession.setUsername("yantok");
        epanBankRepository.getMoney();
    }
}
