package efan.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

import efan.DB.DatabaseUtil;
import efan.model.Account;
import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import efan.util.CurrencyNumber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class DepositController implements Initializable {
    
    private Stage stage;

    private Scene scene;

    private Parent root;
    
    private int moneyDeposited;
    
    @FXML
    private Label successDepositAlert;

    @FXML
    private Label moneyLeftLabel;

    @FXML
    private TextField depositMoneyField;

    @FXML
    private Label alertWrongInputLabel;

    @FXML
    private Button depositButton;

    @FXML
    private ProgressIndicator loadingIndicator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadingIndicator.setVisible(false);

        if((Account.getAccountInstance() != null)){
            moneyLeftLabel.setText(CurrencyNumber.currencyFormat(Account.getAccountInstance().getBalance()));
        }
    }

    public void backToMainmenu(ActionEvent event){

        try {
            root = FXMLLoader.load(getClass().getResource("../view/mainmenu.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            System.out.println("terdapat eror : " + e);
        }
    }

    public void depositMoney(){
        
        DatabaseUtil dataSource = DatabaseUtil.getInstance();
        EpanBankRepositoryImpl epanBankRepositoryImpl = new EpanBankRepositoryImpl(dataSource);

        ExecutorService executorService = EpanBankRepositoryImpl.getExecutorServiceInstace();

        if(!depositMoneyField.getText().isEmpty()){
            try {

                EpanBankRepositoryImpl.AddMoneyTask addMoneyTask = epanBankRepositoryImpl.new AddMoneyTask(Integer.parseInt(depositMoneyField.getText()), Account.getAccountInstance());
                EpanBankRepositoryImpl.GetBalanceTask getBalanceTask = epanBankRepositoryImpl.new GetBalanceTask();

                addMoneyTask.setOnRunning(t -> {
                    depositButton.disableProperty().bind(addMoneyTask.runningProperty());
                    
                    loadingIndicator.visibleProperty().bind(addMoneyTask.runningProperty());
                    
                    loadingIndicator.progressProperty().bind(addMoneyTask.progressProperty());
                });

                getBalanceTask.setOnSucceeded(t -> {
                    if(addMoneyTask.getValue()){
                        // Update current balance amount
                        successDepositAlert.setText("Deposit is Success");
                        
                        moneyLeftLabel.setText(CurrencyNumber.currencyFormat(Account.getAccountInstance().getBalance()));
                        alertWrongInputLabel.setText("");
                        depositMoneyField.setText("");
                    }
                    EpanBankRepositoryImpl.resetLock1();
                });
                
                executorService.submit(getBalanceTask);
                executorService.submit(addMoneyTask);

            } catch (NumberFormatException e) {
                alertWrongInputLabel.setText("please enter the amount (number)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            alertWrongInputLabel.setText("please enter the amount");
        }
    }


    public void clearField() {
        depositMoneyField.setText("");
        alertWrongInputLabel.setText("");
    }
}
