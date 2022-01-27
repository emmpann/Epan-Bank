package efan.controller;

import java.net.URL;
import java.util.ResourceBundle;

import efan.DBUtils;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainmenuController implements Initializable{

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private Label openAccountBtn;

    @FXML
    private Pane contentPane;

    public void backToMain(ActionEvent event){

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

    public void showAccount(ActionEvent event){
        
        DBUtils dataSource = new DBUtils();
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl(dataSource);

        if(epanBankRepository.isAvailableAccount()){
            try {
                Parent openaccountPane = FXMLLoader.load(getClass().getResource("../view/validaccount.fxml"));
                contentPane.getChildren().removeAll();
                contentPane.getChildren().setAll(openaccountPane);
            } catch (Exception e) {
                System.out.println("terdapat eror : " + e);
            }
        } else {
            try {
                Parent openaccountPane = FXMLLoader.load(getClass().getResource("../view/notvalidaccount.fxml"));
                contentPane.getChildren().removeAll();
                contentPane.getChildren().setAll(openaccountPane);
            } catch (Exception e) {
                System.out.println("terdapat eror : " + e);
            }
        }
    }

    public void showWithdraw(ActionEvent event){

        try {
            Parent withdrawPane = FXMLLoader.load(getClass().getResource("../view/withdraw.fxml"));
            contentPane.getChildren().removeAll();
            contentPane.getChildren().setAll(withdrawPane);
        } catch (Exception e) {
            System.out.println("terdapat eror : " + e);
        }
    }

    public void showDeposit(ActionEvent event){
        
        try {
            Parent depositPane = FXMLLoader.load(getClass().getResource("../view/deposit.fxml"));
            contentPane.getChildren().removeAll();
            contentPane.getChildren().setAll(depositPane);
        } catch (Exception e) {
            System.out.println("terdapat eror : " + e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DBUtils resource = new DBUtils();
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl(resource);
        //epanBankService.getMoneyStatus();
        
        // Set profile picture
        
    }
    
}