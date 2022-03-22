package efan.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

import efan.DB.DatabaseUtil;
import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
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

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label forgotPasswordBtn;
    
    @FXML
    private ProgressIndicator loadingIndicator;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("membuka main");
        loadingIndicator.setVisible(false);
        ExecutorService executorService = EpanBankRepositoryImpl.getExecutorServiceInstace();
        System.out.println("executor thread is used from init : " + executorService);
    }

    public void mouseHandler(MouseEvent event){
        if(event.getSource() == btnClose){
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
            System.exit(0);
        } else if (event.getSource() == btnMinimize){
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setIconified(true);
        } else if (event.getSource() == forgotPasswordBtn){
            ScreenPageController.goToForgotPassword(event);
        }
    }

    public void login(ActionEvent event) {

        final String username = usernameTextField.getText();
        final String password = passwordField.getText();

        ExecutorService executorService = EpanBankRepositoryImpl.getExecutorServiceInstace();
        DatabaseUtil dataSource = DatabaseUtil.getInstance();
        EpanBankRepositoryImpl epanBankRepository = new EpanBankRepositoryImpl(dataSource);

        EpanBankRepositoryImpl.IsAvailableAccountTask isAvailableAccountTask = epanBankRepository.new IsAvailableAccountTask();

        EpanBankRepositoryImpl.LoginCustomerTask loginCustomerTask = epanBankRepository.new LoginCustomerTask(username, password);

        loginCustomerTask.setOnRunning(t -> {

            loginButton.disableProperty().bind(loginCustomerTask.runningProperty());

            loadingIndicator.visibleProperty().bind(loginCustomerTask.runningProperty());

            loadingIndicator.progressProperty().bind(loginCustomerTask.progressProperty());
        });
        
        if(!username.isEmpty() && !password.isEmpty()){

            loginCustomerTask.setOnSucceeded(t -> {
    
                if(loginCustomerTask.getValue()){ScreenPageController.goToMainmenu(event);}
                else {loginFailLabel.setText("login failed");}
                EpanBankRepositoryImpl.resetLock();
            });

                executorService.submit(loginCustomerTask);
                executorService.submit(isAvailableAccountTask);
            } else {loginFailLabel.setText("login failed");}
    }

    public void showRegister(ActionEvent event){ScreenPageController.goToRegister(event);}
}