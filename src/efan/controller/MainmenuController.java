package efan.controller;

import java.net.URL;
import java.util.ResourceBundle;

import efan.DBUtils;
import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainmenuController implements Initializable{
    
    private Stage stage;
    private Scene scene;
    private Parent root;

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

    public void showWithdraw(ActionEvent event){

        try {
            root = FXMLLoader.load(getClass().getResource("../view/withdraw.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            System.out.println("terdapat eror : " + e);
        }
    }

    public void showDeposit(ActionEvent event){
        
        try {
            root = FXMLLoader.load(getClass().getResource("../view/deposit.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            System.out.println("terdapat eror : " + e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DBUtils resource = new DBUtils();
        EpanBankRepository epanBankRepository = new EpanBankRepositoryImpl(resource);
        //epanBankService.getMoneyStatus();
        
    }
    
}