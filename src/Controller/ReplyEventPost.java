/*
This method handles the replies to
all event posts
 */

package Controller;

import Model.Event;
import Model.PostClosedException;
import Model.duplicateReplyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.GetPost;

public class ReplyEventPost implements Initializable {
    @FXML private Label venue;
    @FXML private Label date;
    @FXML private Label title;
    @FXML private Label description;
    @FXML private ImageView imageView;
    @FXML private Button yes;
    @FXML private Button no;

    private String date1;
    private String title1;
    private String description1;
    private String imageView1;
    private String venue1;
    private String postId;
    private String status;
    private static String user;
    private static String post;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(getPost()!=null)
        {
            setLabels();
            title.setText(title1);
            description.setText(description1);
            venue.setText(venue1);
            date.setText(date1);
        }
    }


    //method to set the labels to display
    private void setLabels() {
        int i = 0;
        int count = 0;
        String temp = getPost();
        while (i < temp.length())
        {
            String temp1 = "";
            while (i < temp.length() && temp.charAt(i) != '\n') {
                temp1 = temp1 + temp.charAt(i);
                i++;
            }
                    switch (count) {
                        case 0 : postId = temp1;
                                 i++;
                                 count++;
                                 break;

                        case 1: title1 = temp1;
                                i++;
                                count++;
                                break;
                        case 2: description1 = temp1;
                                i++;
                                count++;
                                break;
                        case 3: status = temp1;
                                i++;
                                count++;
                                break;
                        case 4: venue1 = temp1;
                                i++;
                                count++;
                                break;
                        case 5: date1 = temp1;
                                i++;
                                count++;
                                break;
                        default: i++;
                                count++;
                                break;
                    }
        }
    }


    //method for yes button
    @FXML private void confirmAttendence(ActionEvent actionEvent)  {
        yes.setOnMouseClicked((event) ->
        {
            try {
                updateReply();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
                loader.load();
                MainWindow login = loader.getController();
                login.setUser(getUser());
                loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
                Parent parent = loader.load();

                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) (event).getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException | PostClosedException e) {
                e.printStackTrace();
            } catch (duplicateReplyException e) {
                e.printStackTrace();
            }
        });
    }


    //method to update the replies to the event  post
    private void updateReply() throws PostClosedException, duplicateReplyException {
        GetPost getPost = new GetPost();
        getPost.selectDB();
        ArrayList<Event> posts ;
        posts = getPost.getEventPost();
        for (Event i : posts)
        {
            if (postId.contains(i.getPostId()))
            {
                if(i.getStatus().equalsIgnoreCase("OPEN"))
                {
                    if (i.getCapacity() >= i.getAttendeeCount())
                    {
                        i.setAttendeeCount(1);
                        if (i.getAttendeeCount() == i.getCapacity())
                        {
                            i.setStatus("CLOSED");
                        }
                        i.insertReplies(getUser());
                        display();
                    }
                }
                else
                {
                    throw new PostClosedException();
                }
            }
        }
    }


    //display after successful reply to the event post
    @FXML private void display() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setContentText("Your event attendence has been successfully recorded.");
        alert.showAndWait();
    }


    //method for no button
    @FXML private void notAttending(ActionEvent actionEvent) {
        no.setOnMouseClicked((event) ->
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
                loader.load();
                ((Node)(event.getSource())).getScene().getWindow().hide();
                loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
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


    //getter for post variable
    public String getPost() {
        return this.post;
    }


    //setter for post variable
    public void setPost(String post) {
        this.post = post;
    }


    //getter for user variable
    public String getUser() {
        return this.user;
    }


    //setter for user variable
    public void setUser(String user) {
        this.user = user;
    }
}
