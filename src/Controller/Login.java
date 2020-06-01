/*
This controller class controls as the elements
of the Login.fxml file
 */

package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.event.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    @FXML private Label validate;
    @FXML private Button login;
    @FXML protected TextField userName;

    private String user;

    @FXML
    private void initialize() {
        validate.setText("");
    }


    //method for login button
    @FXML
    private void login(ActionEvent actionEvent) {
        if (userName.getText().equals("")) {
            validate.setText("Please enter your username.!");
        } else if (validateLogin(userName.getText()) == 1) {
            validate.setText("Hello " + userName.getText());
            setLogin(userName.getText());
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


    //method to open new window after successfull login validation
    private void setLogin(String user) {
        login.setOnMouseClicked((event) -> {

            try {
//                ((Node)(event.getSource())).getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
                Parent root = loader.load();
                MainWindow login = loader.getController();
                login.setUser(userName.getText());
                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node)(event).getSource()).getScene().getWindow();
                stage.setTitle("Welcome " + user + ".!");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    //getter for username. This is done to display the username in the mainWindow
    public String getUser() {
        return this.user;
    }


    //setter to store the username. This is done to display the username in the mainWindow
    public void setUser(String user) {
        this.user = user;
    }
}
