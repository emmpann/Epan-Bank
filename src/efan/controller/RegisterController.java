package efan.controller;

import efan.DBUtils;
import efan.model.User;
import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {
    
    @FXML
    private Button createAccountButton;
    
    
    @FXML
    private TextField newUsernameTextField;
    
    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label usernameNotAvailableLabel;

    @FXML
    private Label emailNotAvailableLabel;

    @FXML
    private Label passwordErrorLabel;

    @FXML
    private Label confirmPasswordErrorLabel;

    @FXML
    public void createAccount(ActionEvent event) {

        confirmPasswordErrorLabel.setText("");
        passwordErrorLabel.setText("");
        usernameNotAvailableLabel.setText("");
        emailNotAvailableLabel.setText("");

        DBUtils resource = new DBUtils();
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl(resource);

        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        
        User newUser = new User(newUsernameTextField.getText(), passwordField.getText(), emailTextField.getText());

        if(!newUsernameTextField.getText().isEmpty()){
            if(!passwordField.getText().isEmpty()){
                if(password.equals(confirmPassword)){
                    if(!epanBankRepository.isAvailableCustomer(newUser)){
                        epanBankRepository.addCustomer(newUser);
                        ScreenPageController.goToLogin(event);
                    } else {
                        if(newUser.getUsername().equals(newUsernameTextField.getText())){
                            usernameNotAvailableLabel.setText("Username is not available");
                        } else if (newUser.getEmail().equals(emailTextField.getText())){
                            emailNotAvailableLabel.setText("Email is not Available");
                        }
                    }
                } else {
                    confirmPasswordErrorLabel.setText("error confirm password");
                }
            } else {
                passwordErrorLabel.setText("please enter password");
            }
        } else {
            usernameNotAvailableLabel.setText("please enter a username");
        }
    }

    @FXML
    public void backToMain(ActionEvent event){

        ScreenPageController.goToLogin(event);
    }
}
