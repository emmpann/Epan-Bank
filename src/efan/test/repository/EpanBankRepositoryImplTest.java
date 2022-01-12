package efan.test.repository;

import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;

public class EpanBankRepositoryImplTest {
    public static void main(String[] args) {

        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
        System.out.println(epanBankRepository.checkUser("putri"));

        epanBankRepository.add("efan");
    }
}
