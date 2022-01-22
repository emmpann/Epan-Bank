package efan.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ScreenPageController {

    public static void showStage(ActionEvent event, String viewFile){
        try {
            Parent root = FXMLLoader.load(ScreenPageController.class.getResource(viewFile));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void goToMainmenu(ActionEvent event){
        showStage(event, "../view/mainmenu.fxml");
    }

    public static void goToLogin(ActionEvent event){
        showStage(event, "../view/login.fxml");
    }

    public static void goToRegister(ActionEvent event){
        showStage(event, "../view/register.fxml");
    }

    public static void goToDeposit(ActionEvent event){
        showStage(event, "../view/deposit.fxml");
    }

    public void goToWithdraw(ActionEvent event){
        showStage(event, "../view/mwithdraw.fxml");
    }


}
