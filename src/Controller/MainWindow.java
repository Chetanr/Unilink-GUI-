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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Model.GetPost;

import javafx.scene.image.ImageView;

public class MainWindow implements Initializable {
    @FXML private Button moreDetails;
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
            String creatorid = i.getCreatorId();
            String imageName = i.getFileName();
            populateJobListView(id, title, description, status, proposedPrice, creatorid, imageName);
        }
    }


    //method to populate listview with job posts
    private void populateJobListView(String id, String title, String description, String status, Double proposedPrice, String creatorId, String imageName) {
        String post = jobToString(id, title, description, status, creatorId, proposedPrice);
        ImageView imageView = new ImageView();
        postList.add(post);
        list.setItems(postList);
    }


    //convert the variables to String to display Job Post
    private String jobToString(String id, String title, String description, String status, String creatorId, Double proposedPrice)
    {
        return "Post Id : " + id + "\nTitle : " + title + "\nDescription : " + description + "\nStatus : "
                + status + "\nProposed Price : " + proposedPrice + "\nCreator ID : " + creatorId;
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
            String creatorId = i.getCreatorId();
            String imageName = i.getFileName();
            populateSaleListView(id, title, description, status,askingPrice, creatorId, imageName);
        }

    }


    //method to populate listview with sale posts
    private void populateSaleListView(String id, String title, String description, String status, Double askingPrice, String creatorId, String imageName) {
        String post = saleToString(id, title, description, status, creatorId, askingPrice);
        postList.add(post);
        list.setItems(postList);
    }


    //convert the variables to String to display Sale Post
    private String saleToString(String id, String title, String description, String status, String creatorId, Double askingPrice)
    {
        return "Post Id : " + id + "\nTitle : " + title + "\nDescription : " + description + "\nStatus : "
                + status + "\nAsking Price : " + askingPrice + "\nCreator Id : " + creatorId;
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
            String creatorId = i.getCreatorId();
            String imageName = i.getFileName();
            populateEventListView(id, title, description, status,venue,date, creatorId, imageName);
        }
    }


    //convert the variables to String to display Event Post
    private String eventToString(String id, String title, String description, String status, String venue, String creatorId, String date)
    {
        return "Post Id: " + id + "\nTitle : " + title + "\nDescription : " + description + "\nStatus : " + status
                + "\nVenue: " + venue + "\nDate : " + date + "\nCreator ID : " + creatorId;
    }


    //method to populate listview with event posts
    private void populateEventListView(String id, String title, String description, String status,
                                       String venue, String date,String creatorId, String imageName) {
        ImageView imageView = new ImageView();
        String post = eventToString(id, title, description, status, venue, creatorId, date);
        postList.add(post);
        list.setItems(postList);
    }


    //method for creating a new sale post
    @FXML
    private void eventPost(ActionEvent actionEvent) {
        eventPost.setOnMouseClicked((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EventPost.fxml"));
                loader.load();
                EventPost login = loader.getController();
                login.setUserId(getUserName());
                loader = new FXMLLoader(getClass().getResource("/view/EventPost.fxml"));
                Parent parent = loader.load();

                Scene scene = new Scene(parent);
                Stage stage = (Stage)((Node)(event).getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    //method creating a new sale post
    @FXML
    private void salePost(ActionEvent actionEvent) {
        salePost.setOnMouseClicked((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SalePost.fxml"));
                loader.load();
                SalePost salePost = loader.getController();
                salePost.setUserId(getUserName());
                loader = new FXMLLoader(getClass().getResource("/view/SalePost.fxml"));
                Parent parent = loader.load();

                Scene scene = new Scene(parent);
                Stage stage = (Stage)((Node)(event).getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    //method for creating a new job post
    @FXML private void jobPost(ActionEvent actionEvent) {
        jobPost.setOnMouseClicked((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/JobPost.fxml"));
                loader.load();
                JobPost jobPostPost = loader.getController();
                jobPostPost.setUserId(getUserName());
                loader = new FXMLLoader(getClass().getResource("/view/JobPost.fxml"));
                Parent parent = loader.load();

                Scene scene = new Scene(parent);
                Stage stage = (Stage)((Node)(event).getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
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

     @FXML private void reply(MouseEvent event) {

        String temp = list.getSelectionModel().getSelectedItem();
        System.out.println(temp);

             if (temp.contains("EVE")) {
                 list.getSelectionModel().clearSelection();
                 displayEventReply(temp);
//                 list.getSelectionModel().clearSelection();
             } else if (temp.contains("SAL")) {
                 list.getSelectionModel().clearSelection();
//               list.getItems().clear();
                 displaySaleReply(temp);
//                 list.getSelectionModel().clearSelection();
             } else if (temp.contains("JOB")) {
                 list.getSelectionModel().clearSelection();
//               list.getItems().clear();
                 displayJobReply(temp);
             }
             else {
//               mouseEvent.consume();
             }
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
        list.setOnMouseClicked((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ReplyEventPost.fxml"));
                loader.load();
                ReplyEventPost reply = loader.getController();
                reply.setPost(temp);
                ((Node)(event.getSource())).getScene().getWindow().hide();
                loader = new FXMLLoader(getClass().getResource("/view/ReplyEventPost.fxml"));
                Parent parent = loader.load();

                Scene scene = new Scene(parent);
                Stage stage = (Stage)((Node)(event).getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public void setUser(String userName) {
        this.userName = userName;
    }


    public String getUserName() {
        return this.userName;
    }

    @FXML private void importFromFile(ActionEvent actionEvent) {
    }


    //method to export the post details to file
    @FXML private void exportToFile() throws FileNotFoundException {
        GetPost getPost = new GetPost();
        getPost.selectDB();
        PrintWriter printWriter = new PrintWriter("Posts.txt");

        ArrayList<Event> eventPosts = getPost.getEventPost();
        ArrayList<Sale> salePosts = getPost.getSalePost();
        ArrayList<Job> jobPosts = getPost.getJobPost();

        for ( Event i : eventPosts)
        {
            String id = i.getPostId();
            String title = i.getTitle();
            String description = i.getDescription();
            String status = i.getStatus();
            String venue = i.getVenue();
            String date = i.getDate();
            String imageName = i.getFileName();
            printWriter.println(id + "," + title + "," + description + "," + status + ","
                    + venue + "," + date + "," + imageName);
        }

        for ( Job i : jobPosts)
        {
            String id = i.getPostId();
            String title = i.getTitle();
            String description = i.getDescription();
            String status = i.getStatus();
            Double proposedPrice = i.getProposedPrice();
            String imageName = i.getFileName();
            printWriter.println(id + "," + title + "," + description + "," + status + ","
                    + proposedPrice + "," + imageName);
        }

        for ( Sale i : salePosts)
        {
            String id = i.getPostId();
            String title = i.getTitle();
            String description = i.getDescription();
            String status = i.getStatus();
            Double askingPrice = i.getAskingPrice();
            String imageName = i.getFileName();
            printWriter.println(id + "," + title + "," + description + "," + status + ","
                    + askingPrice + "," + imageName);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Successful");
        alert.setContentText("All the posts have been exported to Posts.txt file successfully");
        alert.showAndWait();

        printWriter.close();
    }

    @FXML private void clear(ActionEvent actionEvent) {
        moreDetails.setOnMouseClicked((event) ->{
            list.getSelectionModel().clearSelection();
                });
    }
}
