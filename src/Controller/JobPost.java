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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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

    private static  String userId;


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


    //method for save post button
    @Override
    @FXML protected void savePost(ActionEvent actionEvent) {
        savePost.setOnMouseClicked((event) -> {
            if(checkPrice(proposedPrice.getText()))
            {
                GenerateId id = new GenerateId();
                Job job = new Job (id.getJobId(), title.getText(), description.getText(), Double.parseDouble(proposedPrice.getText()), "OPEN", getUserId(), fileName);
                job.insertDB();
                saveImage();
                FXMLLoader loader = new FXMLLoader();
                try {
                    loader.setLocation(getClass().getResource("/View/MainWindow.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Create Event Post");
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("Could not open MainWindow.fxml");
                    e.printStackTrace();
                }

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

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        JobPost.userId = userId;
    }
}
