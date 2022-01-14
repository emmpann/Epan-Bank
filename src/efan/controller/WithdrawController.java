package efan.controller;

import java.net.URL;
import java.util.ResourceBundle;

import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import efan.service.EpanBankService;
import efan.service.EpanBankServiceImpl;
import efan.session.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

    private int moneyWithdrawn;

    private Stage stage;

    private Scene scene;

    private Parent root;

    private int moneyLeft = UserSession.getMoney();

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
        
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
        EpanBankService epanBankService = new EpanBankServiceImpl(epanBankRepository);
        if(!epanBankService.isEmptyTextField(withdrawAmountField.getText())){
            
            /**
             * convert string to number and checking the input
             */
            try {
                moneyWithdrawn = Integer.parseInt(withdrawAmountField.getText());
                UserSession.setAmountOfMoneyWithdraw(moneyWithdrawn);
                epanBankService.withdrawMoney();
                alertWrongInputLabel.setText("");
                withdrawAmountField.setText("");
            } catch (NumberFormatException e) {
                alertWrongInputLabel.setText("please enter the amount (number)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            alertWrongInputLabel.setText("please enter the amount");
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        moneyLeftLabel.setText(String.valueOf(moneyLeft));
    }
}
