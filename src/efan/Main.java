package efan;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("view/css/epanBank.css").toExternalForm());
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Epan Bank");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
    }
}