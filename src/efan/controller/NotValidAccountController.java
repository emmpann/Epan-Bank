package efan.controller;

import java.net.URL;
import java.util.ResourceBundle;

import efan.repository.EpanBankRepository;
import efan.repository.EpanBankRepositoryImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NotValidAccountController implements Initializable{

    @FXML
    private Pane contentPane;

    @FXML
    private Label showOpenAccountBtn;

    public void showOpenAccount(MouseEvent event){

        if(event.getSource() == showOpenAccountBtn){
            try {
                Parent openaccountPane = FXMLLoader.load(getClass().getResource("../view/openaccount.fxml"));
                contentPane.getChildren().removeAll();
                contentPane.getChildren().setAll(openaccountPane);
            } catch (Exception e) {
                System.out.println("terdapat eror : " + e);
            }

            showOpenAccountBtn.setText("");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        

        System.out.println("memanggil init");
        
    }

}
