package controllers;

import models.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Button createOrganizerButton;

    @FXML
    private Button createDistributorButton;

    @FXML
    private Button createEventButton;

    @FXML
    private Button profilesButton;

    @FXML
    private Button distributorRatingButton;

    @FXML
    private Button backButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void createOrganizerButtonOnAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("admin_createOrg.fxml");
    }

    public void createDistributorButtonOnAction(ActionEvent actionEvent) {
    }

    public void createEventButtonOnAction(ActionEvent actionEvent) {
    }

    public void profilesButtonOnAction(ActionEvent actionEvent) {
    }

    public void distributorRatingButtonOnAction(ActionEvent actionEvent) {
    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("login.fxml");
    }
}
