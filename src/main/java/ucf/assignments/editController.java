package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class editController {

    @FXML
    private CheckBox markCompletedButton;

    @FXML
    private TextField dueDate;

    @FXML
    private TextArea description;

    @FXML
    private Button editDateButton;

    @FXML
    private Button editDescriptionButton;

    @FXML
    private Button saveTodoButton;

    @FXML
    private Button exportListButton;

    @FXML
    private Button newTodoButton;

    @FXML
    private TextField nameListButton;

    @FXML
    private Button saveListButton;

    @FXML
    void editDate(ActionEvent event) {
    //User will be able to edit due date of item by typing and hitting "Edit Due Date" button
    }

    @FXML
    void editDescription(ActionEvent event) {
    //User can type in another description and hit "Edit Description" button
    }

    @FXML
    void exportList(ActionEvent event) {
    //User will be able to export current list into a file containing only this list
    //Once clicked, user will be directed to saveTodo.fxml
    }

    @FXML
    void markCompleted(ActionEvent event) {
    //User can mark item as completed or incomplete
    }

    @FXML
    void nameList(ActionEvent event) {
    //User can type in name of current list or change existing name
    //Once done typing, user can hit saveList() button to save file name
    }

    @FXML
    void newTodo(ActionEvent event) {
    //User can add a new item into list
    }

    @FXML
    void saveList(ActionEvent event) {
    //User can save list to file that is currently being opened
    }

    @FXML
    void saveTodo(ActionEvent event) {
    //Once done editing, user can save item to current list
    }

}
