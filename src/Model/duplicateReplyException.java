package Model;

import javafx.scene.control.Alert;

public class duplicateReplyException extends Exception {
    public duplicateReplyException()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Duplicate reply");
        alert.setContentText("You have already replied to the post.!");
        alert.show();
    }
}
