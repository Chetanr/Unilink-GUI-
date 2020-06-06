/*
This controller class controls as the elements
of the EventPost.fxml file
 */

package Controller;

import Model.Event;
import Model.GenerateId;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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


public class EventPost extends Post{
    @FXML private Label capacityError;
    @FXML private ImageView imageView;
    @FXML private DatePicker date;
    @FXML private Button back;
    @FXML private TextField title;
    @FXML private TextField description;
    @FXML private TextField venue;
    @FXML private TextField capacity;
    @FXML private Button upload;
    @FXML private Button closePost;
    @FXML private Button deletePost;
    @FXML private Button savePost;

    private static String userId;


    private String fileName = "No Preview";
    private FileChooser fileChooser = new FileChooser();
    private File file;


    //method for upload button
    @Override
    @FXML public void upload(ActionEvent actionEvent) {
        upload.setOnMouseClicked((event) ->
        {
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
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
        });
    }


    //method for close post button
    @Override
    @FXML public void closePost(ActionEvent actionEvent) {
    }

    //method for delete post button
    @Override
    @FXML public void deletePost(ActionEvent actionEvent) {
    }

    //method for sale post button
    @Override
    @FXML public void savePost(ActionEvent actionEvent) {
        savePost.setOnMouseClicked((event) -> {
            if (checkCapacity())
            {
                GenerateId id = new GenerateId();
                Event ev = new Event (id.getId(), title.getText(), description.getText(), venue.getText(), date.getValue().toString(), Integer.parseInt(capacity.getText()), "OPEN", getUserId(), fileName );
                ev.insertDB();
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
                capacityError.setText("Please enter a number");
            }
        });
    }



    //method for save image button
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


    //method for back to main window button
    @Override
    @FXML
    protected void back(ActionEvent actionEvent) {
        back.setOnMouseClicked((event) -> {
            FXMLLoader loader = new FXMLLoader();
            try {
                loader.setLocation(getClass().getResource("/View/MainWindow.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setTitle("Create Event Post");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException e) {
                System.out.println("Could not open SalePost.fxml");
                e.printStackTrace();
            }
        });
    }

    //method to check if user has entered a number as capacity
    @FXML
    private boolean checkCapacity() {
        if(!(capacity.getText().contains("[0-9]")))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
