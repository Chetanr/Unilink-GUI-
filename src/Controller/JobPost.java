/*
This controller class controls as the elements
of the JobPost.fxml file
 */

package Controller;

import Model.Event;
import Model.GenerateId;
import Model.Job;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class JobPost extends Post{


    @FXML private Label proposedPriceError;
    @FXML private ImageView imageView;
    @FXML private Button savePost;
    @FXML private Button deletePost;
    @FXML private Button closePost;
    @FXML private Button upload;
    @FXML private TextArea proposedPrice;
    @FXML private TextArea description;
    @FXML private TextArea title;
    @FXML private Button back;

    private String fileName = "No Preview";
    private FileChooser fileChooser = new FileChooser();
    private File file;


    //method for back to main window button
    @Override
    @FXML protected void back(ActionEvent actionEvent) {
        back.setOnMouseClicked((event) -> {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });
    }


    ///method for upload button
    @Override
    @FXML protected void upload(ActionEvent actionEvent) {
        upload.setOnMouseClicked((event) ->
        {
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
            fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(imageFilter);
            file = fileChooser.showOpenDialog(null);
            fileName = file.getAbsoluteFile().getName();
            if (file != null)
            {
                try {
                    String url = file.toURI().toURL().toString();
                    imageView.setImage(new Image(url));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else
            {

            }
        });
    }


    //method for close post button
    @Override
    @FXML protected void closePost(ActionEvent actionEvent) {
    }


    //method for delete post button
    @Override
    @FXML protected void deletePost(ActionEvent actionEvent) {
    }


    //method for save post button
    @Override
    @FXML protected void savePost(ActionEvent actionEvent) {
        savePost.setOnMouseClicked((event) -> {
            if(checkPrice(proposedPrice.getText()))
            {
                GenerateId id = new GenerateId();
                Login login = new Login();
                Job job = new Job (id.getJobId(), title.getText(), description.getText(), Double.parseDouble(proposedPrice.getText()), "OPEN", login.getUser(), fileName);
                job.insertDB();
                saveImage();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successfully created post");
                alert.setContentText("The Post has been created successfully");
                alert.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            else
            {
                proposedPriceError.setText("Please enter a valid price.!");
            }
        });
    }


    //method to save image in local project folder
    @Override
    protected void saveImage() {
        try
        {
            String path = "./src/Images/" + fileName;
            System.out.println(path);
            File dest = new File(path);
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    @FXML private boolean checkPrice(String area) {
        if(!(area.contains("[0-9]")))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
