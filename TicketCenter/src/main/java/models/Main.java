package models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;

public class Main extends Application {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("TicketCenter");
    private static Stage stg;

    @Override
    public void start(Stage stage) throws Exception {
        stg = stage;
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("TicketCenter");
        stage.setScene(new Scene(root,520,400));
        stage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void addUser(String username,String password,String firstName,String lastName,int role_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            User user = new User();
            user.setFirstName(firstName);
            user.setUsername(username);
            user.setLastName(lastName);
            user.setPassword(password);
            user.setRole_id(role_id);
            em.persist(user);
            et.commit();
        } catch(Exception e) {
            if(et != null) {
                et.rollback();
            }
            e.printStackTrace();
        }

        finally {
            em.close();
        }
    }
}
