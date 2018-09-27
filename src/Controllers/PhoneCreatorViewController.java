package Controllers;

import Models.DBConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class  PhoneCreatorViewController implements Initializable {

    @FXML
    private ChoiceBox<String> makeChoiceBox;

    @FXML
    private TextField screenSizeTextBox;

    @FXML
    private TextField frontCamerTextBox;

    @FXML
    private Button chooseImageButton;

    @FXML
    private TextField priceTextBox;

    @FXML
    private TextField rearCameraTextBox;

    @FXML
    private Button createPhoneButton;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField modelTextField;

    @FXML
    private ChoiceBox<String> osChoiceBox;

    @FXML
    private Slider memorySlider;

    @FXML
    private Label memoryLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // pre-load the choicebox with valid Manufacturers
        try {
            this.makeChoiceBox.getItems().addAll(DBConnect.getPhoneManufacturers());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        osChoiceBox.getItems().addAll(Arrays.asList("iOS","Android","Blackberry"));

        makeChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue)->
                {
                    try {
                        osChoiceBox.setValue(DBConnect.getOSForManufacturer(newValue));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}

