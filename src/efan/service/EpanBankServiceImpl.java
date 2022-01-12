package efan.service;

import efan.repository.EpanBankRepository;

public class EpanBankServiceImpl implements EpanBankService{

    EpanBankRepository epanBankRepository;

    public EpanBankServiceImpl(EpanBankRepository epanBankRepository){
        this.epanBankRepository = epanBankRepository; 
    }

    @Override
    public void addMoney(String username) {
        epanBankRepository.add(username);
    }

    @Override
    public void removeMoney() {
        // TODO Auto-generated method stub
        
    }
    
}
