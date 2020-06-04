package Model;

import javafx.scene.Node;
import javafx.scene.control.Alert;

public class PostClosedException extends Exception {
    public PostClosedException()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Post closed");
        alert.setContentText("The Post has already been closed. You can no more reply.");
        alert.show();
    }
}
