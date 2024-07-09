package org.example.demo9;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.example.demo9.controller.Controller;
import org.example.demo9.model.Map;
import org.example.demo9.model.raiders.FlierRaider;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    @FXML
    private Label coins;

    @FXML
    private Label hearts;

    @FXML
    private AnchorPane root;

    @FXML
    private Label waves;

    @FXML
    private ImageView nextWave;
    private static Map map;
    private ArrayList <Node> heroPlaces=new ArrayList<>();

    @FXML
    void start(MouseEvent event) {
        nextWave.setVisible(false);
        FlierRaider raider=new FlierRaider(75,map.getHeroPlaces());
        map.getRaidersInMap().add(raider);
        raider.walk();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //play music
        Controller.getController().setCoins(coins);
        Controller.getController().setHearts(hearts);
        Controller.getController().setWaves(waves);
        Controller.getController().setMap(root);
        waves.setText("0/"+String.valueOf(map.getAttackWaves()));
        coins.setText(String.valueOf(map.getCoins()));
        for(int i=0;i<map.getTowerPlaces().size();i++)
            if(map.getTowerPlaces().get(i)!=null)
            {
                Ellipse tower=new Ellipse(); tower.setLayoutX(map.getTowerPlaces().get(i++)); tower.setLayoutY(map.getTowerPlaces().get(i));
                tower.setFill(Color.TRANSPARENT); tower.setStroke(Color.TRANSPARENT); tower.setRadiusX(30); tower.setRadiusY(23); tower.setCursor(Cursor.HAND);
                root.getChildren().add(tower);
                tower.setOnMouseClicked(event -> {
                    try {
                        if(Controller.getController().getSelectedTower()!=null)
                        {
                            root.getChildren().remove(Controller.getController().getSelectedTower());
                            Controller.getController().setSelectedTower(null);
                        }
                        if(Controller.getController().getPageForUpgrade()!=null)
                        {
                            root.getChildren().remove(Controller.getController().getPageForUpgrade());
                            Controller.getController().setPageForUpgrade(null);
                        }
                        Node child=new FXMLLoader(Main.class.getResource("TowerGenerator.fxml")).load();
                        child.setLayoutX(tower.getLayoutX()-65); child.setLayoutY(tower.getLayoutY()-65);
                        TowerGeneratorController.setMap(map);
                        root.getChildren().add(child);
                        Controller.getController().setSelectedTower(child);
                        Controller.getController().setTowerPlace(tower);
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("error");
                        alert.setHeaderText(e.getMessage());
                        alert.showAndWait();
                    }
                });
            }
    }

    public static Map getMap() {
        return map;
    }

    public static void setMap(Map map) {
        MapController.map = map;
    }
}
