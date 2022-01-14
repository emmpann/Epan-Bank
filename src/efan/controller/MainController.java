package efan.controller;

import java.net.URL;
import java.util.ResourceBundle;

import efan.entity.User;
import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import efan.service.EpanBankService;
import efan.service.EpanBankServiceImpl;
import efan.session.UserSession;
import efan.test.UserSession.UserSessionTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainController implements Initializable{
    
    private Stage stage;
    
    private Scene scene;

    private Parent root;
    
    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameTextField;
    
    @FXML
    private Label wrongUsernameAlertLabel;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("membuka main");        
    }

    public void showMainmenu(ActionEvent event) {
        
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl();
        EpanBankService epanBankService = new EpanBankServiceImpl(epanBankRepository);
        
        epanBankService.setUserSession(usernameTextField.getText());

        if(epanBankService.accountCheck()){
            try {
                epanBankRepository.getMoney();
                root = FXMLLoader.load(getClass().getResource("../view/mainmenu.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (Exception e) {
                System.out.println("terdapat eror : " + e);
            }
        } else {
            wrongUsernameAlertLabel.setText("wrong username");
        }
    }

    public void showRegister(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("../view/register.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            System.out.println("terdapat eror : " + e);
        }
    }

}
