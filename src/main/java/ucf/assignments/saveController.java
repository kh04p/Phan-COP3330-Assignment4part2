/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Khoa Phan
 */
package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class saveController {

    @FXML
    private TextField fileNameField;

    @FXML
    private Button saveFileButton;

    @FXML
    void fileName(ActionEvent event) {
        //User will be prompted to enter the path they wish to save at
    }

    @FXML
    void saveFile(ActionEvent event) {

        String filePath = fileNameField.getText().toString();
        fileNameField.setText(file.export(filePath));

        //User can hit saveFileButton once they are done typing
        //File path will be saved as String and passed back
    }

    @FXML
    void goBack(ActionEvent event) {
        todo m = new todo();
        m.changeScene("mainTodo.fxml");
    }
}
