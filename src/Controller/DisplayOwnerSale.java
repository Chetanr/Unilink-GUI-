/*
This method displays the sale post window
to the owner of the post
 */

package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DisplayOwnerSale implements Initializable {

    private static String post;
    private static String user;

    @FXML private Label title;
    @FXML private Label description;
    @FXML private Label askingPrice;
    @FXML private Label offerList;
    @FXML private Button back;

    private String postId;
    private String title1;
    private String description1;
    private String status1;
    private String askingPrice1;
    private String offerList1 = "List of Offers:\n";
    private String imageName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (getPost() != null)
        {
            setLabels();

            title.setText(title1);
            description.setText(description1);
            askingPrice.setText(askingPrice1);
            offerList.setText(offerList1);
//            date.setText(date1);
//            attendeeCount.setText("Attendee Count:" + attendeeCount1);
//            attendeeList.setText("List of attendees:" + attendeeList1);
        }
    }


    //method to set the labels
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
                case 4: askingPrice1 = temp1;
                    i++;
                    count++;
                    break;
                default:i++;
                    count++;
                    break;
            }
        }
        getImageName();
        getOffers();
    }


    private void getImageName() {
        GetPost getPost = new GetPost();
        getPost.selectDB();
        ArrayList<Job> posts;
        posts = getPost.getJobPost();
        for (Job i : posts) {
            if (postId.contains(i.getPostId())) {
                imageName = i.getFileName();
                break;
            }
        }
    }


    private void getOffers() {
        GetPost getPost = new GetPost();
        getPost.selectDB();
        String responderId;
        Double offer;
        String temp;
        ArrayList<Reply> posts;
        posts = getPost.getReply();
        for (Reply i : posts)
        {
            temp = i.getPostId();
            if (postId.contains(temp))
            {
                responderId = i.getAttendeeId();
                offer = i.getSaleOffer();
                temp = responderId.concat(" : ".concat(String.valueOf(offer)));
                offerList1 = offerList1.concat(temp.concat("\n"));
            }
        }
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
