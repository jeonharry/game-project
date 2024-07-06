package org.example.demo9;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.example.demo9.controller.Controller;
import org.example.demo9.model.Map;

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
    private static Map map;
    private Node selectedTower=null;

    private ArrayList <Node> heroPlaces=new ArrayList<>();

    @FXML
    void placeTower(MouseEvent event) {

    }

    @FXML
    void start(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.getController().setCoins(coins);
        Controller.getController().setHearts(hearts);
        Controller.getController().setWaves(waves);
        Controller.getController().setMap(root);
        waves.setText(String.valueOf(map.getAttackWaves()));
        coins.setText(String.valueOf(map.getCoins()));
        for(int i=0;i<map.getTowerPlaces().size();i++)
            if(map.getTowerPlaces().get(i)!=null)
            {
                Ellipse tower=new Ellipse(); tower.setLayoutX(map.getTowerPlaces().get(i++)); tower.setLayoutY(map.getTowerPlaces().get(i));
                tower.setFill(Color.TRANSPARENT); tower.setStroke(Color.TRANSPARENT); tower.setRadiusX(30); tower.setRadiusY(23); tower.setCursor(Cursor.HAND);
                root.getChildren().add(tower);
                tower.setOnMouseClicked(event -> {
                    try {
                        if(selectedTower!=null)
                        {
                            root.getChildren().remove(selectedTower);
                            Controller.getController().setSelectedTower(null);
                        }
                        Node child=new FXMLLoader(Main.class.getResource("TowerGenerator.fxml")).load();
                        child.setLayoutX(tower.getLayoutX()-65); child.setLayoutY(tower.getLayoutY()-65);
                        TowerGeneratorController.setMap(map);
                        root.getChildren().add(child);
                        selectedTower=child;
                        Controller.getController().setSelectedTower(child);
                        Controller.getController().setTowerPlace(tower);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
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
