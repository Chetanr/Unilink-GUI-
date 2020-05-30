package Controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.event.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.GetPost;
import javafx.util.Callback;

import javax.imageio.ImageIO;
import javax.swing.text.Element;
import javafx.scene.image.ImageView;

public class MainWindow implements Initializable {
    @FXML private ChoiceBox <String> postStatus;
    @FXML private ChoiceBox <String> postCreator;
    @FXML private ChoiceBox <String> postType;
    @FXML private Button jobPost;
    @FXML private Button salePost;
    @FXML private Button eventPost;
    @FXML private Label welcomeLabel;
    @FXML private ListView list;
    @FXML private Button logout;
    @FXML private Menu unilinkMenu;
    @FXML private MenuItem im_port;
    @FXML private MenuItem export;
    @FXML private MenuBar menuBar;
    @FXML private Menu data;


    ObservableList<String> postList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       postType.getItems().add("All Posts");
       postType.getItems().add("Event Post");
       postType.getItems().add("Sale Post");
       postType.getItems().add("Job Post");

       postStatus.getItems().add("Open");
       postStatus.getItems().add("Closed");

       postCreator.getItems().add("My Post");
       postCreator.getItems().add("All Post");


       GetPost getPost = new GetPost();
       getPost.selectDB();
        try {
            displayListView(getPost.getEventPosts());
        } catch (IOException e) {
            e.printStackTrace();
        }
//       list.getItems().add(getPost.getEventPosts());
       Login login = new Login();
       welcomeLabel.setText("Welcome " + login.getUser());
    }

    private void displayListView(StringBuilder eventPosts) throws IOException {
        String[] temp = eventPosts.toString().split(" ");
        String[] temp2 = {"Post ID: ", "Title: ", "Description: ", "Status: ", "Venue: ",
        "Date: ", "Image: "};
        String temp3 = "";
        int k = 0;
        int i = 0;
        int j = 0;
        while(i != temp.length - 1)
        {
            while(j != temp2.length)
            {
                if(!(temp[i].contains("|")))
                {
                    temp3 = temp3.concat(temp2[j++].concat(temp[i++]).concat("\n"));
                }
                else
                {
                    i++;
                }
            }
            postList.add(temp3);
            temp3 = "";
            k++;
            j = 0;
        }
        list.setItems(postList);

        list.setCellFactory(param -> new ListCell<String>()
        {
            private ImageView imageView = new ImageView();
            private Image image;
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (name.equals(postList.toString()))
                    {
                        image = new Image(String.valueOf(getClass().getResource("/src/Images/")));
                        imageView.setImage(image);
                    }
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });
    }



    //method for creating a new sale post
    @FXML
    private void eventPost(ActionEvent actionEvent) {
        eventPost.setOnMouseClicked((event) -> {
            FXMLLoader loader = new FXMLLoader();
            try {
                loader.setLocation(getClass().getResource("/view/EventPost.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setTitle("Create Event Post");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException e) {
                System.out.println("Could not open EventPost.fxml");
                e.printStackTrace();
            }
        });
    }


    //method creating a new sale post
    @FXML
    private void salePost(ActionEvent actionEvent) {
        salePost.setOnMouseClicked((event) -> {
            FXMLLoader loader = new FXMLLoader();
            try {
                loader.setLocation(getClass().getResource("/View/SalePost.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setTitle("Create Event Post");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException e) {
                System.out.println("Could not open SalePost.fxml");
                e.printStackTrace();
            }
        });
    }


    //method for creating a new job post
    @FXML private void jobPost(ActionEvent actionEvent) {
        jobPost.setOnMouseClicked((event) -> {
            FXMLLoader loader = new FXMLLoader();
            try {
                loader.setLocation(getClass().getResource("/View/jobPost.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setTitle("Create Event Post");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException e) {
                System.out.println("Could not open JobPost.fxml");
                e.printStackTrace();
            }
        });
    }


    //quit application
    @FXML
    private void quit(ActionEvent actionEvent) {
        Platform.exit();
    }

    //method for Developer Info button
    @FXML public void devInfo(ActionEvent actionEvent) throws IOException {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/Devinfo.fxml"));
            stage.setTitle("Developer Info");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }




    //method for logout button
    @FXML
    private void logout(ActionEvent actionEvent) {
        logout.setOnMouseClicked((event) -> {
            FXMLLoader loader = new FXMLLoader();
            try {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                loader.setLocation(getClass().getResource("/View/Login.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();


            } catch (IOException e) {
                System.out.println("Could not open Logout.fxml");
                e.printStackTrace();
            }
        });
    }

}
