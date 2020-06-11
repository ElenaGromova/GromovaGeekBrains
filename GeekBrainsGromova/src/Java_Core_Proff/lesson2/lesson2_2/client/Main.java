package Java_Core_Proff.lesson2.lesson2_2.client;

import Java_Core_Lesson7.client.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    Controller c;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("client.fxml"));
        //Parent root = getClass().getResource("client.fxml");
        //primaryStage.setTitloader.load(getClass().getResource("client.fxml"));
        //Parent root = loader.load(getClass().getResourceAsStream("client.fxml"));
        //Parent root = FXMLLoader.load(le("2k19SummerChat");
        primaryStage.setScene(new Scene(root, 600, 400));
        c = loader.getController();
        primaryStage.show();

        // primaryStage.setOnCloseRequest(event -> System.out.println("On Close"));
        primaryStage.setOnCloseRequest(event -> {
            c.Dispose();
            Platform.exit();
            System.exit(0);
        });

}

    public static void main(String[] args) {
        launch(args);
    }
}
