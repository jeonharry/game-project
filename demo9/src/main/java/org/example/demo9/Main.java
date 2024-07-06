package org.example.demo9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.example.demo9.model.Database;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.example.demo9.model.Map;
import org.example.demo9.model.towers.TowerPrices;

import java.util.ArrayList;


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
        try
        {
            stage.show();
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText(exception.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Database.getDatabase().finish();
    }

    public static void main(String[] args) {
        makeMaps();
        launch();
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }
    public static void makeMaps()
    {
        ArrayList<Double> end=new ArrayList<>(); end.add(737.0); end.add(334.0);
        ArrayList <Double> towerPlaces=new ArrayList<>();
        towerPlaces.add(286.0); towerPlaces.add(178.0);
        towerPlaces.add(296.0); towerPlaces.add(295.0);
        towerPlaces.add(295.0); towerPlaces.add(351.0);
        towerPlaces.add(503.0); towerPlaces.add(364.0);
        towerPlaces.add(617.0); towerPlaces.add(419.0);
        Map firstMap=new Map(250,towerPlaces,new ArrayList<>(),end,10);
        Database.getDatabase().getMaps().add(firstMap);
    }
}