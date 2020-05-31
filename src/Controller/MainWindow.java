/*
This controller class controls as the elements
of the MainWindow.fxml file
 */


package Controller;

import com.sun.tools.javac.Main;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.Background;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.GetPost;

import javafx.scene.image.ImageView;

public class MainWindow implements Initializable {
    @FXML private ChoiceBox <String> postStatus;
    @FXML private ChoiceBox <String> postCreator;
    @FXML private ChoiceBox <String> postType;
    @FXML private Button jobPost;
    @FXML private Button salePost;
    @FXML private Button eventPost;
    @FXML private Label welcomeLabel;
    @FXML private ListView list = null;
    @FXML private Button logout;
    @FXML private Menu unilinkMenu;
    @FXML private MenuItem im_port;
    @FXML private MenuItem export;
    @FXML private MenuBar menuBar;
    @FXML private Menu data;


    ObservableList<String> postList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        GetPost getPost = new GetPost();
        getPost.selectDB();

        postType.getItems().add("All");
        postType.getItems().add("Event");
        postType.getItems().add("Sale");
        postType.getItems().add("Job");
        postType.setValue("All");

        postStatus.getItems().add("All");
        postStatus.getItems().add("Open");
        postStatus.getItems().add("Closed");
        postStatus.setValue("All");

        postCreator.getItems().add("My Post");
        postCreator.getItems().add("All Post");
        postCreator.setValue("All Posts");

//        postType.showingProperty().addListener((v, olderValue, newValue)->
//        {
//            if (newValue.equals("Event")) {
//                try {
//                    displayEventPost(getPost.getEventPosts());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            else if (newValue.equals("Sale"))
//                displaySalePost(getPost.getSalePosts());
//            else if (newValue.equals("Job"))
//                displayJobPost(getPost.getJobPosts());
//        });

        postType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.equals("Event")) {
                    list.getItems().clear();
                try {
                    displayEventPost(getPost.getEventPosts());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (t1.equals("Sale"))
                {
                    list.getItems().clear();
                    displaySalePost(getPost.getSalePosts());
                }
            else if (t1.equals("Job"))
                {
                    list.getItems().clear();
                    displayJobPost(getPost.getJobPosts());
                }
            else
                {
                    list.getItems().clear();
                    displayJobPost(getPost.getJobPosts());
                    try {
                        displayEventPost(getPost.getEventPosts());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    displaySalePost(getPost.getSalePosts());
                }

            }
        });




