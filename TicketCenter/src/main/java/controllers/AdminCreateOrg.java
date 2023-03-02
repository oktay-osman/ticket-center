package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import models.DatabaseConnection;
import models.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AdminCreateOrg  implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private TextField firstNameTextBox;

    @FXML
    private TextField lastNameTextBox;

    @FXML
    private Button promoteButton;

    @FXML
    private TextField userIdTextBox;

    @FXML
    private ChoiceBox<String> userSelectChoiceBox;

//    @FXML
//    void backButtonOnAction(ActionEvent event) {
//
//    }
//
//    @FXML
//    void promoteButtonOnAction(ActionEvent event) {
//
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection connectNow = null;
        Connection connectDB = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connectNow = new DatabaseConnection();
            connectDB = connectNow.getConnection();
            statement = connectDB.createStatement();

            String getUsersQuery = "SELECT username FROM user u\n" +
                    "JOIN role r\n" +
                    "ON u.role_id = r.id\n" +
                    "WHERE r.role_name = 'USER'";

            rs = statement.executeQuery(getUsersQuery);

            while (true) {
                if (!rs.next()) break;
                String username = null;
                username = rs.getString("username");
                userSelectChoiceBox.getItems().add(username);
                userSelectChoiceBox.setOnAction(this::choiceBoxEvent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{ rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try{ statement.close(); }catch (SQLException e) { e.printStackTrace(); }
            try{ connectDB.close(); }catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private void getData() {
        String selected = userSelectChoiceBox.getValue();
        userIdTextBox.setText(selected);
    }

    public void choiceBoxEvent(ActionEvent actionEvent) {
        DatabaseConnection connectNow = null;
        Connection connectDB = null;
        final Statement[] statement = {null};



            userSelectChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                    try {

                        userIdTextBox.setText(newValue);
                        System.out.println(userIdTextBox.getText());

                        statement[0] = connectDB.createStatement();

                        String getSelectedItemData = "SELECT id, first_name, last_name" +
                                "FROM user " +
                                "WHERE username = '" + newValue+"'";

                        ResultSet rs = null;
                        rs = statement[0].executeQuery(getSelectedItemData);

                        while (rs.next()) {
                            int id = rs.getInt("id");
                        }
                    } catch (SQLException e) {e.printStackTrace();}
                }
            });
    }
//todo: selectByUsername(String username)


    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("admin.fxml");
    }

    public void promoteButtonOnAction(ActionEvent actionEvent) throws IOException {

    }
}
