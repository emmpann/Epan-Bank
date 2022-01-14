package efan.controller;

import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import efan.service.EpanBankService;
import efan.service.EpanBankServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class RegisterController {

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private Button createAccountButton;

    @FXML
    private TextField newUsernameTextField;

    @FXML
    private Label notAvailableUsernameAlertLabel;

    @FXML
    public void createAccount(ActionEvent event) {
        
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
        EpanBankService epanBankService = new EpanBankServiceImpl(epanBankRepository);
        
        if(!epanBankService.isEmptyTextField(newUsernameTextField.getText())){
            if(epanBankService.createAccount(newUsernameTextField.getText())){
                try {
                    root = FXMLLoader.load(getClass().getResource("../view/main.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    
                } catch (Exception e) {
                    System.out.println("terdapat eror : " + e);
                }
            } else {
                notAvailableUsernameAlertLabel.setText("Username not available");
            }
        } else {
            notAvailableUsernameAlertLabel.setText("please enter a username");
        }
    }
}
