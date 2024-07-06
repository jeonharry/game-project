package org.example.demo9;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.example.demo9.controller.Controller;
import org.example.demo9.controller.PlayerController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {

    @FXML
    private ImageView logout;

    @FXML
    private StackPane logedinStackPane;

    @FXML
    private Label logedinUsername;

    @FXML
    private StackPane root;

    @FXML
    void exit(MouseEvent event) {
        Main.getStage().close();
    }

    @FXML
    void setting(MouseEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("SettingPage.fxml"));
        Controller.getController().getStartPageRoot().getChildren().add(loader.load());
    }

    @FXML
    void start(MouseEvent event) {
        try
        {
            if(!PlayerController.getPlayerController().checkLogin())
            {
                FXMLLoader loader=new FXMLLoader(Main.class.getResource("SignUpLoginPage.fxml"));
                Controller.getController().getStartPageRoot().getChildren().add(loader.load());
            }
            else {
                FXMLLoader loader=new FXMLLoader(Main.class.getResource("GamePage.fxml"));
                Main.getStage().setScene(new Scene(loader.load(),840,460));
            }
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText(exception.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.getController().setStartPageRoot(root);
        Controller.getController().setLoginStackPane(logedinStackPane);
        Controller.getController().setLogout_btn(logout);
        Controller.getController().setLogedInUsername(logedinUsername);
        try {
            if(PlayerController.getPlayerController().checkLogin())
            {
                Controller.getController().getLogedInUsername().setText(PlayerController.getPlayer().getUsername());
                Controller.getController().getLoginStackPane().setVisible(true);
                Controller.getController().getLogout_btn().setOnMouseClicked(event -> {
                    try {
                        PlayerController.getPlayerController().logout();
                    } catch (SQLException e) {}
                    Controller.getController().getLoginStackPane().setVisible(false);
                });
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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