package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Login {
    @FXML
    private Label validate;
    @FXML
    private Button login;
    @FXML
    private TextField userName;

    @FXML
    private void initialize() {
        validate.setText("");
    }

    @FXML
    private void login(ActionEvent actionEvent) {
        if (userName.getText().equals("")) {
            validate.setText("Please enter your username.!");
        } else if (validateLogin(userName.getText()) == 1) {
            validate.setText("Hello " + userName.getText());
            setLogin(actionEvent, userName.getText());
        } else {
            validate.setText("Invalid username. Please try again.!");
        }
    }

    private int validateLogin(String id) {
        if (id.charAt(0) == 's') {
            if (id.charAt(1) >= '0' && id.charAt(1) <= '9') {
                return 1;
            }
        }

        return 0;
    }

    private void setLogin(ActionEvent actionEvent, String user) {
        login.setOnMouseClicked((event) -> {
            FXMLLoader loader = new FXMLLoader();
            try {
                loader.setLocation(getClass().getResource("/view/MainWindow.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setTitle("Welcome " + user + ".!");
                stage.setScene(scene);
                stage.show();


            } catch (IOException e) {
                e.printStackTrace();
            }
        });



    }



}
