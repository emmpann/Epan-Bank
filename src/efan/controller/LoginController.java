package efan.controller;

import java.net.URL;
import java.util.ResourceBundle;

import efan.DBUtils;
import efan.model.User;
import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LoginController implements Initializable{
    
    @FXML
    private ImageView btnMinimize;

    @FXML
    private ImageView btnClose;

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameTextField;
    
    @FXML
    private Label loginFailLabel;

    @FXML private PasswordField passwordField;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("membuka main");        
    }

    public void mouseHandler(MouseEvent event){
        if(event.getSource() == btnClose){
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
            System.exit(0);
        } else if (event.getSource() == btnMinimize){
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setIconified(true);
        }
    }

    public void login(ActionEvent event) {
        
        DBUtils dataSource = new DBUtils();
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl(dataSource);

        String username = "";
        String password = "";
        username = usernameTextField.getText();
        password = passwordField.getText();

        if(!username.isEmpty() && !password.isEmpty()){
            if(epanBankRepository.loginCustomer(username, password)){
                ScreenPageController.goToMainmenu(event);
            } else {
                loginFailLabel.setText("login failed");
            }
        } else {
            loginFailLabel.setText("login failed");
        }

        
    }

    public void showRegister(ActionEvent event){
        ScreenPageController.goToRegister(event);
    }

}
