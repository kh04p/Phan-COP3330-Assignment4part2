/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Khoa Phan
 */
package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class openController {

    @FXML
    private TextField fileNameField;

    @FXML
    private Button openFileButton;

    @FXML
    void fileName(ActionEvent event) {
        //User will be prompted to enter the path of the file they wish to open
    }

    @FXML
    void openFile(ActionEvent event) {
        //User can hit openFileButton once they are done typing
        //File path will be saved as String and passed back
    }

}
