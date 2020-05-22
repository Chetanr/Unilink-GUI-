package Controller;

import Model.Event;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class EventPost {
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


    private String fileName;
    private String filePath;
    FileChooser fileChooser = new FileChooser();
    File file;


    @FXML private void upload(ActionEvent actionEvent) {
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
            else
            {

            }
        });
    }


    @FXML private void closePost(ActionEvent actionEvent) {
    }

    @FXML private void deletePost(ActionEvent actionEvent) {
        System.out.println(fileName);
        saveImage();
    }

    @FXML private void savePost(ActionEvent actionEvent) {
        savePost.setOnMouseClicked((event) -> {
            checkCapacity();
            Event ev = new Event ("EVE1", title.getText(), description.getText(), venue.getText(), date.getValue().toString(), Integer.parseInt(capacity.getText()), "OPEN", "s1" );
            ev.insertDB();
            //saveImage();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successfully created post");
            alert.setContentText("The Post has been created successfully");
            alert.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });



    }

    private void saveImage() {
        try
        {
            String path = "./src/Images/" + fileName;
            //String path = System.getProperty("user.dir");
            System.out.println(path);
            File dest = new File(path);
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
           //ImageIO.write(ImageIO.read(file),"jpeg", new File("./src/images/" + fileName));
        } catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML private void back(ActionEvent actionEvent) {
        back.setOnMouseClicked((event) -> {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });
    }

    @FXML private void checkCapacity() {
        if(!(capacity.getText().contains("[0-9]")))
        {
            capacityError.setText("Please enter a number");
        }
        else
        {
            capacityError.setText("");
        }
    }
}
