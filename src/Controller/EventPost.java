package Controller;

import Model.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.NumberFormat;

public class EventPost {
    @FXML private Label capacityError;
    @FXML private ImageView imageView;
    @FXML private DatePicker date;
    @FXML private Button back;
    @FXML private TextField title;
    @FXML private TextField description;
    @FXML private TextField venue;
    @FXML private TextField capacity;
    @FXML private Button upload;
    @FXML private Button closePost;
    @FXML private Button deletePost;
    @FXML private Button savePost;


    @FXML private void upload(ActionEvent actionEvent) {
        upload.setOnMouseClicked((event) ->
        {
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(imageFilter);
            File file = fileChooser.showOpenDialog(null);
            if (file != null)
            {
                try {
                    String url = file.toURI().toURL().toString();
                    imageView.setImage(new Image(url));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else
            {

            }
        });
    }


    @FXML private void closePost(ActionEvent actionEvent) {
    }

    @FXML private void deletePost(ActionEvent actionEvent) {
    }

    @FXML private void savePost(ActionEvent actionEvent) {
        savePost.setOnMouseClicked((event) -> {
            checkCapacity();
            Event ev = new Event ("EVE1", title.getText(), description.getText(), venue.getText(), date.getValue().toString(), Integer.parseInt(capacity.getText()), "OPEN", "s1" );
            ev.insertDB();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successfully created post");
            alert.setContentText("The Post has been created successfully");
            alert.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });



    }

    @FXML private void back(ActionEvent actionEvent) {
        back.setOnMouseClicked((event) -> {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });
    }

    @FXML private void checkCapacity() {
        if(!(capacity.getText().contains("[0-9]")))
        {
            capacityError.setText("Please enter a number");
        }
        else
        {
            capacityError.setText("");
        }
    }
}
