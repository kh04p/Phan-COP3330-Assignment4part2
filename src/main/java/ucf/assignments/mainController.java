package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class mainController {

    @FXML
    private Button openFileButton;

    @FXML
    private Button exportFileButton;

    @FXML
    private Button openListButton;

    @FXML
    private Button newFileButton;

    @FXML
    private Button newListButton;

    @FXML
    void exportFile(ActionEvent event) {

    }

    //User will have the option to create a new empty file
    @FXML
    void newFile(ActionEvent event) {
    //File will change scene to saveTodo.fxml to ask user for the file name first.
    //After saving the file, user will be directed back to mainTodo.fxml to create a new list using newList()
    }

    //User will have an option to add a new list to an existing file or a new file
    @FXML
    void newList(ActionEvent event) {
    //Once clicked, user will be directed to editTodo.fxml to edit new Todo List
    }

    //User will have an option to open an existing file containing existing Todo lists
    @FXML
    void openFile(ActionEvent event) {
    //Once clicked, program will open and parse file
        //.............................
    }

    //User will be allowed to open a list in current file
    @FXML
    void openList(ActionEvent event) {
    //Once clicked, user will be directed to editTodo.fxml to edit list
    }

}
