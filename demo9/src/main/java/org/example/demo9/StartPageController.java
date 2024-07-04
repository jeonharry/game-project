package org.example.demo9;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.example.demo9.controller.Controller;
import org.example.demo9.controller.PlayerController;

import java.io.IOException;
import java.sql.SQLException;

public class StartPageController{

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
        Controller.getController().setStartPageRoot(root);
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("SettingPage.fxml"));
        Controller.getController().getStartPageRoot().getChildren().add(loader.load());
    }

    @FXML
    void start(MouseEvent event) {
        Controller.getController().setStartPageRoot(root);
        Controller.getController().setLoginStackPane(logedinStackPane);
        Controller.getController().setLogout_btn(logout);
        Controller.getController().setLogedInUsername(logedinUsername);
        try
        {
            if(PlayerController.getPlayerController().checkLogin())
                ;
            //loading next page
            else
            {
                FXMLLoader loader=new FXMLLoader(Main.class.getResource("SignUpLoginPage.fxml"));
                Controller.getController().getStartPageRoot().getChildren().add(loader.load());
            }
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText(exception.getMessage());
            alert.showAndWait();
        }
    }
}