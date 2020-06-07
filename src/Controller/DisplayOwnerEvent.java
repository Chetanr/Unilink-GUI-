/*
This method displays the event post window
to the owner of the post
 */
package Controller;

import Model.Event;
import Model.GetPost;
import Model.Reply;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DisplayOwnerEvent implements Initializable {

    private static String post;
    private static String user;
    @FXML private ImageView imageView;


    @FXML private Label attendeeList;
    @FXML private Button back;
    @FXML private Label title;
    @FXML private Label description;
    @FXML private Label venue;
    @FXML private Label date;
    @FXML private Label attendeeCount;

    private String title1;
    private String description1;
    private String venue1;
    private String date1;
    private String status1;
    private String postId;
    private String attendeeCount1;
    private String attendeeList1 = "\n";
    private String imageName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (getPost() != null)
        {
            setLabels();

            title.setText(title1);
            description.setText(description1);
            venue.setText(venue1);
            date.setText(date1);
            attendeeCount.setText("Attendee Count:" + attendeeCount1);
            attendeeList.setText("List of attendees:" + attendeeList1);
            File file = new File("/src/Images" + imageName);
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }

    }

    private void setLabels() {
        int i = 0;
        int count = 0;
        String temp = getPost();
        while (i < temp.length())
        {
            String temp1 = "";
            while (i < temp.length() && temp.charAt(i) != '\n')
            {
                temp1 = temp1 + temp.charAt(i);
                i++;
            }
            switch (count) {
                case 0:postId = temp1;
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
                case 3: status1 = temp1;
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
                default:i++;
                    count++;
                    break;
            }
        }
        getAttendeeCount();
    }


    //agetting the attendee count for the event
    private void getAttendeeCount() {
        GetPost getPost = new GetPost();
        getPost.selectDB();
        ArrayList<Event> posts;
        posts = getPost.getEventPost();
        for (Event i : posts)
        {
            if (postId.contains(i.getPostId()))
            {
                attendeeCount1 = String.valueOf(i.getAttendeeCount());
                imageName = i.getFileName();
                break;
            }

        }
        getAttendeeList();
    }


    //getting the list of attendees for the event
    private void getAttendeeList() {
        GetPost getPost = new GetPost();
        getPost.selectDB();
        ArrayList<Reply> posts;
        posts = getPost.getReply();
        for (Reply i : posts)
        {
            if (postId.contains(i.getPostId()))
            {
                attendeeList1 = attendeeList1.concat(i.getAttendeeId().concat("\n"));
            }
        }
    }


    //setter for post variable
    public void setPost(String t1) {
        this.post = t1;
    }


    //setter for userName variable
    public void setUser(String userName) {
        this.user = userName;
    }


    //getter for post variable
    public static String getPost() {
        return post;
    }


    //setter for userName variable
    public static String getUser()
    {
        return user;
    }


    //method for back button
    @FXML private void back(ActionEvent actionEvent) {
        back.setOnMouseClicked((event) ->
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
}
