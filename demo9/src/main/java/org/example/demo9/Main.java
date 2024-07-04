package org.example.demo9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demo9.model.Database;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main extends Application {
    private static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        Main.stage=stage;
        Database.getDatabase().makeConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("StartPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 840, 460);
        stage.setTitle("game");
        stage.setScene(scene);
        stage.setResizable(false);
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

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }
}