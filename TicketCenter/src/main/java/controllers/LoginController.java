package controllers;

import models.DatabaseConnection;
import models.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public LoginController() {

    }

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField enterPasswordField;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView brandingImageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File brandingFile = new File("resources/Logo.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
    }

    private void checkLogin() throws IOException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Main m = new Main();

        try{
            Statement statement = connectDB.createStatement();
            String verifyRole = "SELECT role_id from user where username ='" + usernameTextField.getText() + "' and password ='" + enterPasswordField.getText() +"'" ;

            ResultSet rs = statement.executeQuery(verifyRole);
            loginMessageLabel.setText("Invalid login. Please try again.");
            while(rs.next())
            {
                if(rs.getInt(1) == 0) {
                    loginMessageLabel.setText("Logged in as ADMIN");
                     m.changeScene("admin.fxml");
                }
                else if (rs.getInt(1) == 1) {
                    //todo create UserPanel
                    loginMessageLabel.setText("Logged in as USER");
                    // m.changeScene("UserPanel.fxml");
                }
                else if (rs.getInt(1) == 2) {
                    // todo create an organizer panel
                    loginMessageLabel.setText("Logged in as ORGANIZER");
                    // m.changeScene("OrganizerPanel.fxml");
                }
                else if(rs.getInt(1) == 3) {
                    //todo create a DistributorPanel
                    loginMessageLabel.setText("Logged in as DISTRIBUTOR");
                    // m.changeScene("DistributorPanel.fxml");
                }
                else if(rs.getInt(1) == 4) {
                    //todo create an EventOwnerPanel
                    loginMessageLabel.setText("Logged in as EVENT OWNER");
                    // m.changeScene("EventOwnerPanel.fxml");
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void cancelButtonOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        if(usernameTextField.getText().isEmpty() == false && enterPasswordField.getText().isEmpty() == false) {
            checkLogin();
        } else {
            loginMessageLabel.setTextFill(Color.RED);
            loginMessageLabel.setText("Please enter username and password.");
        }
    }
}
