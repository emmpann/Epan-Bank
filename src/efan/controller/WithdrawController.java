package efan.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

import javax.swing.plaf.synth.SynthScrollBarUI;

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
import javafx.scene.Node;
import javafx.stage.Stage;

public class WithdrawController implements Initializable {

    @FXML
    private Label alertWrongInputLabel;

    @FXML
    private Label moneyLeftLabel;

    @FXML
    private TextField withdrawAmountField;
    
    @FXML
    private Label successWithdrawAlert;

    @FXML
    private Button withdrawButton;

    @FXML
    private ProgressIndicator loadingIndicator;


    private int moneyWithdrawn;
    
    private Stage stage;
    
    private Scene scene;
    
    private Parent root;
    
    private int moneyLeft = 0;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        if(!(Account.getAccountInstance() == null)){
            moneyLeft = Account.getAccountInstance().getBalance();
            moneyLeftLabel.setText(CurrencyNumber.currencyFormat(moneyLeft));
            loadingIndicator.setVisible(false);
        }else {
            System.out.println("you don't have bank account yet");
        }
    }

    public void backToMainmenu(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("../view/mainmenu.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("terdapat eror : " + e);
        }
    }

    public void withdrawMoney() {
        
        DatabaseUtil dataSource = DatabaseUtil.getInstance();
        EpanBankRepositoryImpl epanBankRepositoryImpl = new EpanBankRepositoryImpl(dataSource);

        ExecutorService executorService = EpanBankRepositoryImpl.getExecutorServiceInstace();

        if(!withdrawAmountField.getText().isEmpty()){
            try {

                EpanBankRepositoryImpl.RemoveMoneyTask removeMoneyTask = epanBankRepositoryImpl.new RemoveMoneyTask(Integer.parseInt(withdrawAmountField.getText()), Account.getAccountInstance());
                EpanBankRepositoryImpl.GetBalanceTask getBalanceTask = epanBankRepositoryImpl.new GetBalanceTask();

                removeMoneyTask.setOnRunning(t -> {
                    withdrawButton.disableProperty().bind(removeMoneyTask.runningProperty());
                    
                    loadingIndicator.visibleProperty().bind(removeMoneyTask.runningProperty());
                    
                    loadingIndicator.progressProperty().bind(removeMoneyTask.progressProperty());
                });

                getBalanceTask.setOnSucceeded(t -> {
                    if(removeMoneyTask.getValue()){
                        // Update curent balance amount
                        successWithdrawAlert.setText("Withdraw is Success");
                        moneyLeftLabel.setText(CurrencyNumber.currencyFormat(Account.getAccountInstance().getBalance()));
                        alertWrongInputLabel.setText("");
                        withdrawAmountField.setText("");
                    }
                    EpanBankRepositoryImpl.resetLock1();
                });

                executorService.submit(getBalanceTask);
                executorService.submit(removeMoneyTask);

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
        alertWrongInputLabel.setText("");
        withdrawAmountField.setText("");
    }
}
