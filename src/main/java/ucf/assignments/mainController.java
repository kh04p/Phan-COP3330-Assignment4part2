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
    void newFile(ActionEvent event) {
    //Once newFileButton is clicked, user will be directed to saveTodo.fxml to enter a file name to save to local directory.
    //changeScene(saveTodo.fxml) will be used
    //New File variable will be created to create empty text file using file path from user
    //Return confirmation
    }

    @FXML
    void openFile(ActionEvent event) {
        //Once openFileButton is clicked, user will be prompted for file path through openTodo.fxml -> changeScene(openTodo.fxml)
        //An arraylist of arraylists of maps will be created and each map will contain item's name, current state, due date and description.
        //File and Scanner variables will be created using provided file path
        //while scanner.hasNextLine()
        //Another Scanner will be created to scan each character in each line
        //while Scanner has next
        //Scanned data will be put into temporary Map
        //Map will be put into temporary ArrayList of Maps
        //temporary ArrayList of Maps will be put into bigger ArrayList
        //Populate listView in mainTodo.fxml with names of each list
    }

    @FXML
    void exportFile(ActionEvent event) {
        //Once exportFileButton is clicked, program will convert current ArrayList of ArrayLists of Maps into String
        //User will be prompted for name of file
        //BufferedWriter will be used to write String into file and save it
        //Return confirmation
    }

    @FXML
    void newList(ActionEvent event) {
    //Once newListButton is clicked, an empty ArrayList of Maps will be created
    //User will be directed to editTodo.fxml to edit new list
    //changeScene(editTodo.fxml) will be used
    }

    @FXML
    void openList(ActionEvent event) {
    //Once a list is selected and openListButton is clicked, program will record name of list
    //User will be directed to editTodo.fxml to edit each item in list
    //changeScene(editTodo.fxml) will be used
    }
}
