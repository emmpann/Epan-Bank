package efan.controller;

import javax.swing.Action;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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

    public static void showStage(MouseEvent event, String viewFile){
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

    public static void showPane(ActionEvent event, Pane contentPane, String viewFile) {
        try {
            Parent openaccountPane = FXMLLoader.load(ScreenPageController.class.getResource(viewFile));
            contentPane.getChildren().removeAll();
            contentPane.getChildren().setAll(openaccountPane);
        } catch (Exception e) {
            System.out.println("terdapat eror : " + e);
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

    public void goToWithdraw(ActionEvent event){
        showStage(event, "../view/mwithdraw.fxml");
    }

    public static void goToForgotPassword(MouseEvent event){
        showStage(event, "../view/forgotpassword.fxml");
    }

    public static void goToValidAccount(Pane contentPane, ActionEvent event) {
        showPane(event, contentPane, "../view/validaccount.fxml");
    }

    public static void goToNotValidAccount(Pane contentPane, ActionEvent event) {
        showPane(event, contentPane, "../view/notvalidaccount.fxml");
    }

    public static void goToWithdraw(Pane contentPane, ActionEvent event) {
        showPane(event, contentPane, "../view/withdraw.fxml");
    }

    public static void goToDeposit(Pane contentPane, ActionEvent event) {
        showPane(event, contentPane, "../view/deposit.fxml");
    }

    public static void goToTransfer(Pane contentPane, ActionEvent event) {
        showPane(event, contentPane, "../view/transfer.fxml");
    }



}

/**
 * go to validaccount
 * go to withdraw
 * go to deposit
 * go to transfer
 */
