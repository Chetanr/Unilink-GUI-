package Controller;

import Model.GenerateId;
import Model.Sale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;



public class SalePost extends Post{


    @FXML private Label minRaiseError;
    @FXML private Label askingPriceError;
    @FXML private ImageView imageView;
    @FXML private TextArea minimumRaise;
    @FXML private TextArea askingPrice;
    @FXML private Button savePost;
    @FXML private Button deletePost;
    @FXML private Button closePost;
    @FXML private Button upload;
    @FXML private TextArea proposedPrice;
    @FXML private TextArea description;
    @FXML private TextArea title;
    @FXML private Button back;

    private String fileName = "No Preview";
    FileChooser fileChooser = new FileChooser();
    File file;


    @Override
    @FXML protected void back(ActionEvent actionEvent) {
        back.setOnMouseClicked((event) -> {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });
    }

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

    @Override
    @FXML protected void closePost(ActionEvent actionEvent) {
    }

    @Override
    @FXML protected void deletePost(ActionEvent actionEvent) {
    }

    @Override
    @FXML protected void savePost(ActionEvent actionEvent) {
        savePost.setOnMouseClicked((event) -> {
            if(checkPrice(askingPrice.getText()))
            {
                if (checkPrice(minimumRaise.getText()))
                {
                    GenerateId id = new GenerateId();
                    Login login = new Login();
                    Sale sale = new Sale (id.getId(), title.getText(), description.getText(), Double.parseDouble(askingPrice.getText()), Double.parseDouble(minimumRaise.getText()),  "OPEN", login.getUser(), fileName );
                    sale.insertDB();
                    saveImage();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Successfully created post");
                    alert.setContentText("The Post has been created successfully");
                    alert.show();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                }
                else
                {
                    minRaiseError.setText("Please enter a number");
                }
            }
            else
            {
                askingPriceError.setText("Please enter a number");
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
