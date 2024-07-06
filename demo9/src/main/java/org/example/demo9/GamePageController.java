package org.example.demo9;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    void openShop(MouseEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("ShopPage.fxml"));
        Controller.getController().getGamePage().getChildren().addLast(loader.load());
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
    void playForthMap(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.getController().setGems(gems);
        Controller.getController().setGamePage(root);
        level.setText("Lv."+String.valueOf(PlayerController.getPlayer().getLevel()));
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
    public void win(long mapNum)
    {
        if(mapNum==1)
        {
            firstMap.setImage(new Image(Main.class.getResource("pics/Clipped_image_20240705_112219.png").toExternalForm()));
            secondMap.setVisible(true);
            try
            {
                PlayerController.getPlayerController().updateLevel(2);
            }catch (Exception exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(exception.getMessage());
                alert.showAndWait();
            }
            level.setText("Lv."+String.valueOf(PlayerController.getPlayer().getLevel()));
        }
        else if(mapNum==2)
        {
            secondMap.setImage(new Image(Main.class.getResource("pics/Clipped_image_20240705_112219.png").toExternalForm()));
            thirdMap.setVisible(true);
            try
            {
                PlayerController.getPlayerController().updateLevel(3);
            }catch (Exception exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(exception.getMessage());
                alert.showAndWait();
            }
            level.setText("Lv."+String.valueOf(PlayerController.getPlayer().getLevel()));
        }
        else if(mapNum==3)
        {
            thirdMap.setImage(new Image(Main.class.getResource("pics/Clipped_image_20240705_112219.png").toExternalForm()));
            forthMap.setVisible(true);
            try
            {
                PlayerController.getPlayerController().updateLevel(4);
            }catch (Exception exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(exception.getMessage());
                alert.showAndWait();
            }
            level.setText("Lv."+String.valueOf(PlayerController.getPlayer().getLevel()));
        }
        else if(mapNum==4)
        {
            forthMap.setImage(new Image(Main.class.getResource("pics/Clipped_image_20240705_112219.png").toExternalForm()));
            try
            {
                PlayerController.getPlayerController().updateLevel(5);
            }catch (Exception exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(exception.getMessage());
                alert.showAndWait();
            }
            level.setText("Lv."+String.valueOf(PlayerController.getPlayer().getLevel()));
        }
    }
}
