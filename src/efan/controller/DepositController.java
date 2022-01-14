package efan.controller;

import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import efan.service.EpanBankService;
import efan.service.EpanBankServiceImpl;
import efan.session.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class DepositController {
    
    private Stage stage;

    private Scene scene;

    private Parent root;
    
    private int moneyDeposited;

    @FXML
    private TextField depositMoneyField;

    @FXML
    private Label alertWrongInputLabel;

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
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
        EpanBankService epanBankService = new EpanBankServiceImpl(epanBankRepository);

        if(!epanBankService.isEmptyTextField(depositMoneyField.getText())){
            try {
                moneyDeposited = Integer.parseInt(depositMoneyField.getText());
                UserSession.setAmountOfMoneyAdded(moneyDeposited);
                epanBankService.depositMoney();
                alertWrongInputLabel.setText("");
                depositMoneyField.setText("");
            } catch (NumberFormatException e) {
                alertWrongInputLabel.setText("please enter the amount (number)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            alertWrongInputLabel.setText("please enter the amoung");
        }
    }
}
