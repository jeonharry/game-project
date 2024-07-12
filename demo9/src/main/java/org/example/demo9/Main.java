package org.example.demo9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.demo9.model.Database;
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
        ArrayList <ArrayList <ArrayList<Double>>> roads=new ArrayList<>();
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
        roads.add(road);
        ArrayList <Direction> directions=new ArrayList<>();
        directions.add(Direction.RIGHT);
        directions.add(Direction.LEFT);
        directions.add(Direction.LEFT);
        directions.add(Direction.DOWN);
        directions.add(Direction.UP);
        ArrayList <Double> startButton=new ArrayList<>();
        startButton.add(354.0); startButton.add(5.0);
        Image image=new Image(Main.class.getResource("pics/Screenshot_20240706_122243_Kingdom Rush.jpg").toExternalForm());
        Map firstMap=new Map(250,towerPlaces,roads,end,directions,image,startButton,5);
        Database.getDatabase().getMaps().add(firstMap);

        ArrayList<Double> end2=new ArrayList<>(); end2.add(343.0); end2.add(450.0);
        ArrayList <Double> towerPlaces2=new ArrayList<>();
        towerPlaces2.add(464.0); towerPlaces2.add(75.0);
        towerPlaces2.add(462.0); towerPlaces2.add(133.0);
        towerPlaces2.add(333.0); towerPlaces2.add(85.0);
        towerPlaces2.add(280.0); towerPlaces2.add(194.0);
        towerPlaces2.add(409.0); towerPlaces2.add(287.0);
        towerPlaces2.add(337.0); towerPlaces2.add(300.0);
        towerPlaces2.add(420.0); towerPlaces2.add(392.0);
        ArrayList <ArrayList <ArrayList<Double>>> roads2=new ArrayList<>();
        ArrayList <ArrayList<Double>> road2=new ArrayList<>();
        road2.add(makeSpot(745.0,108.0));
        road2.add(makeSpot(571.0,118.0));
        road2.add(makeSpot(505.0,22.0));
        road2.add(makeSpot(403.0,7.0));
        road2.add(makeSpot(366.0,105.0));
        road2.add(makeSpot(271.0,119.0));
        road2.add(makeSpot(188.0,161.0));
        road2.add(makeSpot(222.0,227.0));
        road2.add(makeSpot(385.0,219.0));
        road2.add(makeSpot(461.0,248.0));
        road2.add(makeSpot(451.0,313.0));
        road2.add(makeSpot(356.0,339.0));
        road2.add(makeSpot(343.0,450.0));
        roads2.add(road2);
        ArrayList <Direction> directions2=new ArrayList<>();
        directions2.add(Direction.UP);
        directions2.add(Direction.RIGHT);
        directions2.add(Direction.RIGHT);
        directions2.add(Direction.LEFT);
        directions2.add(Direction.UP);
        directions2.add(Direction.UP);
        directions2.add(Direction.LEFT);
        ArrayList <Double> startButton2=new ArrayList<>();
        startButton2.add(800.0); startButton2.add(100.0);
        Image image2=new Image(Main.class.getResource("pics/Screenshot_20240711_101808_Kingdom Rush.jpg").toExternalForm());
        Map secondMap=new Map(300,towerPlaces2,roads2,end2,directions2,image2,startButton2,7);
        Database.getDatabase().getMaps().add(secondMap);

        ArrayList<Double> end3=new ArrayList<>(); end3.add(460.0); end3.add(29.0);
        ArrayList <Double> towerPlaces3=new ArrayList<>();
        towerPlaces3.add(392.0); towerPlaces3.add(362.0);
        towerPlaces3.add(475.0); towerPlaces3.add(366.0);
        towerPlaces3.add(605.0); towerPlaces3.add(318.0);
        towerPlaces3.add(566.0); towerPlaces3.add(197.0);
        towerPlaces3.add(458.0); towerPlaces3.add(118.0);
        towerPlaces3.add(166.0); towerPlaces3.add(325.0);
        towerPlaces3.add(282.0); towerPlaces3.add(273.0);
        towerPlaces3.add(265.0); towerPlaces3.add(176.0);
        towerPlaces3.add(308.0); towerPlaces3.add(134.0);
        ArrayList <ArrayList <ArrayList<Double>>> roads3=new ArrayList<>();
        ArrayList <ArrayList<Double>> road31=new ArrayList<>();
        road31.add(makeSpot(348.0,433.0));
        road31.add(makeSpot(356.0,392.0));
        road31.add(makeSpot(479.0,404.0));
        road31.add(makeSpot(581.0,386.0));
        road31.add(makeSpot(650.0,329.0));
        road31.add(makeSpot(653.0,219.0));
        road31.add(makeSpot(616.0,160.0));
        road31.add(makeSpot(562.0,112.0));
        road31.add(makeSpot(447.0,54.0));
        road31.add(makeSpot(460.0,29.0));
        roads3.add(road31);
        ArrayList <ArrayList<Double>> road32=new ArrayList<>();
        road32.add(makeSpot(348.0,433.0));
        road32.add(makeSpot(356.0,392.0));
        road32.add(makeSpot(297.0,369.0));
        road32.add(makeSpot(246.0,321.0));
        road32.add(makeSpot(188.0,255.0));
        road32.add(makeSpot(171.0,177.0));
        road32.add(makeSpot(212.0,103.0));
        road32.add(makeSpot(288.0,63.0));
        road32.add(makeSpot(432.0,45.0));
        road31.add(makeSpot(460.0,29.0));
        roads3.add(road32);
        ArrayList <Direction> directions3=new ArrayList<>();
        directions3.add(Direction.DOWN);
        directions3.add(Direction.DOWN);
        directions3.add(Direction.RIGHT);
        directions3.add(Direction.RIGHT);
        directions3.add(Direction.UP);
        directions3.add(Direction.RIGHT);
        directions3.add(Direction.LEFT);
        directions3.add(Direction.LEFT);
        directions3.add(Direction.LEFT);
        ArrayList <Double> startButton3=new ArrayList<>();
        startButton3.add(380.0); startButton3.add(459.0);
        Image image3=new Image(Main.class.getResource("pics/Screenshot_20240711_103109_Kingdom Rush.jpg").toExternalForm());
        Map thirdMap=new Map(370,towerPlaces3,roads3,end3,directions3,image3,startButton3,9);
        Database.getDatabase().getMaps().add(thirdMap);

        ArrayList<Double> end4=new ArrayList<>(); end4.add(760.0); end4.add(327.0);
        ArrayList <Double> towerPlaces4=new ArrayList<>();
        towerPlaces4.add(269.0); towerPlaces4.add(150.0);
        towerPlaces4.add(354.0); towerPlaces4.add(153.0);
        towerPlaces4.add(438.0); towerPlaces4.add(172.0);
        towerPlaces4.add(500.0); towerPlaces4.add(81.0);
        towerPlaces4.add(310.0); towerPlaces4.add(246.0);
        towerPlaces4.add(494.0); towerPlaces4.add(295.0);
        towerPlaces4.add(412.0); towerPlaces4.add(329.0);
        towerPlaces4.add(334.0); towerPlaces4.add(351.0);
        towerPlaces4.add(557.0); towerPlaces4.add(404.0);
        ArrayList <ArrayList <ArrayList<Double>>> roads4=new ArrayList<>();
        ArrayList <ArrayList<Double>> road41=new ArrayList<>();
        road41.add(makeSpot(31.0,106.0));
        road41.add(makeSpot(188.0,90.0));
        road41.add(makeSpot(319.0,87.0));
        road41.add(makeSpot(435.0,106.0));
        road41.add(makeSpot(493.0,148.0));
        road41.add(makeSpot(449.0,228.0));
        road41.add(makeSpot(253.0,302.0));
        road41.add(makeSpot(237.0,369.0));
        road41.add(makeSpot(319.0,387.0));
        road41.add(makeSpot(548.0,320.0));
        road41.add(makeSpot(658.0,309.0));
        road41.add(makeSpot(760.0,327.0));
        roads4.add(road41);
        ArrayList <ArrayList<Double>> road42=new ArrayList<>();
        road42.add(makeSpot(26.0,236.0));
        road42.add(makeSpot(188.0,194.0));
        road42.add(makeSpot(260.0,187.0));
        road42.add(makeSpot(354.0,195.0));
        road42.add(makeSpot(392.0,236.0));
        road42.add(makeSpot(253.0,302.0));
        road42.add(makeSpot(237.0,369.0));
        road42.add(makeSpot(319.0,387.0));
        road42.add(makeSpot(548.0,320.0));
        road42.add(makeSpot(658.0,309.0));
        road42.add(makeSpot(760.0,327.0));
        roads4.add(road42);
        ArrayList <Direction> directions4=new ArrayList<>();
        directions4.add(Direction.UP);
        directions4.add(Direction.UP);
        directions4.add(Direction.UP);
        directions4.add(Direction.DOWN);
        directions4.add(Direction.UP);
        directions4.add(Direction.UP);
        directions4.add(Direction.UP);
        directions4.add(Direction.UP);
        directions4.add(Direction.UP);
        ArrayList <Double> startButton4=new ArrayList<>();
        startButton4.add(24.0); startButton4.add(140.0);
        Image image4=new Image(Main.class.getResource("pics/Untitled.jpg").toExternalForm());
        Map forthMap=new Map(400,towerPlaces4,roads4,end4,directions4,image4,startButton4,10);
        Database.getDatabase().getMaps().add(forthMap);
    }
    public static ArrayList<Double> makeSpot(double x,double y)
    {
        ArrayList <Double> spot=new ArrayList<>();
        spot.add(x); spot.add(y);
        return spot;
    }
}