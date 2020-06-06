package Controller;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DisplayOwnerJob implements Initializable {

    private static String post;
    private static String user;

    @FXML private Label title;
    @FXML private Label description;
    @FXML private Label offer;
    @FXML private Button back;
    @FXML private Label offers;

    private String postId;
    private String title1;
    private String description1;
    private String status1;
    private String offer1;
    private String offers1 = "List of Offers:\n";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (getPost() != null)
        {
            setLabels();

            title.setText(title1);
            description.setText(description1);
            offer.setText(offer1);
            offers.setText(offers1);
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
                case 4: offer1 = temp1;
                    i++;
                    count++;
                    break;
                default:i++;
                    count++;
                    break;
            }
        }
        getOffers();
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
                offer = i.getJobOffer();
                temp = responderId.concat(" : ".concat(String.valueOf(offer)));
                offers1 = offers1.concat(temp.concat("\n"));
            }
        }
    }

    public static String getPost() {
        return post;
    }


    public void setPost(String post) {
        this.post = post;
    }

    public void setUser(String userName) {
        this.user = userName;
    }

    public String getUser() {
        return this. user;
    }

    public void back(ActionEvent actionEvent) {
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
