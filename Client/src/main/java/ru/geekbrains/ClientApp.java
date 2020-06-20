package ru.geekbrains;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * JavaFX App
 */
public class ClientApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Сетевой чат");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        PrimaryController controller = loader.getController();
        stage.setOnHidden(e -> controller.shutdown());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}