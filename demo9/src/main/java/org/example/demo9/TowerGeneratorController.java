package org.example.demo9;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.demo9.controller.Controller;
import org.example.demo9.model.Map;
import org.example.demo9.model.towers.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TowerGeneratorController implements Initializable {

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
    private Label wizardPrice;

    @FXML
    private ImageView wizardTick;

    private static Map map;

    @FXML
    void selectArcher(MouseEvent event) {
        if(Integer.parseInt(Controller.getController().getCoins().getText())- TowerPrices.ARCHER.getPrice()>=0)
        {
            Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())- TowerPrices.ARCHER.getPrice()));
            try{
                ArcherTower archerTower=new ArcherTower(80+(MapController.getMapNum())*10,18+(5*(MapController.getMapNum()-1)));
                map.getTowers().add(archerTower);
                Controller.getController().getMap().getChildren().remove(Controller.getController().getTowerPlace());
                archerTower.getTower().setLayoutX(Controller.getController().getSelectedTower().getLayoutX()+65-28);
                archerTower.getTower().setLayoutY(Controller.getController().getSelectedTower().getLayoutY()+65-35);
                Controller.getController().getMap().getChildren().add(archerTower.getTower());
                archerTower.getTower().setOnMouseClicked(e -> {
                    if(Controller.getController().getPageForUpgrade()!=null)
                    {
                        Controller.getController().getMap().getChildren().remove(Controller.getController().getPageForUpgrade());
                        Controller.getController().setPageForUpgrade(null);
                    }
                    if(Controller.getController().getSelectedTower()!=null)
                    {
                        Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTower());
                        Controller.getController().setSelectedTower(null);
                    }
                    FXMLLoader loader=new FXMLLoader(Main.class.getResource("UpgradeTower.fxml"));
                    try {
                        UpgradeTowerController.setTower(archerTower);
                        Node child=loader.load(); child.setLayoutY(archerTower.getTower().getLayoutY()-50); child.setLayoutX(archerTower.getTower().getLayoutX()-40);
                        Controller.getController().setSelectedTowerUpgrade(child);
                        Controller.getController().getMap().getChildren().add(child);
                        Controller.getController().setPageForUpgrade(child);
                    } catch (IOException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("error");
                        alert.setHeaderText(ex.getMessage());
                        alert.showAndWait();
                    }
                });
            }catch (Exception exception){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(exception.getMessage());
                alert.showAndWait();
            }
        }
        else
            showError();
        Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTower());
    }

    @FXML
    void selectBomb(MouseEvent event) {
        if(Integer.parseInt(Controller.getController().getCoins().getText())- TowerPrices.BOMB.getPrice()>=0)
        {
            Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())-TowerPrices.BOMB.getPrice()));
            try
            {
                Artillery artillery=new Artillery(60+(MapController.getMapNum())*10,16+(5*(MapController.getMapNum()-1)));
                map.getTowers().add(artillery);
                Controller.getController().getMap().getChildren().remove(Controller.getController().getTowerPlace());
                artillery.getTower().setLayoutX(Controller.getController().getSelectedTower().getLayoutX()+65-28);
                artillery.getTower().setLayoutY(Controller.getController().getSelectedTower().getLayoutY()+65-35);
                Controller.getController().getMap().getChildren().add(artillery.getTower());
                artillery.getTower().setOnMouseClicked(e -> {
                    if(Controller.getController().getPageForUpgrade()!=null)
                    {
                        Controller.getController().getMap().getChildren().remove(Controller.getController().getPageForUpgrade());
                        Controller.getController().setPageForUpgrade(null);
                    }
                    if(Controller.getController().getSelectedTower()!=null)
                    {
                        Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTower());
                        Controller.getController().setSelectedTower(null);
                    }
                    FXMLLoader loader=new FXMLLoader(Main.class.getResource("UpgradeTower.fxml"));
                    try {
                        UpgradeTowerController.setTower(artillery);
                        Node child=loader.load(); child.setLayoutY(artillery.getTower().getLayoutY()-50); child.setLayoutX(artillery.getTower().getLayoutX()-40);
                        Controller.getController().setSelectedTowerUpgrade(child);
                        Controller.getController().getMap().getChildren().add(child);
                        Controller.getController().setPageForUpgrade(child);
                    } catch (IOException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("error");
                        alert.setHeaderText(ex.getMessage());
                        alert.showAndWait();
                    }
                });
            }catch (Exception exception){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(exception.getMessage());
                alert.showAndWait();
            }
        }
        else
            showError();
        Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTower());
    }

    @FXML
    void selectDef(MouseEvent event) {
        if(Integer.parseInt(Controller.getController().getCoins().getText())- TowerPrices.DEF.getPrice()>=0)
        {
            Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())-TowerPrices.DEF.getPrice()));
            try
            {
                DefendTower defendTower=new DefendTower(85+(MapController.getMapNum())*10);
                map.getTowers().add(defendTower);
                Controller.getController().getMap().getChildren().remove(Controller.getController().getTowerPlace());
                defendTower.getTower().setLayoutX(Controller.getController().getSelectedTower().getLayoutX()+65-28);
                defendTower.getTower().setLayoutY(Controller.getController().getSelectedTower().getLayoutY()+65-35);
                Controller.getController().getMap().getChildren().add(defendTower.getTower());
                defendTower.getTower().setOnMouseClicked(e -> {
                    if(Controller.getController().getPageForUpgrade()!=null)
                    {
                        Controller.getController().getMap().getChildren().remove(Controller.getController().getPageForUpgrade());
                        Controller.getController().setPageForUpgrade(null);
                    }
                    if(Controller.getController().getSelectedTower()!=null)
                    {
                        Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTower());
                        Controller.getController().setSelectedTower(null);
                    }
                    FXMLLoader loader=new FXMLLoader(Main.class.getResource("UpgradeTower.fxml"));
                    try {
                        UpgradeTowerController.setTower(defendTower);
                        Node child=loader.load(); child.setLayoutY(defendTower.getTower().getLayoutY()-50); child.setLayoutX(defendTower.getTower().getLayoutX()-40);
                        Controller.getController().setSelectedTowerUpgrade(child);
                        Controller.getController().getMap().getChildren().add(child);
                        Controller.getController().setPageForUpgrade(child);
                    } catch (IOException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("error");
                        alert.setHeaderText(ex.getMessage());
                        alert.showAndWait();
                    }
                });
                defendTower.animation();
            }catch (Exception exception){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(exception.getMessage());
                alert.showAndWait();
            }
        }
        else
            showError();
        Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTower());
    }

    @FXML
    void selectWizard(MouseEvent event) {
        if(Integer.parseInt(Controller.getController().getCoins().getText())- TowerPrices.WIZARD.getPrice()>=0)
        {
            Controller.getController().getCoins().setText(String.valueOf(Integer.parseInt(Controller.getController().getCoins().getText())-TowerPrices.WIZARD.getPrice()));
            try
            {
                WizardTower wizardTower=new WizardTower(70+(MapController.getMapNum())*10,24+(5*(MapController.getMapNum()-1)));
                map.getTowers().add(wizardTower);
                Controller.getController().getMap().getChildren().remove(Controller.getController().getTowerPlace());
                wizardTower.getTower().setLayoutX(Controller.getController().getSelectedTower().getLayoutX()+65-28);
                wizardTower.getTower().setLayoutY(Controller.getController().getSelectedTower().getLayoutY()+65-35);
                Controller.getController().getMap().getChildren().add(wizardTower.getTower());
                wizardTower.getTower().setOnMouseClicked(e -> {
                    if(Controller.getController().getPageForUpgrade()!=null)
                    {
                        Controller.getController().getMap().getChildren().remove(Controller.getController().getPageForUpgrade());
                        Controller.getController().setPageForUpgrade(null);
                    }
                    if(Controller.getController().getSelectedTower()!=null)
                    {
                        Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTower());
                        Controller.getController().setSelectedTower(null);
                    }
                    FXMLLoader loader=new FXMLLoader(Main.class.getResource("UpgradeTower.fxml"));
                    try {
                        UpgradeTowerController.setTower(wizardTower);
                        Node child=loader.load(); child.setLayoutY(wizardTower.getTower().getLayoutY()-50); child.setLayoutX(wizardTower.getTower().getLayoutX()-40);
                        Controller.getController().setSelectedTowerUpgrade(child);
                        Controller.getController().getMap().getChildren().add(child);
                        Controller.getController().setPageForUpgrade(child);
                    } catch (IOException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("error");
                        alert.setHeaderText(ex.getMessage());
                        alert.showAndWait();
                    }
                });
            }catch (Exception exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(exception.getMessage());
                alert.showAndWait();
            }
        }
        else
            showError();
        Controller.getController().getMap().getChildren().remove(Controller.getController().getSelectedTower());
    }

    @FXML
    void pickArcher(MouseEvent event) {
        archerTick.setVisible(true);
//        defTick.setVisible(false);
        wizardTick.setVisible(false);
        bombTick.setVisible(false);
    }

    @FXML
    void pickBomb(MouseEvent event) {
        archerTick.setVisible(false);
//        defTick.setVisible(false);
        wizardTick.setVisible(false);
        bombTick.setVisible(true);
    }

    @FXML
    void pickDef(MouseEvent event) {
        archerTick.setVisible(false);
//        defTick.setVisible(true);
        wizardTick.setVisible(false);
        bombTick.setVisible(false);
    }

    @FXML
    void pickWizard(MouseEvent event) {
        archerTick.setVisible(false);
//        defTick.setVisible(false);
        wizardTick.setVisible(true);
        bombTick.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        archerPrice.setText(String.valueOf(TowerPrices.ARCHER.getPrice()));
        bombPrice.setText(String.valueOf(TowerPrices.BOMB.getPrice()));
//        defPrice.setText(String.valueOf(TowerPrices.DEF.getPrice()));
        defPrice.setText("000");
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
