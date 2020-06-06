/*
This method handles the replies to
all job posts
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReplyJobPost implements Initializable {

    @FXML private ImageView imageView;
    @FXML private Label title;
    @FXML private Label description;
    @FXML private Label proposedOffer;
    @FXML private TextField offer;
    @FXML private Button cancel;
    @FXML private Button apply;

    private String title1;
    private String description1;
    private String proposedOffer1;
    private Double offer1;
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
            proposedOffer.setText(proposedOffer1);
        }
    }


    ////method to set the labels to display
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
                case 4: proposedOffer1 = temp1;
                    i++;
                    count++;
                    break;
                default: i++;
                    count++;
                    break;
            }
        }
    }


    //method for apply button
    @FXML private void applyToJob(ActionEvent actionEvent) {

        apply.setOnMouseClicked((event) ->
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
            } catch (offerNotAcceptedException e) {
                e.printStackTrace();
            } catch (PostClosedException e) {
                e.printStackTrace();
            } catch (IOException | duplicateReplyException e) {
                e.printStackTrace();
            }
        });
    }


    //method to update replies to job post
    public void updateReply() throws offerNotAcceptedException, PostClosedException, duplicateReplyException {
        GetPost getPost = new GetPost();
        getPost.selectDB();
        ArrayList<Job> posts ;
        posts = getPost.getJobPost();
        Double temp = Double.parseDouble(offer.getText());
        for (Job i : posts)
        {
            if (postId.contains(i.getPostId()))
            {
                if (i.getStatus().equalsIgnoreCase("Open"))
                {
                    if(temp < i.getProposedPrice())
                    {
                        i.setLowestOffer(temp);
                        i.insertReplies(getUser());
                        display();
                    }
                    else if (temp > i.getProposedPrice())
                    {
                        throw new offerNotAcceptedException();
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
    private void display() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setContentText("Your offer has been successfully recorded.");
        alert.showAndWait();
    }


    //method for cancel button
    @FXML private void cancel(ActionEvent actionEvent) {
        cancel.setOnMouseClicked((event) ->
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


    //getter for user variable
    public String getUser() {
        return this.user;
    }


    //setter for user variable
    public void setUser(String user) {
        this.user = user;
    }


    //getter for post variable
    public static String getPost() {
        return post;
    }


    //getter for post variable
    public static void setPost(String post) {
        ReplyJobPost.post = post;
    }


}
