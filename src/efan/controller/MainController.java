package efan.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainController {
    
    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private TextField usernameTextField;

    public void showMainmenu(ActionEvent event) {
        if(true){// ado didalem database){

            try {
                root = FXMLLoader.load(getClass().getResource("../view/mainmenu.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (Exception e) {
                System.out.println("terdapat error : " + e);
            }
        } else {
            System.out.println("cobo lagi");
        }
    }
}
