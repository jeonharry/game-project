package org.example.demo9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.example.demo9.model.Database;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.example.demo9.model.Direction;
import org.example.demo9.model.Map;

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
        ArrayList <ArrayList<Double>> road=new ArrayList<>();
        road.add(makeSpot(356.0,21.0));
        road.add(makeSpot(347.0,176.0));
        road.add(makeSpot(266.0,225.0));
        road.add(makeSpot(206.0,278.0));
        road.add(makeSpot(218.0,360.0));
        road.add(makeSpot(337.0,388.0));
        road.add(makeSpot(409.0,382.0));
        road.add(makeSpot(498.0,404.0));
        road.add(makeSpot(611.0,326.0));
        road.add(makeSpot(737.0,334.0));
        ArrayList <Direction> directions=new ArrayList<>();
        directions.add(Direction.RIGHT);
        directions.add(Direction.LEFT);
        directions.add(Direction.LEFT);
        directions.add(Direction.DOWN);
        directions.add(Direction.UP);
        Map firstMap=new Map(250,towerPlaces,road,end,directions,5);
        Database.getDatabase().getMaps().add(firstMap);
    }
    public static ArrayList<Double> makeSpot(double x,double y)
    {
        ArrayList <Double> spot=new ArrayList<>();
        spot.add(x); spot.add(y);
        return spot;
    }
}