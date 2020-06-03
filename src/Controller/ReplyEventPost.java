package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ReplyEventPost implements Initializable {
    @FXML private Label venue;
    @FXML private Label date;
    @FXML private Label title;
    @FXML private Label description;
    @FXML private ImageView imageView;
    @FXML private Button yes;
    @FXML private Button No;

    private String date1;
    private String title1;
    private String description1;
    private String imageView1;
    private String venue1;

    private static String post;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(getPost()!=null)
        {
            setLabels();
        }
        title.setText(title1);
        description.setText(description1);
        venue.setText(venue1);
        date.setText(date1);


    }

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
                        case 1:
                            title1 = temp1;
                            i++;
                            count++;
                            break;
                        case 2:
                            description1 = temp1;
                            i++;
                            count++;
                            break;
                        case 4:
                            venue1 = temp1;
                            i++;
                            count++;
                            break;
                        case 5:
                            date1 = temp1;
                            i++;
                            count++;
                            break;
                        default:
                            i++;
                            count++;
                            break;
                    }

        }

    }


    @FXML private void confirmAttendence(ActionEvent actionEvent) {
    }

    @FXML private void notAttending(ActionEvent actionEvent) {
    }

    public String getPost() {
        return this.post;
    }

    public void setPost(String post) {
        this.post = post;
    }


}
