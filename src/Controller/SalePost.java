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

public class SalePost {


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


    @FXML private void back(ActionEvent actionEvent) {
        back.setOnMouseClicked((event) -> {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });
    }

    @FXML private void upload(ActionEvent actionEvent) {
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

    @FXML private void closePost(ActionEvent actionEvent) {
    }

    @FXML private void deletePost(ActionEvent actionEvent) {
    }

    @FXML private void savePost(ActionEvent actionEvent) {
        savePost.setOnMouseClicked((event) -> {
            if(checkPrice(askingPrice))
            {
                if (checkPrice(minimumRaise))
                {
                    GenerateId id = new GenerateId();
                    Sale sale = new Sale (id.getId(), title.getText(), description.getText(), Double.parseDouble(askingPrice.getText()), Double.parseDouble(minimumRaise.getText()),  "OPEN", "s1", fileName );
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

    private void saveImage() {
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

    @FXML private boolean checkPrice(TextArea area) {
        if(!(area.getText().contains("[0-9]")))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
