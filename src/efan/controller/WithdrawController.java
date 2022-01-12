package efan.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.stage.Stage;

public class WithdrawController implements Initializable{
    
    @FXML
    private Label moneyLeftLabel;

    private Stage stage;

    private Scene scene;

    private Parent root;

    private double moneyLeft = 150_000;

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

    public void withdrawMoney(){

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        moneyLeftLabel.setText(Double.toString(moneyLeft));
    }
}
