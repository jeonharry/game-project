package org.example.demo9;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.example.demo9.controller.Controller;
import org.example.demo9.controller.PlayerController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingInGameController implements Initializable {

    @FXML
    private Label error;

    @FXML
    private StackPane close_btn;

    @FXML
    private PasswordField newPassword;

    @FXML
    private HBox newPasswordHbox;

    @FXML
    private TextField newUsername;

    @FXML
    private HBox newUsernameHbox;

    @FXML
    private Slider volume;

    @FXML
    void back(MouseEvent event) throws IOException {
        Controller.getController().getGamePage().getChildren().removeLast();
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("StartPage.fxml"));
        Main.getStage().setScene(new Scene(loader.load(),840,460));
    }

    @FXML
    void changePassword(MouseEvent event) {
        newPasswordHbox.setVisible(true);
    }

    @FXML
    void changeUsername(MouseEvent event) {
        newUsernameHbox.setVisible(true);
    }

    @FXML
    void closePage(MouseEvent event) {
        Controller.getController().getGamePage().getChildren().removeLast();
    }

    @FXML
    void submitPassword(MouseEvent event) {
        try
        {
            error.setVisible(false);
            PlayerController.getPlayerController().updatePassword(newPassword.getText());
            newPasswordHbox.setVisible(false);
        }catch (Exception exception)
        {
            error.setText(exception.getMessage());
            error.setVisible(true);
        }
    }

    @FXML
    void submitUsername(MouseEvent event) {
        try
        {
            error.setVisible(false);
            PlayerController.getPlayerController().updateName(newUsername.getText());
            newUsernameHbox.setVisible(false);
        }catch (Exception exception)
        {
            error.setText(exception.getMessage());
            error.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newUsernameHbox.setVisible(false);
        newPasswordHbox.setVisible(false);
        volume.setValue(Controller.getController().getMusic().getVolume()*100);
        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                Controller.getController().getMusic().setVolume(volume.getValue()/100);
            }
        });
    }
}
