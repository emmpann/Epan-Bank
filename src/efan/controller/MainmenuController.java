package efan.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

import efan.DB.DatabaseUtil;
import efan.model.Account;
import efan.model.User;
import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainmenuController implements Initializable{

    DatabaseUtil dataSource = DatabaseUtil.getInstance();

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private Label openAccountBtn;

    @FXML
    private Pane contentPane;

    @FXML
    public void backToMain(ActionEvent event){

        // Logout

        if(!(Account.getAccountInstance() == null)){
            System.out.println("memanggil clearAccount()");
            Account.getAccountInstance().clearAccount();
            User.getUserInstance().clearUser();
        }

        try {
            root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            System.out.println("terdapat eror : " + e);
        }
    }

    @FXML
    public void showAccount(ActionEvent event){

        if(!(Account.getAccountInstance() == null)){
            ScreenPageController.goToValidAccount(contentPane, event);
        } else {
            ScreenPageController.goToNotValidAccount(contentPane, event);
        }
    }

    @FXML
    public void showWithdraw(ActionEvent event){

        ScreenPageController.goToWithdraw(contentPane, event);
    }

    @FXML
    public void showDeposit(ActionEvent event){
        
        ScreenPageController.goToDeposit(contentPane, event);
    }

    @FXML
    void showTransfer(ActionEvent event) {

        ScreenPageController.goToTransfer(contentPane, event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        // ExecutorService executorService = EpanBankRepositoryImpl.getExecutorServiceInstace();

        // // Take account data
        // EpanBankRepositoryImpl epanBankRepository = new EpanBankRepositoryImpl(dataSource);
        // EpanBankRepositoryImpl.IsAvailableAccountTask isAvailableAccountTask = epanBankRepository.new IsAvailableAccountTask();
        // if(Account.getAccountInstance() == null){
        //     // take availble account
        //     executorService.submit(isAvailableAccountTask);
        //     //epanBankRepository.isAvailableAccount();
        // }

        //epanBankService.getMoneyStatus();
        
        // Set profile picture
        
    }
    
}