package efan;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import efan.DB.DatabaseThreadFactory;
import efan.repository.EpanBankRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application{
    public static void main(String[] args) {launch(args);}

    private static ExecutorService databaseExecutor;
 
    public static ExecutorService getExecutorService() {return databaseExecutor;}

    @Override
    public void init() throws Exception {EpanBankRepositoryImpl.setExecutorService(Executors.newFixedThreadPool(3, new DatabaseThreadFactory()));}

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("view/css/epanBank.css").toExternalForm());
        Image icon = new Image(getClass().getResourceAsStream("asset/icon.png"));
        stage.getIcons().add(icon);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Epan Bank");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}