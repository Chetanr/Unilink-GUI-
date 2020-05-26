package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

abstract public class Post {

    @FXML protected abstract void upload(ActionEvent actionEvent);

    @FXML protected abstract void closePost(ActionEvent actionEvent);

    @FXML protected abstract void deletePost(ActionEvent actionEvent);

    @FXML protected abstract void savePost(ActionEvent actionEvent);

    protected abstract void saveImage();

    @FXML
    protected abstract void back(ActionEvent actionEvent);
}
