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
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

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

    private static String userName;
    private String postCreatorFilter = "all";
    private String postStausFilter = "all";
    private String postTypeFilter = "all";


    ObservableList<String> postList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        GetPost getPost = new GetPost();
        getPost.selectDB();

        try {
            displayEventPost(getPost.getEventPost());
        } catch (IOException e) {
            e.printStackTrace();
        }
        displaySalePost(getPost.getSalePost());
        displayJobPost(getPost.getJobPost());

        initialiseFilters();



        checkPostFilters(getPost);
        welcomeLabel.setText("Welcome " + getUserName() + ".!");
        reply();

    }



    private void checkPostFilters(GetPost getPost) {
        postType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.equalsIgnoreCase("Event")) {
                    list.getItems().clear();
                    try {
                        postTypeFilter = "Event";
                        displayEventPost(getPost.getEventPost());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (t1.equalsIgnoreCase("Sale"))
                {
                    list.getItems().clear();
                    displaySalePost(getPost.getSalePost());
                }
                else if (t1.equalsIgnoreCase("Job"))
                {
                    list.getItems().clear();
                    displayJobPost(getPost.getJobPost());
                }
                else
                {
                    list.getItems().clear();
                    try {
                        displayEventPost(getPost.getEventPost());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    displaySalePost(getPost.getSalePost());
                    displayJobPost(getPost.getJobPost());

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
            populateEventListView(id, title, description, status, venue, date, creatorId, imageName);
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

     @FXML private void reply() {
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.contains("EVE")) {
                    list.getSelectionModel().clearSelection();
                    if (t1.contains(userName))
                    {
                        displayOwnerEvent(t1);
                    }
                    else
                    {
                        displayEventReply(t1);
                    }
                } else if (t1.contains("SAL")) {
                    list.getSelectionModel().clearSelection();
                    if (t1.contains(userName))
                    {
                        displayOwnerSale(t1);
                    }
                    else
                    {
                        displaySaleReply(t1);
                    }

                } else if (t1.contains("JOB")) {
                    list.getSelectionModel().clearSelection();
                    if (t1.contains(userName))
                    {
                        displayOwnerJob(t1);
                    }
                    else
                    {
                        displayJobReply(t1);
                    }
                }
            }
        });
    }

    private void displayOwnerJob(String t1) {
        list.setOnMouseClicked((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DisplayOwnerJob.fxml"));
                loader.load();
                DisplayOwnerJob reply = loader.getController();
                reply.setPost(t1);
                reply.setUser(getUserName());
                ((Node)(event.getSource())).getScene().getWindow().hide();
                loader = new FXMLLoader(getClass().getResource("/View/DisplayOwnerJob.fxml"));
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

    private void displayOwnerSale(String t1) {
        list.setOnMouseClicked((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DisplayOwnerSale.fxml"));
                loader.load();
                DisplayOwnerSale reply = loader.getController();
                reply.setPost(t1);
                reply.setUser(getUserName());
                ((Node)(event.getSource())).getScene().getWindow().hide();
                loader = new FXMLLoader(getClass().getResource("/View/DisplayOwnerSale.fxml"));
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

    private void displayOwnerEvent(String t1) {
        list.setOnMouseClicked((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DisplayOwnerEvent.fxml"));
                loader.load();
                DisplayOwnerEvent reply = loader.getController();
                reply.setPost(t1);
                reply.setUser(getUserName());
                ((Node)(event.getSource())).getScene().getWindow().hide();
                loader = new FXMLLoader(getClass().getResource("/View/DisplayOwnerEvent.fxml"));
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


    //to display ReplyJobPost.fxml window so that one can reply to job posts
    private void displayJobReply(String temp) {
        list.setOnMouseClicked((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ReplyJobPost.fxml"));
                loader.load();
                ReplyJobPost reply = loader.getController();
                reply.setPost(temp);
                reply.setUser(getUserName());
                ((Node)(event.getSource())).getScene().getWindow().hide();
                loader = new FXMLLoader(getClass().getResource("/view/ReplyJobPost.fxml"));
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


    //to display ReplySalePost.fxml window so that one can reply to sale posts
    private void displaySaleReply(String temp) {
        list.setOnMouseClicked((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ReplySalePost.fxml"));
                loader.load();
                ReplySalePost reply = loader.getController();
                reply.setPost(temp);
                reply.setUser(getUserName());
                ((Node)(event.getSource())).getScene().getWindow().hide();
                loader = new FXMLLoader(getClass().getResource("/view/ReplySalePost.fxml"));
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


    //to display ReplyEventPost.fxml window so that one can reply to event posts
    private void displayEventReply(String temp) {
        list.setOnMouseClicked((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ReplyEventPost.fxml"));
                loader.load();
                ReplyEventPost reply = loader.getController();
                reply.setPost(temp);
                reply.setUser(getUserName());
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
            String creatorId = i.getCreatorId();
            int attendeeCount = i.getAttendeeCount();
            String imageName = i.getFileName();
            printWriter.println(id + "," + title + "," + description + "," + status + ","
                    + venue + "," + date + "," + creatorId + "," + attendeeCount + "," + imageName);
        }

        for ( Job i : jobPosts)
        {
            String id = i.getPostId();
            String title = i.getTitle();
            String description = i.getDescription();
            String status = i.getStatus();
            Double proposedPrice = i.getProposedPrice();
            String creatorId = i.getCreatorId();
            String imageName = i.getFileName();
            printWriter.println(id + "," + title + "," + description + "," + proposedPrice
                    + "," + status + "," + creatorId + "," + imageName);
        }

        for ( Sale i : salePosts)
        {
            String id = i.getPostId();
            String title = i.getTitle();
            String description = i.getDescription();
            String status = i.getStatus();
            Double askingPrice = i.getAskingPrice();
            Double minimumRaise = i.getMinimumRaise();
            String creatorId = i.getCreatorId();
            String imageName = i.getFileName();
            printWriter.println(id + "," + title + "," + description + "," + askingPrice + ","
                    + minimumRaise + "," + status + "," + creatorId + "," + imageName);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Successful");
        alert.setContentText("All the posts have been exported to Posts.txt file successfully");
        alert.showAndWait();

        printWriter.close();
    }


    //method to import the data from an external text file
    public void importFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(imageFilter);
        File file = fileChooser.showOpenDialog(null);
        String fileName = file.getAbsoluteFile().getName();
        if (file != null)
        {
            try {
                insertIntoDb(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //method to insert data into database after reading the data
    private void insertIntoDb(String fileName) throws IOException {
        Scanner sc = new Scanner(new File(fileName));
        while(sc.hasNextLine())
        {
            String [] words = new String[0];
            Scanner s = new Scanner(new File(fileName));

                words = sc.nextLine().split(",");

                if (words[0].contains("EVE"))
                {
                    Event event = new Event(words[0], words[1], words[2],words[3], words[4]
                    , Integer.parseInt(words[5]), words[6], words[7], words[8]);
                    event.insertDB();
                }
                else if(words[0].contains("SAL"))
                {
                    Sale sale = new Sale(words[0], words[1], words[2],Double.parseDouble(words[3]),
                            Double.parseDouble(words[4]), words[5], words[6], words[7]);
                    sale.insertDB();
                }
                else if(words[0].contains("JOB"))
                {
                    Job job = new Job(words[0], words[1], words[2],Double.parseDouble(words[3]),
                            words[4], words[5], words[6]);
                    job.insertDB();
                }
                s.close();
        }
        sc.close();

    }
}
