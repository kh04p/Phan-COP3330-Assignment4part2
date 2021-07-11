/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Khoa Phan
 */
package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class saveListController {

    @FXML
    private TextField fileNameField;

    @FXML
    void saveFile(ActionEvent event) {
        String filePath = fileNameField.getText().toString();
        fileNameField.setText(file.exportList(filePath));
    }

    @FXML
    void goBack(ActionEvent event) {
        todo todo = new todo();
        todo.changeScene("mainTodo.fxml");
    }
}
