package efan.test.service;

import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import efan.service.EpanBankService;
import efan.service.EpanBankServiceImpl;

public class EpanBankServiceImplTest {
    public static void main(String[] args) {
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
        EpanBankService epanBankService = new EpanBankServiceImpl(epanBankRepository);

        epanBankService.addMoney("putri");
    }
}
