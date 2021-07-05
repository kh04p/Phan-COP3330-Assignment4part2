package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class todo extends Application {
    private static Stage stage; //Stage variable will be created to change scene later

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage; //current stage will be duplicated to change scene later
        try {
            Parent root = FXMLLoader.load(getClass().getResource("mainTodo.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Todo List Management System");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method will change scene using specified fxml file name
    public void changeScene(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
