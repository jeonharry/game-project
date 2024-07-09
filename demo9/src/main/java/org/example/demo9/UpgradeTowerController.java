package org.example.demo9;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.example.demo9.controller.Controller;
import org.example.demo9.controller.PlayerController;
import org.example.demo9.model.Map;
import org.example.demo9.model.towers.Tower;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpgradeTowerController implements Initializable {

    @FXML
    private ImageView sellTick;

    @FXML
    private Label upgradePrice;

    @FXML
    private ImageView upgradeTick;
    private static Tower tower;

    @FXML
    void pickSell(MouseEvent event) {
        sellTick.setVisible(true);
        upgradeTick.setVisible(false);
    }

    @FXML
    void pickUpgrade(MouseEvent event) {
        upgradeTick.setVisible(true);
        sellTick.setVisible(false);
    }

    @FXML
    void sell(MouseEvent event) {
        Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())+(tower.getSellPrice()/2)));
        tower.destroy();
        Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTowerUpgrade());
    }

    @FXML
    void upgrade(MouseEvent event) {
        if(Integer.parseInt(Controller.getController().getCoins().getText())- getTower().getUpgradePrice()>=0)
        {
            try
            {
                int price=tower.getUpgradePrice();
                tower.upgrade();
                Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())-price));
            }catch (Exception exception)
            {
                showError(exception.getMessage());
            }
        }
        else
            showError("not enough coins");
        Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTowerUpgrade());
    }

    public static Tower getTower() {
        return tower;
    }

    public static void setTower(Tower tower) {
        UpgradeTowerController.tower = tower;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        upgradePrice.setText(String.valueOf(tower.getUpgradePrice()));
    }
    public void showError(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
