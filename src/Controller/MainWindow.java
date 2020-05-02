package Controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.event.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    @FXML private Menu uniLink;
    @FXML private MenuItem developerInfo;
    @FXML private MenuItem quit;
    @FXML private MenuItem im_port;
    @FXML private MenuItem export;
    @FXML private MenuBar menuBar;
    @FXML private Menu data;
    @FXML private ComboBox <String> postType;
    @FXML private ComboBox <String> postStatus;
    @FXML private ComboBox <String> postCreator;

    ObservableList<String> postTypeList = FXCollections.observableArrayList("Event Post", "Sale Post", "Job Post");
    ObservableList<String> postStatusList = FXCollections.observableArrayList("Open", "Closed");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        postType.setItems(postTypeList);
        postStatus.setItems(postStatusList);
    }


    @FXML private void eventPost(ActionEvent actionEvent) {
    }

    @FXML private void salePost(ActionEvent actionEvent) {
    }

    @FXML private void jobPost(ActionEvent actionEvent) {
    }

    @FXML private void quitUnilink(ActionEvent actionEvent) {

    }

    public void quit(ActionEvent actionEvent) {

    }

    public void devInfo(ActionEvent actionEvent) {
    }


}
