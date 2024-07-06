package org.example.demo9;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.demo9.controller.Controller;
import org.example.demo9.model.Map;
import org.example.demo9.model.towers.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TowerGeneratorController implements Initializable {

    @FXML
    private AnchorPane afterBuild;

    @FXML
    private Label archerPrice;

    @FXML
    private ImageView archerTick;

    @FXML
    private AnchorPane beforeBuild;

    @FXML
    private Label bombPrice;

    @FXML
    private ImageView bombTick;

    @FXML
    private Label defPrice;

    @FXML
    private ImageView defTick;

    @FXML
    private ImageView sellTick;

    @FXML
    private Label upgradePrice;

    @FXML
    private ImageView upgradeTick;

    @FXML
    private Label wizardPrice;

    @FXML
    private ImageView wizardTick;

    private static Map map;

    @FXML
    void selectArcher(MouseEvent event) {
        if(Integer.parseInt(Controller.getController().getCoins().getText())- TowerPrices.ARCHER.getPrice()>=0)
        {
            Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())- TowerPrices.ARCHER.getPrice()));
            ArcherTower archerTower=new ArcherTower(70);
            map.getTowers().add(archerTower);
            Controller.getController().getMap().getChildren().remove(Controller.getController().getTowerPlace());
            archerTower.getTower().setLayoutX(Controller.getController().getSelectedTower().getLayoutX()+65-28);
            archerTower.getTower().setLayoutY(Controller.getController().getSelectedTower().getLayoutY()+65-35);
            Controller.getController().getMap().getChildren().add(archerTower.getTower());
//            archerTower.animation();
        }
        else
            showError();
        Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTower());
    }

    @FXML
    void selectBomb(MouseEvent event) {
        //build tower
        if(Integer.parseInt(Controller.getController().getCoins().getText())- TowerPrices.BOMB.getPrice()>=0)
        {
            Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())-TowerPrices.BOMB.getPrice()));
            Artillery artillery=new Artillery(60);
            map.getTowers().add(artillery);
            Controller.getController().getMap().getChildren().remove(Controller.getController().getTowerPlace());
        }
        else
            showError();
        Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTower());
    }

    @FXML
    void selectDef(MouseEvent event) {
        //build tower
        if(Integer.parseInt(Controller.getController().getCoins().getText())- TowerPrices.DEF.getPrice()>=0)
        {
            Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())-TowerPrices.DEF.getPrice()));
            DefendTower defendTower=new DefendTower(85);
            map.getTowers().add(defendTower);
            Controller.getController().getMap().getChildren().remove(Controller.getController().getTowerPlace());
        }
        else
            showError();
        Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTower());
    }

    @FXML
    void selectWizard(MouseEvent event) {
        //build tower
        if(Integer.parseInt(Controller.getController().getCoins().getText())- TowerPrices.WIZARD.getPrice()>=0)
        {
            Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())-TowerPrices.WIZARD.getPrice()));
            WizardTower wizardTower=new WizardTower(70);
            map.getTowers().add(wizardTower);
            Controller.getController().getMap().getChildren().remove(Controller.getController().getTowerPlace());
        }
        else
            showError();
        Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTower());
    }

    @FXML
    void sell(MouseEvent event) {

    }

    @FXML
    void upgrade(MouseEvent event) {

    }

    @FXML
    void pickArcher(MouseEvent event) {
        archerTick.setVisible(true);
        defTick.setVisible(false);
        wizardTick.setVisible(false);
        bombTick.setVisible(false);
    }

    @FXML
    void pickBomb(MouseEvent event) {
        archerTick.setVisible(false);
        defTick.setVisible(false);
        wizardTick.setVisible(false);
        bombTick.setVisible(true);
    }

    @FXML
    void pickDef(MouseEvent event) {
        archerTick.setVisible(false);
        defTick.setVisible(true);
        wizardTick.setVisible(false);
        bombTick.setVisible(false);
    }

    @FXML
    void pickWizard(MouseEvent event) {
        archerTick.setVisible(false);
        defTick.setVisible(false);
        wizardTick.setVisible(true);
        bombTick.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        archerPrice.setText(String.valueOf(TowerPrices.ARCHER.getPrice()));
        bombPrice.setText(String.valueOf(TowerPrices.BOMB.getPrice()));
        defPrice.setText(String.valueOf(TowerPrices.DEF.getPrice()));
        wizardPrice.setText(String.valueOf(TowerPrices.WIZARD.getPrice()));
    }
    public void showError()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("error");
        alert.setHeaderText("not enough coins");
        alert.showAndWait();
    }

    public static void setMap(Map map) {
        TowerGeneratorController.map = map;
    }

    public static Map getMap() {
        return map;
    }
}
