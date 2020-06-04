/*
This method handles the replies to
all sale posts
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

public class ReplySalePost implements Initializable {
    @FXML private Button makeOffer;
    @FXML private Label title;
    @FXML private Label askingPrice;
    @FXML private Button cancel;
    @FXML private TextField offer;
    @FXML private ImageView imageView;
    @FXML private Label description;

    private String title1;
    private String description1;
    private String askingPrice1;
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
            askingPrice.setText(askingPrice1);
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
                case 4: askingPrice1 = temp1;
                    i++;
                    count++;
                    break;
                default: i++;
                    count++;
                    break;
            }
        }
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

    //method for makeOffer button
    @FXML private void makeOffer(ActionEvent actionEvent) {
        makeOffer.setOnMouseClicked((event) ->
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
            } catch (offerNotAcceptedException e) {
                e.printStackTrace();
            }
        });
    }


    //method to update the replies to the sale  post
    private void updateReply() throws PostClosedException, duplicateReplyException, offerNotAcceptedException {
        GetPost getPost = new GetPost();
        getPost.selectDB();
        ArrayList<Sale> posts ;
        posts = getPost.getSalePost();
        Double temp = Double.parseDouble(offer.getText());
        for (Sale i : posts)
        {
            if(postId.contains(i.getPostId()))
            {
                if (i.getStatus().equalsIgnoreCase("OPEN"))
                {
                    if (temp < i.getMinimumRaise())
                    {
                        throw new offerNotAcceptedException();
                    }
                    else if (temp > i.getMinimumRaise())
                    {
                        if ( i.getHighestOffer() - temp  < i.getAskingPrice())
                        {
                            if (temp >= i.getAskingPrice())
                            {
                                i.setHighestOffer (temp);
                                i.setStatus("CLOSED");
                                i.insertReplies(user);
                                display();
                            }
                            else
                            {
                                i.setHighestOffer (temp);
                                throw new offerNotAcceptedException();
                            }
                        }
                    }
                }
                else
                {
                    throw new PostClosedException();
                }
            }
        }
    }


    //display after successful reply to the sale post
    @FXML private void display() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setContentText("Your offer has been successfully recorded.");
        alert.showAndWait();
    }


    //getter for the post variable
    public String getPost() {
        return this.post;
    }


    //setter for tge post variable
    public void setPost(String post) {
        this.post = post;
    }


    //getter for the user variable
    public String getUser() {
        return user;
    }


    //setter for the user variable
    public void setUser(String user) {
        this.user = user;
    }

}
