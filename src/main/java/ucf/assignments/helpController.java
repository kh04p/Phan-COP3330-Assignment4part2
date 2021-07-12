package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.HashMap;
import java.util.Map;

public class helpController {
    private ObservableList<String> instructions = FXCollections.observableArrayList();
    private Map<String, String> instructionsMap = new HashMap<>();

    @FXML
    private ListView<String> helpList = new ListView<>(instructions);

    @FXML
    private TextArea helpArea;

    @FXML
    public void initialize() {
        //adds all instruction names to listView first to display
        instructions.addAll("Importing & Exporting Files", "Opening Lists and Items", "Creating New Items", "Editing Todo Items");
        helpList.setItems(instructions);

        //instruction details once user selects an item
        instructionsMap.put("Importing & Exporting Files", "Click open file and enter the\nfile path of .txt file you want to open.\n\nOnce finished, hit \"Open\" and wait for confirmation.\n\nHit \"Go Back\" once file is confirmed\nto have been imported.");
        instructionsMap.put("Opening Lists and Items", "Click on a todo list to select it first\nthen click \"Open Todo List\" to edit list.\n\nSimilarly, click on any individual todo item within\nthe list and all data values will populate on\nthe right side.");
        instructionsMap.put("Creating New Items", "Click \"New Todo List\" to create a new generic list.\n\nOnce done, edit list to change its name\nand data values.\n\nSimilarly, you can click \"New Todo Item\" to create\na generic todo item (you can put in a name\nin the todo item name field before creating\nto use desired name)");
        instructionsMap.put("Editing Todo Items", "Once clicked on, all data for\nindividual todo items will populate on the right side.\n\nTo change its name, enter a new name in the\nTodo Name field and hit \"Edit Name\".\n\nTo change other values, please change one\nat a time and hit \"Save Changes\" before\nchanging another value such as complete status,\ndue date or description.");
    }

    @FXML
    void showInstructions(ActionEvent event) {
        String selectedItem = helpList.getSelectionModel().getSelectedItem();
        for (int i = 0; i < instructionsMap.size(); i++) { //loops through Map of instructions and display matching item
            String instructionsName = (String) instructionsMap.keySet().toArray()[i];
            if (instructionsName.equals(selectedItem)) {
                helpArea.setText(instructionsMap.get(instructionsName));
            }
        }
    }

    @FXML
    void goBack(ActionEvent event) {
        todo todo = new todo();
        todo.changeScene("mainTodo.fxml");
    }
}
