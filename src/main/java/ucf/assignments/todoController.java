package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;

public class todoController {
    @FXML
    private TextField outputDisplay;

    public void button1(ActionEvent actionEvent) {
        display("Why did you click this??");
    }

    public void button2(ActionEvent actionEvent) {
        display("Thanks.");
    }

    public void display(String text) {
        outputDisplay.setText(text);
    }
}