//        Platform.runLater(() -> {
//            try {
//                displayEventPost(getPost.getEventPosts());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            displayJobPost(getPost.getJobPosts());
//            displaySalePost(getPost.getSalePosts());
//        });
        Login login = new Login();
       welcomeLabel.setText("Welcome " + login.getUser());
    }

    //displaying the job posts on listview
    private void displayJobPost(StringBuilder jobPosts) {
        String[] finalTemp = setJob(jobPosts);
        populateListView(finalTemp);
//        list.setItems(postList);
//        list.setCellFactory(param -> new ListCell<String>()
//        {
//            private ImageView imageView = new ImageView();
//            @Override
//            public void updateItem(String name, boolean empty) {
//                super.updateItem(name, empty);
//
//                Image image = null;
//                if (empty) {
//                    setText(null);
//                    setGraphic(null);
//                }
//                else {
//                    for (String i : finalTemp)
//                    {
//                        if (i != null)
//                        {
//                            String path = "./src/Images/" + i;
//                            File dest = new File(path);
//                            try {
//                                image = new Image(dest.toURI().toURL().toString());
//                            } catch (MalformedURLException e) {
//                                e.printStackTrace();
//                            }
//                            imageView.setImage(image);
//                            imageView.setFitHeight(50);
//                            imageView.setFitWidth(50);
//                            setStyle("-fx-background-color: #8f8;");
//                        }
////                        else if (i == null)
////                        {
////                            break;
////                        }
//
//                    }
//                    setText(name);
//                    setGraphic(imageView);
//
//                }
//            }
//        });
    }

    private String[] setJob(StringBuilder jobPosts) {
        String[] temp = jobPosts.toString().split(" ");
        String[] temp2 = {"Post ID: ", "Title: ", "Description: ", "Status: ", "Proposed Offer: ", "Image: "};
        String temp3 = "";
        String[] temp4 = new String[temp.length];
        int k = 0;
        int i = 0;
        int j = 0;
        int l = 0;
        while(i != temp.length - 1)
        {
            while(j != temp2.length)
            {
                if(!(temp[i].contains("|")))
                {
                    if(temp2[j].contains("Image"))
                    {
                        temp4[l++] = temp[i++];
                        j++;
                    }
                    else
                    {
                        temp3 = temp3.concat(temp2[j++].concat(temp[i++]).concat("\n"));
                    }

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
        return temp4;
    }


    //displaying the sale posts on listview
    private void displaySalePost(StringBuilder jobPosts) {
        String[] finalTemp = setSale(jobPosts);
        populateListView(finalTemp);
//        list.setItems(postList);
//        list.setCellFactory(param -> new ListCell<String>()
//        {
//            private ImageView imageView = new ImageView();
//            @Override
//            public void updateItem(String name, boolean empty) {
//                super.updateItem(name, empty);
//                Image image = null;
//                if (empty) {
//                    setText(null);
//                    setGraphic(null);
//                }
//                else {
//                    for (String i : finalTemp)
//                    {
//                        if (i != null)
//                        {
//                            String path = "./src/Images/" + i;
//                            File dest = new File(path);
//                            try {
//                                image = new Image(dest.toURI().toURL().toString());
//                            } catch (MalformedURLException e) {
//                                e.printStackTrace();
//                            }
//                            imageView.setImage(image);
//                            imageView.setFitHeight(50);
//                            imageView.setFitWidth(50);
//                        }
//                        else if (i == null)
//                        {
//                            break;
//                        }
//
//                    }
//                    setText(name);
//                    setGraphic(imageView);
//                    setStyle("-fx-background-color: #AAAA;");
//                }
//            }
//        });
    }

    private String[] setSale(StringBuilder jobPosts) {
        String[] temp = jobPosts.toString().split(" ");
        String[] temp2 = {"Post ID: ", "Title: ", "Description: ", "Status: ", "Asking Price: ", "Image: "};
        String temp3 = "";
        String[] temp4 = new String[temp.length];
        int k = 0;
        int i = 0;
        int j = 0;
        int l = 0;
        while(i != temp.length - 1)
        {
            while(j != temp2.length)
            {
                if(!(temp[i].contains("|")))
                {
                    if(temp2[j].contains("Image"))
                    {
                        temp4[l++] = temp[i++];
                        j++;
                    }
                    else
                    {
                        temp3 = temp3.concat(temp2[j++].concat(temp[i++]).concat("\n"));
                    }

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
        return temp4;
    }

    private void populateListView(String[] finalTemp)
    {
        list.setItems(postList);
        list.setCellFactory(param -> new ListCell<String>()
        {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                Image image = null;
                if (empty) {
                    setText(null);
                    setGraphic(null);
                }
                else {
                    for (String i : finalTemp)
                    {
                        if (i != null)
                        {
                            String path = "./src/Images/" + i;
                            File dest = new File(path);
                            try {
                                image = new Image(dest.toURI().toURL().toString());
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                            imageView.setImage(image);
                            imageView.setFitHeight(50);
                            imageView.setFitWidth(50);
                        }
                        else if (i == null)
                        {
                            break;
                        }

                    }
                    setText(name);
                    setGraphic(imageView);
                    setStyle("-fx-background-color: #8f8;");
                }
            }
        });
    }

    //displaying the event posts on listview
    private void displayEventPost(StringBuilder Posts) throws IOException {
        String[] finalTemp = setlistView(Posts);
        populateListView(finalTemp);
//        list.setItems(postList);
//        list.setCellFactory(param -> new ListCell<String>()
//        {
//            private ImageView imageView = new ImageView();
//            @Override
//            public void updateItem(String name, boolean empty) {
//                super.updateItem(name, empty);
//                Image image = null;
//                if (empty) {
//                    setText(null);
//                    setGraphic(null);
//                }
//                else {
//                    for (String i : finalTemp)
//                    {
//                        if (i != null)
//                        {
//                            String path = "./src/Images/" + i;
//                            File dest = new File(path);
//                            try {
//                                image = new Image(dest.toURI().toURL().toString());
//                            } catch (MalformedURLException e) {
//                                e.printStackTrace();
//                            }
//                            imageView.setImage(image);
//                            imageView.setFitHeight(50);
//                            imageView.setFitWidth(50);
//                        }
//                        else if (i == null)
//                        {
//                            break;
//                        }
//
//                    }
//                    setText(name);
//                    setGraphic(imageView);
//                    setStyle("-fx-background-color: #8f8;");
//                }
//            }
//        });
    }

    private String[] setlistView(StringBuilder eventPosts) {
        String[] temp = eventPosts.toString().split(" ");
        String[] temp2 = {"Post ID: ", "Title: ", "Description: ", "Status: ", "Venue: ",
                "Date: ", "Image: "};
        String temp3 = "";
        String[] temp4 = new String[temp.length];
        int k = 0;
        int i = 0;
        int j = 0;
        int l = 0;
        while(i != temp.length - 1)
        {
            while(j != temp2.length)
            {
                if(!(temp[i].contains("|")))
                {
                    if(temp2[j].contains("Image"))
                    {
                        temp4[l++] = temp[i++];
                        j++;
                    }
                    else
                    {
                        temp3 = temp3.concat(temp2[j++].concat(temp[i++]).concat("\n"));
                    }

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
        return temp4;
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
