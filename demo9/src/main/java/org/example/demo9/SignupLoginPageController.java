package org.example.demo9;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.example.demo9.controller.Controller;
import org.example.demo9.controller.PlayerController;

import java.sql.SQLException;

public class SignupLoginPageController {

    @FXML
    private StackPane back_btn;

    @FXML
    private Label error_lbl;

    @FXML
    private HBox loginHBox;

    @FXML
    private PasswordField password;

    @FXML
    private Label signupLogin_lbl;

    @FXML
    private TextField username;

    @FXML
    void back(MouseEvent event) {
        back_btn.setVisible(false);
        loginHBox.setDisable(false);
        loginHBox.setVisible(true);
        signupLogin_lbl.setText("Signup");
        error_lbl.setVisible(false);
        username.setText("");
        password.setText("");
    }

    @FXML
    void closePage(MouseEvent event) {
        Controller.getController().getStartPageRoot().getChildren().removeLast();
    }

    @FXML
    void login(MouseEvent event) {
        loginHBox.setVisible(false);
        loginHBox.setDisable(true);
        back_btn.setVisible(true);
        signupLogin_lbl.setText("Login");
        error_lbl.setVisible(false);
        username.setText("");
        password.setText("");
    }

    @FXML
    void signupOrLogin(MouseEvent event) {
        if(signupLogin_lbl.getText().compareTo("Signup")==0)
        {
            try
            {
                error_lbl.setVisible(false);
                PlayerController.getPlayerController().signUp(username.getText(),password.getText());
                loginHBox.setVisible(false);
                loginHBox.setDisable(true);
                back_btn.setVisible(true);
                signupLogin_lbl.setText("Login");
                error_lbl.setVisible(false);
                username.setText("");
                password.setText("");
            }catch (Exception exception)
            {
                error_lbl.setText(exception.getMessage());
                error_lbl.setVisible(true);
            }
        }
        else
        {
            try
            {
                error_lbl.setVisible(false);
                PlayerController.getPlayerController().login(username.getText(),password.getText());
                Controller.getController().getStartPageRoot().getChildren().removeLast();
                logIn(username.getText());
                FXMLLoader loader=new FXMLLoader(Main.class.getResource("GamePage.fxml"));
                Main.getStage().setScene(new Scene(loader.load(),840,460));
            }catch (Exception exception)
            {
                error_lbl.setText(exception.getMessage());
                error_lbl.setVisible(true);
            }
        }
    }
    public void logIn(String username)
    {
        Controller.getController().getLogedInUsername().setText(username);
        Controller.getController().getLoginStackPane().setVisible(true);
        Controller.getController().getLogout_btn().setOnMouseClicked(event -> {
            try {
                PlayerController.getPlayerController().logout();
            } catch (SQLException e) {}
            Controller.getController().getLoginStackPane().setVisible(false);
        });
    }
}
