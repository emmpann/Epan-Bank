package efan.test.service;

import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import efan.service.EpanBankService;
import efan.service.EpanBankServiceImpl;

public class EpanBankServiceImplTest {
    public static void main(String[] args) {
        accountCheckTest();
    }

    public static void accountCheckTest(){
        
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
        EpanBankService epanBankService = new EpanBankServiceImpl(epanBankRepository);
        System.out.println(epanBankService.accountCheck("yantok"));
        

    }

}
