package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class editController {
    //TextField variables will contain information obtained from current list but user can enter in new value and hit "Edit" buttons to save.

    @FXML
    private CheckBox markCompletedButton;

    @FXML
    private TextField dueDate;

    @FXML
    private TextArea description;

    @FXML
    private TextField todoNameField;

    @FXML
    private Button saveTodoNameButton;

    @FXML
    private Button editDateButton;

    @FXML
    private Button editDescriptionButton;

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
    //Once user selects an item, enter a desired date and hit editDateButton, program will find a Map with that name
    //Program will find a value within that Map with the key for "date" and replaces it with user input
    }

    @FXML
    void editDescription(ActionEvent event) {
    //Once user selects an item, enter a desired date and hit editDateButton, program will find a Map with that name
    //Program will find a value within that Map with the key for "date" and replaces it with user input
    }

    @FXML
    void exportList(ActionEvent event) {
    //User will be able to export current list into a file containing only this list
    //Once clicked, user will be directed to saveTodo.fxml
    //Path of file to be saved will be passed back
    //Current ArrayList of Maps will be converted to String
    //File variable will be created to create new file with desired name
    //BufferedWriter will be used to write String to new file and save
    //Return confirmation
    }

    @FXML
    void markCompleted(ActionEvent event) {
    //User can mark item as completed or incomplete
    //Once checked or unchecked, program will search current Map for value with "status" key and change its boolean state
    }

    @FXML
    void saveTodoName(ActionEvent event) {
    //Once user types in name for current item, user can hit SaveTodoNameButton to save
    //Program will search in current Map for value with "name" key and replace it with user input
    }

    @FXML
    void nameList(ActionEvent event) {
    //User can type in name of current list or change existing name
    //Once done typing, user can hit saveListButton to save using method saveList()
    }

    @FXML
    void newTodo(ActionEvent event) {
    //User can add a new item into list
    //Once newTodoButton is clicked, program will create a new Map containing empty fields for name, status, due date and description
    //User can use editDate, editDescription and markCompleted to change current item
    }

    @FXML
    void saveList(ActionEvent event) {
    //Once user is done typing in name of current list in nameList(), user can hit saveListButton
    //Program will create a temporary variable containing the old name of the list if it was not empty
    //Program will return both old name and ArrayList of Maps with new name back to mainController
    //ArrayList with old name will be deleted and new ArrayList of Maps will be added to bigger ArrayList in mainController
    }

}
