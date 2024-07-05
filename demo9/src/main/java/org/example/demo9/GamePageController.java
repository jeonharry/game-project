package org.example.demo9;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.demo9.controller.Controller;
import org.example.demo9.controller.PlayerController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GamePageController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView firstMap;

    @FXML
    private ImageView forthMap;

    @FXML
    private Label gems;

    @FXML
    private Label level;

    @FXML
    private ImageView secondMap;

    @FXML
    private ImageView thirdMap;

    @FXML
    void openSetting(MouseEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("SettingInGame.fxml"));
        Controller.getController().getGamePage().getChildren().addLast(loader.load());
    }

    @FXML
    void openShop(MouseEvent event) {

    }

    @FXML
    void openUpgrade(MouseEvent event) {

    }

    @FXML
    void playFirstMap(MouseEvent event) {

    }

    @FXML
    void playSecondMap(MouseEvent event) {

    }

    @FXML
    void playThirdMap(MouseEvent event) {

    }

    @FXML
    void playforthMap(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.getController().setGamePage(root);
        level.setText(String.valueOf(PlayerController.getPlayer().getLevel()));
        gems.setText(String.valueOf(PlayerController.getPlayer().getGems()));
        //        MediaPlayer music=new MediaPlayer(new Media(""));
//        volume.setValue(music.getVolume()*100);
//        volume.valueProperty().addListener(new InvalidationListener() {
//            @Override
//            public void invalidated(Observable observable) {
//                music.setVolume(volume.getValue()/100);
//            }
//        });
    }
}
