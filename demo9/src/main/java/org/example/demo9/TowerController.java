package org.example.demo9;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import org.example.demo9.model.towers.Tower;

import java.net.URL;
import java.util.ResourceBundle;

public class TowerController implements Initializable {

    @FXML
    private ImageView level1;

    @FXML
    private ImageView level2;

    @FXML
    private ImageView level3;

    @FXML
    private ImageView level4;

    @FXML
    private ImageView level5;

    @FXML
    private ImageView towerImage;
    private static Tower tower;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(tower.getLevel()>1)
            level2.setVisible(true);
        if(tower.getLevel()>2)
            level3.setVisible(true);
        if(tower.getLevel()>3)
            level4.setVisible(true);
        if(tower.getLevel()>4)
            level5.setVisible(true);
        tower.setLevel2(level2);
        tower.setLevel3(level3);
        tower.setLevel4(level4);
        tower.setLevel5(level5);
        tower.setTowerImage(towerImage);
    }

    public static Tower getTower() {
        return tower;
    }

    public static void setTower(Tower tower) {
        TowerController.tower = tower;
    }
}
