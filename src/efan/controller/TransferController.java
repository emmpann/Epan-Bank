package efan.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

import efan.DB.DatabaseUtil;
import efan.model.Account;
import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import efan.test.model.AccountTest;
import efan.util.CurrencyNumber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class TransferController implements Initializable {

    @FXML
    private TextField accountNumberField;

    @FXML
    private TextField balanceField;

    @FXML
    private Label moneyLeftLabel;

    @FXML
    private Label successTransferAlert;

    @FXML
    private Button transferButton;
    
    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    public void transferBtn(ActionEvent event) {
        
        DatabaseUtil dataSource = DatabaseUtil.getInstance(); 
        EpanBankRepositoryImpl epanBankRepositoryImpl = new EpanBankRepositoryImpl(dataSource);
        
        ExecutorService executorService = EpanBankRepositoryImpl.getExecutorServiceInstace();

        Account accountNumTarget = new Account();

        if((!(balanceField.getText().isBlank())) && (!(accountNumberField.getText().isBlank()))){
            accountNumTarget.setAccountNumber(accountNumberField.getText());
            EpanBankRepositoryImpl.AddMoneyTask addMoneyTask = epanBankRepositoryImpl.new AddMoneyTask(Integer.parseInt(balanceField.getText()), accountNumTarget);
            EpanBankRepositoryImpl.RemoveMoneyTask removeMoneyTask = epanBankRepositoryImpl.new RemoveMoneyTask(Integer.parseInt(balanceField.getText()), Account.getAccountInstance());
            EpanBankRepositoryImpl.GetBalanceTask getBalanceTask = epanBankRepositoryImpl.new GetBalanceTask();
        
            removeMoneyTask.setOnRunning(t -> {
                transferButton.disableProperty().bind(removeMoneyTask.runningProperty());
                
                loadingIndicator.visibleProperty().bind(removeMoneyTask.runningProperty());
                
                loadingIndicator.progressProperty().bind(removeMoneyTask.progressProperty());
            });
            
            getBalanceTask.setOnSucceeded(t -> {
                
                if(removeMoneyTask.getValue()){
                    successTransferAlert.setText("Transfer is succes");
                    System.out.println(Account.getAccountInstance().getBalance());
                    moneyLeftLabel.setText(CurrencyNumber.currencyFormat(Account.getAccountInstance().getBalance()));
                    clearField();
                } else {
                    System.out.println("transfer gagal");
                }
                
                EpanBankRepositoryImpl.resetLock1();
            });

            // removeMoneyTask.setOnSucceeded(t -> {
                
            //     if(removeMoneyTask.getValue()){
            //         successTransferAlert.setText("Transfer is succes");
            //         moneyLeftLabel.setText(CurrencyNumber.currencyFormat(Account.getAccountInstance().getBalance()));
            //         clearField();
            //     } else {
            //         System.out.println("transfer gagal");
            //     }
                
            //     EpanBankRepositoryImpl.resetLock1();
            // });
            
            executorService.submit(addMoneyTask);
            executorService.submit(getBalanceTask);
            executorService.submit(removeMoneyTask);
        } else {
            System.out.println("field blank");
        }
        

        // if(epanBankRepositoryImpl.transferMoney(Integer.parseInt(balanceField.getText()), Account.getAccountInstance(), accountNumTarget)){
            //     epanBankRepositoryImpl.getBalance();
            //     successTransferAlert.setText("Transfer is succes");
            //     moneyLeftLabel.setText(CurrencyNumber.currencyFormat(Account.getAccountInstance().getBalance()));
            //     clearField();
            // } else {
            //     System.out.println("transfer gagal");
            // }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!(Account.getAccountInstance() == null)){
            moneyLeftLabel.setText(CurrencyNumber.currencyFormat(Account.getAccountInstance().getBalance()));
            loadingIndicator.setVisible(false);
        
        }else {
            System.out.println("you don't have bank account yet");
        }
    }

    private void clearField() {
        accountNumberField.setText("");
        balanceField.setText("");
    }
}
