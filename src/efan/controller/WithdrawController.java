package efan.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

public class WithdrawController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

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
}
