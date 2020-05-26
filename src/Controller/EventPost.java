package Controller;

import Model.Event;
import Model.GenerateId;
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


    private String fileName = "No Preview";
    private FileChooser fileChooser = new FileChooser();
    private File file;


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
//            else
//            {
//
//            }
        });
    }


    @Override
    @FXML public void closePost(ActionEvent actionEvent) {
    }

    @Override
    @FXML public void deletePost(ActionEvent actionEvent) {
    }

    @Override
    @FXML public void savePost(ActionEvent actionEvent) {
        savePost.setOnMouseClicked((event) -> {
            if (checkCapacity())
            {
                GenerateId id = new GenerateId();
                Event ev = new Event (id.getId(), title.getText(), description.getText(), venue.getText(), date.getValue().toString(), Integer.parseInt(capacity.getText()), "OPEN", "s1", fileName );
                ev.insertDB();
                saveImage();
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

    @Override
    @FXML
    protected void back(ActionEvent actionEvent) {
        back.setOnMouseClicked((event) -> {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });
    }

    @FXML private boolean checkCapacity() {
        if(!(capacity.getText().contains("[0-9]")))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
