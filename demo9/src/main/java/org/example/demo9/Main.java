package org.example.demo9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demo9.model.Database;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Database.getDatabase().makeConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(""));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("game");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Database.getDatabase().finish();
    }

    public static void main(String[] args) {
        launch();
    }
}