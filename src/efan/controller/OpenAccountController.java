package efan.controller;

import java.util.Random;

import efan.DBUtils;
import efan.model.Account;
import efan.model.User;
import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class OpenAccountController {
    
    @FXML
    private TextField addressField;

    @FXML
    private TextField frontNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField pinNumberField;

    public void openAccount(){

        // Generate account number
        Random randNumber = new Random();
        String accountNumber = Integer.toString(randNumber.nextInt(10));
        
        for (int i = 0; i < 11; i++) {
            accountNumber += Integer.toString(randNumber.nextInt(10));
        }

        DBUtils dataSource = new DBUtils();
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl(dataSource);

        User customer = User.getUserInstance();

        // Create instance
        Account newAccount = new Account(frontNameField.getText() + " " + lastNameField.getText(), 
        addressField.getText(), Integer.parseInt(pinNumberField.getText()), accountNumber, customer.getId());
        
        epanBankRepository.openAccount(newAccount);
        
    }

}
