/*
This controller class controls as the elements
of the MainWindow.fxml file
 */


package Controller;

import Model.Event;
import Model.Job;
import Model.Sale;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    @FXML private ListView<String> list = null;
    @FXML private Button logout;
    @FXML private Menu unilinkMenu;
    @FXML private MenuItem im_port;
    @FXML private MenuItem export;
    @FXML private MenuBar menuBar;
    @FXML private Menu data;

    private static String userName;


    ObservableList<String> postList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GetPost getPost = new GetPost();
        getPost.selectDB();

        initialiseFilters();

        checkPostFilters(getPost);

        welcomeLabel.setText("Welcome " + getUserName() + ".!");

    }

    private void checkPostFilters(GetPost getPost) {
        postType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.equals("Event")) {
                    list.getItems().clear();
                    try {
                        displayEventPost(getPost.getEventPost());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (t1.equals("Sale"))
                {
                    list.getItems().clear();
                    displaySalePost(getPost.getSalePost());
                }
                else if (t1.equals("Job"))
                {
                    list.getItems().clear();
                    displayJobPost(getPost.getJobPost());
                }
                else
                {
                    list.getItems().clear();
                    displayJobPost(getPost.getJobPost());
                    try {
                        displayEventPost(getPost.getEventPost());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    displaySalePost(getPost.getSalePost());
                }
            }
        });
    }

    private void initialiseFilters() {
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
    }


    //display listview with job posts
    private void displayJobPost(ArrayList<Job> jobPosts) {
        for (Job i : jobPosts)
        {
            String id = i.getPostId();
            String title = i.getTitle();
            String description = i.getDescription();
            String status = i.getStatus();
            Double proposedPrice = i.getProposedPrice();
            String image_name = i.getFileName();
            populateJobListView(id, title, description, status, proposedPrice, image_name);
        }
    }


    //method to populate listview with job posts
    private void populateJobListView(String id, String title, String description, String status, Double proposedPrice, String image_name) {
        String post = jobToString(id, title, description, status, proposedPrice);
        ImageView imageView = new ImageView();
        postList.add(post);
        list.setItems(postList);
    }


    //convert the variables to String to display Job Post
    private String jobToString(String id, String title, String description, String status, Double proposedPrice)
    {
        return "Post Id : " + id + "\nTitle : " + title + "\nDescription : " + description + "\nStatus : "
                + status + "\nProposed Price : " + proposedPrice;
    }


    //displaying the sale posts on listview
    private void displaySalePost(ArrayList<Sale> salePosts) {
        for (Sale i : salePosts)
        {
            String id = i.getPostId();
            String title = i.getTitle();
            String description = i.getDescription();
            String status = i.getStatus();
            Double askingPrice = i.getAskingPrice();
            String image_name = i.getFileName();
            populateSaleListView(id, title, description, status,askingPrice, image_name);
        }

    }


    //method to populate listview with sale posts
    private void populateSaleListView(String id, String title, String description, String status, Double askingPrice, String image_name) {
        String post = saleToString(id, title, description, status, askingPrice);
        postList.add(post);
        list.setItems(postList);
    }


    //convert the variables to String to display Sale Post
    private String saleToString(String id, String title, String description, String status, Double askingPrice)
    {
        return "Post Id : " + id + "\nTitle : " + title + "\nDescription : " + description + "\nStatus : "
                + status + "\nAsking Price : " + askingPrice;
    }


    //displaying the event posts on listview
    private void displayEventPost(ArrayList<Event> eventPosts) throws IOException {
        for (Event i : eventPosts)
        {
            String id = i.getPostId();
            String title = i.getTitle();
            String description = i.getDescription();
            String status = i.getStatus();
            String venue = i.getVenue();
            String date = i.getDate();
            String image_name = i.getFileName();
            populateEventListView(id, title, description, status,venue,date, image_name);
        }
    }


    //convert the variables to String to display Event Post
    private String eventToString(String id, String title, String description, String status, String venue, String date)
    {
        return "Post Id: " + id + "\nTitle : " + title + "\nDescription : " + description + "\nStatus : " + status
                + "\nVenue: " + venue + "\nDate : " + date;
    }


    //method to populate listview with event posts
    private void populateEventListView(String id, String title, String description, String status,
                                  String venue, String date, String image_name) {
        ImageView imageView = new ImageView();
        String post = eventToString(id, title, description, status,venue,date);
        postList.add(post);
        list.setItems(postList);
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

    @FXML private void reply() {
        list.setOnMouseClicked(mouseEvent1 -> {
            String temp = list.getSelectionModel().getSelectedItem();
            System.out.println(temp);

            if (temp.contains("EVE")) {
                displayEventReply(temp);
                list.getSelectionModel().clearSelection();
            } else if (temp.contains("SAL")) {
                displaySaleReply(temp);
                list.getSelectionModel().clearSelection();
            } else if (temp.contains("JOB")) {
                displayJobReply(temp);
                list.getSelectionModel().clearSelection();
            }
            else
            {
//                mouseEvent.consume();
                mouseEvent1.consume();
            }
            list.getSelectionModel().clearSelection();
        });
    }


    //to display ReplyJobPost.fxml window so that one can reply to job posts
    private void displayJobReply(String temp) {
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FXMLLoader loader = new FXMLLoader();
                try {
                    loader.setLocation(getClass().getResource("/View/ReplyJobPost.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("Could not open ReplyJobPost.fxml");
                    e.printStackTrace();
                }
            }
        });
    }


    //to display ReplySalePost.fxml window so that one can reply to sale posts
    private void displaySaleReply(String temp) {
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FXMLLoader loader = new FXMLLoader();
                try {
                    loader.setLocation(getClass().getResource("/View/ReplySalePost.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("Could not open ReplySalePost.fxml");
                    e.printStackTrace();
                }
            }
        });
    }


    //to display ReplyEventPost.fxml window so that one can reply to event posts
    private void displayEventReply(String temp) {
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FXMLLoader loader = new FXMLLoader();
                try {
//                    ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
                    loader.setLocation(getClass().getResource("/View/ReplyEventPost.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("Could not open ReplyEventPost.fxml");
                    e.printStackTrace();
                }
            }
        });
    }


    public void setUser(String userName) {
        this.userName = userName;
    }


    public String getUserName() {
        return this.userName;
    }
}
