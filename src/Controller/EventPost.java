package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class EventPost {
    @FXML private ImageView imageView;
    @FXML private DatePicker date;
    @FXML private Button back;
    @FXML private TextArea title;
    @FXML private TextArea description;
    @FXML private TextArea venue;
    @FXML private TextArea capacity;
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
    }

    @FXML private void back(ActionEvent actionEvent) {
        back.setOnMouseClicked((event) -> {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });
    }
}
