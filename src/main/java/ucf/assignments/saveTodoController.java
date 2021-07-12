/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Khoa Phan
 */
package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class saveTodoController {

    @FXML
    private TextField fileNameField;

    @FXML
    void saveFile(ActionEvent event) {
        String filePath = fileNameField.getText(); //gets user input for file path
        fileNameField.setText(file.exportTodo(filePath)); //returns confirmation on whether file import was successful
    }

    @FXML
    void goBack(ActionEvent event) {
        todo todo = new todo();
        todo.changeScene("mainTodo.fxml");
    }
}
