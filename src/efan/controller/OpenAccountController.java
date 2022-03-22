package efan.controller;

import java.util.Random;

import efan.DB.DatabaseUtil;
import efan.model.Account;
import efan.model.User;
import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import javafx.event.ActionEvent;
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

    @FXML
    public void openAccount(ActionEvent event){

        // Generate account number
        Random randNumber = new Random();
        String accountNumber = Integer.toString(randNumber.nextInt(10));
        
        for (int i = 0; i < 11; i++) {
            accountNumber += Integer.toString(randNumber.nextInt(10));
        }

        DatabaseUtil dataSource = DatabaseUtil.getInstance();
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl(dataSource);

        User customer = User.getUserInstance();

        // Create new account
        Account newAccount = new Account(frontNameField.getText() + " " + lastNameField.getText(), 
        addressField.getText(), Integer.parseInt(pinNumberField.getText()), accountNumber, customer.getId());
        
        epanBankRepository.openAccount(newAccount);
        
        // Add instance to currently account
        if(Account.getAccountInstance() == null){
            // take account
            epanBankRepository.isAvailableAccount();
            
            ScreenPageController.goToMainmenu(event);
        }
    }

}
