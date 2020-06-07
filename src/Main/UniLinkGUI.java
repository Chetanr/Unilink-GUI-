package Main;

import Model.CreateTable;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class UniLinkGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(root);

            stage.setTitle("Unilink");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Unable to load the fxml file.!");
        }

    }
}
