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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.example.demo9.controller.Controller;
import org.example.demo9.controller.PlayerController;
import org.example.demo9.model.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private static boolean win=false;
    private static int num=0;

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
    void playFirstMap(MouseEvent event) throws IOException {
        MapController.setLose(false);
        if(MapController.getTransition()!=null && MapController.getEnd()!=null)
        {
            MapController.getEnd().stop();
            MapController.getTransition().stop();
        }
        Database.getDatabase().getMaps().getFirst().setRaidersInMap(new ArrayList<>());
        Database.getDatabase().getMaps().getFirst().setTowers(new ArrayList<>());
        MapController.setMap(Database.getDatabase().getMaps().getFirst());
        MapController.setMapNum(1);
        MapController.setImage(Database.getDatabase().getMaps().getFirst().getImage());
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("Map.fxml"));
        Main.getStage().setScene(new Scene(loader.load(),840,460));
    }

    @FXML
    void playSecondMap(MouseEvent event) throws IOException {
        MapController.setLose(false);
        if(MapController.getTransition()!=null && MapController.getEnd()!=null)
        {
            MapController.getEnd().stop();
            MapController.getTransition().stop();
        }
        Database.getDatabase().getMaps().get(1).setRaidersInMap(new ArrayList<>());
        Database.getDatabase().getMaps().get(1).setTowers(new ArrayList<>());
        MapController.setMap(Database.getDatabase().getMaps().get(1));
        MapController.setMapNum(2);
        MapController.setImage(Database.getDatabase().getMaps().get(1).getImage());
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("Map.fxml"));
        Main.getStage().setScene(new Scene(loader.load(),840,460));
    }

    @FXML
    void playThirdMap(MouseEvent event) throws IOException {
        MapController.setLose(false);
        if(MapController.getTransition()!=null && MapController.getEnd()!=null)
        {
            MapController.getEnd().stop();
            MapController.getTransition().stop();
        }
        Database.getDatabase().getMaps().get(2).setRaidersInMap(new ArrayList<>());
        Database.getDatabase().getMaps().get(2).setTowers(new ArrayList<>());
        MapController.setMap(Database.getDatabase().getMaps().get(2));
        MapController.setMapNum(3);
        MapController.setImage(Database.getDatabase().getMaps().get(2).getImage());
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("Map.fxml"));
        Main.getStage().setScene(new Scene(loader.load(),840,460));
    }

    @FXML
    void playForthMap(MouseEvent event) throws IOException {
        MapController.setLose(false);
        if(MapController.getTransition()!=null && MapController.getEnd()!=null)
        {
            MapController.getEnd().stop();
            MapController.getTransition().stop();
        }
        Database.getDatabase().getMaps().get(3).setRaidersInMap(new ArrayList<>());
        Database.getDatabase().getMaps().get(3).setTowers(new ArrayList<>());
        MapController.setMap(Database.getDatabase().getMaps().get(3));
        MapController.setMapNum(4);
        MapController.setImage(Database.getDatabase().getMaps().get(3).getImage());
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("Map.fxml"));
        Main.getStage().setScene(new Scene(loader.load(),840,460));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(win)
            win(num);
        Controller.getController().getMusic().stop();
        MediaPlayer music=new MediaPlayer(new Media(Main.class.getResource("audio/01. Main Theme.mp3").toExternalForm()));
        music.setVolume(Controller.getController().getMusic().getVolume());
        Controller.getController().setMusic(music);
        Controller.getController().getMusic().setCycleCount(MediaPlayer.INDEFINITE);
        Controller.getController().getMusic().play();
        Controller.getController().setGems(gems);
        Controller.getController().setGamePage(root);
        level.setText("Lv."+String.valueOf(PlayerController.getPlayer().getLevel()));
        gems.setText(String.valueOf(PlayerController.getPlayer().getGems()));
        if(PlayerController.getPlayer().getLevel()>=2)
        {
            firstMap.setImage(new Image(Main.class.getResource("pics/Clipped_image_20240705_112219.png").toExternalForm()));
            secondMap.setVisible(true);
        }
        if(PlayerController.getPlayer().getLevel()>=3)
        {
            secondMap.setImage(new Image(Main.class.getResource("pics/Clipped_image_20240705_112219.png").toExternalForm()));
            thirdMap.setVisible(true);
        }
        if(PlayerController.getPlayer().getLevel()>=4)
        {
            thirdMap.setImage(new Image(Main.class.getResource("pics/Clipped_image_20240705_112219.png").toExternalForm()));
            forthMap.setVisible(true);
        }
        if(PlayerController.getPlayer().getLevel()>=5)
        {
            forthMap.setImage(new Image(Main.class.getResource("pics/Clipped_image_20240705_112219.png").toExternalForm()));
        }
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

    public static boolean isWin() {
        return win;
    }

    public static void setWin(boolean win) {
        GamePageController.win = win;
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        GamePageController.num = num;
    }
}
