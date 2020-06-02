package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ReplyJobPost {

    @FXML private ImageView imageView;
    @FXML private Label title;
    @FXML private Label description;
    @FXML private Label proposedOffer;
    @FXML private TextField offer;
    @FXML private Button cancel;
    @FXML private Button apply;

    @FXML private void applyToJob(ActionEvent actionEvent) {
    }

    @FXML private void cancel(ActionEvent actionEvent) {
    }
}
