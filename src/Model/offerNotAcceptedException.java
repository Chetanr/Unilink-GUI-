package Model;

import javafx.scene.control.Alert;

public class offerNotAcceptedException extends Exception {
    public offerNotAcceptedException()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Offer not Acceptable");
        alert.setContentText("The offer entered by you is not acceptable.!");
        alert.show();
    }
}
