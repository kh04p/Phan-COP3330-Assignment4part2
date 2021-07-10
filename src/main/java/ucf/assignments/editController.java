/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Khoa Phan
 */
package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class editController {
    //Limit of 100 items total
    //private static ArrayList<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>> bigList = file.getBigList();
    private static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> todoList = file.getCurrentTodoList();
    private static LinkedHashMap<String, LinkedHashMap<String, String>> currentTodo = todoList.get((String) todoList.keySet().toArray()[0]);

    @FXML
    private ListView ListOfTodos = new ListView();

    @FXML
    private Button newTodoButton;

    @FXML
    private Button deleteTodoButton;

    @FXML
    private CheckBox markCompletedButton;

    @FXML //YYYY-MM-DD format
    private TextField dueDateField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Button editDateButton;

    @FXML
    private Button editDescriptionButton;

    @FXML
    private TextField todoNameField;

    @FXML
    private Button editTodoNameButton;

    @FXML
    private TextField nameListField;

    @FXML
    private Button saveListButton;

    @FXML
    private Button exportListButton;

    @FXML
    private Button filterButton;

    @FXML
    public void initialize() {
        todoList = file.getCurrentTodoList();
        currentTodo = todoList.get((String) todoList.keySet().toArray()[0]);
        nameListField.setText((String) todoList.keySet().toArray()[0]);
        LinkedHashMap<String, LinkedHashMap<String, String>> todo = todoList.get((String) todoList.keySet().toArray()[0]);

        for (int j = 0; j < todo.size(); j++) {
            String todoName = (String) todo.keySet().toArray()[j];
            ListOfTodos.getItems().add(todoName);
        }
    }

    @FXML
    void goBack(ActionEvent event) {
        todo m = new todo();
        m.changeScene("mainTodo.fxml");
    }

    @FXML
    void selectTodo() {

        String todoName = (String) ListOfTodos.getSelectionModel().getSelectedItem();
        todoNameField.setText(todoName);
        LinkedHashMap<String, String> todoValues = currentTodo.get(todoName);

        markCompletedButton.setSelected(Boolean.parseBoolean(todoValues.get("status")));
        dueDateField.setText(todoValues.get("date"));
        descriptionField.setText(todoValues.get("description"));
    }

    @FXML
    void newTodo(ActionEvent event) {
        //User can add a new item into list
        //Once newTodoButton is clicked, program will create a new Map containing empty fields for name, status, due date and description
        //User can use editDate, editDescription and markCompleted to change current item
        String todoName = todoNameField.getText().trim();

        if (todoName.isBlank()) {
            todoName = String.format("Todo item %d", currentTodo.size() + 1);
        }

        for (int i = 0; i < currentTodo.size(); i++) {
            if (todoName.equals(currentTodo.keySet().toArray()[i])) {
                todoName = String.format("Todo item %d", currentTodo.size() + 1);
            }
        }

        LinkedHashMap<String, String> todoValues = new LinkedHashMap<>();
        todoValues.put("status", "");
        todoValues.put("date", "");
        todoValues.put("description", "");

        currentTodo.put(todoName, todoValues);
        todoList.put((String) todoList.keySet().toArray()[0], currentTodo);
        ListOfTodos.getItems().clear();
        initialize();
    }

    @FXML
    void deleteTodo(ActionEvent event) {
        //User can select a current item and hit deleteTodoButton to delete it
        //Program will find a Map with matching "name" key and remove it from ArrayList of Maps
        //Return confirmation
        String todoName = (String) ListOfTodos.getSelectionModel().getSelectedItem();

        for (int i = 0; i < currentTodo.size(); i++) {
            currentTodo.remove(todoName);
        }

        todoList.put((String) todoList.keySet().toArray()[0], currentTodo);
        ListOfTodos.getItems().clear();
        initialize();
    }

    @FXML
    void todoName(ActionEvent event) {
        //User will type in desired name for item here. Otherwise, it will be populated with existing data from item.
        //After entering desired name, user can hit editTodoNameButton to save
    }

    @FXML
    void editTodoName(ActionEvent event) {
        //Name will be passed from todoNameField after user hits editTodoNameButton
        //Program will find a value within that Map with the key for "name" and replaces it with provided user input
    }

    @FXML
    void markCompleted(ActionEvent event) {
        //User can mark item as completed or incomplete
        //Once checked or unchecked, program will search current Map for value with "status" key and change its boolean state
    }

    @FXML
    void dueDate(ActionEvent event) {
        //User will type in desired date here. Otherwise, it will be populated with existing data from item.
        //After entering desired date, user can hit editDateButton to save
    }

    @FXML
    void editDate(ActionEvent event) {
        //Date will be passed from dueDateField after user hits editDateButton
        //Program will find a value within that Map with the key for "date" and replaces it with provided user input
    }

    @FXML
    void description(MouseEvent event) {
        //User will type in desired description here. Otherwise, it will be populated with existing data from item.
        //After entering desired description, user can hit editDescriptionButton to save
    }

    @FXML
    void editDescription(ActionEvent event) {
        //Description will be passed from descriptionField after user hits editDescriptionButton
        //Program will find a value within that Map with the key for "description" and replaces it with provided String
    }

    @FXML
    void nameList(ActionEvent event) {
        //User will type in desired name for list here, which will be ArrayList of Maps
        //Otherwise, it will be populated with existing data from item.
        //After entering desired name, user can hit saveListButton to return entire ArrayList back to mainController
    }

    @FXML
    void saveList(ActionEvent event) {
        //Once user is done typing in name of current list in nameList(), user can hit saveListButton
        //Program will create a temporary variable containing the old name of the list if it was not empty
        //Program will return both old name and ArrayList of Maps with new name back to mainController
        //ArrayList with old name will be deleted and new ArrayList of Maps will be added to bigger ArrayList in mainController
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
    void filterStatus(ActionEvent event) {
        //Every time filterButton is clicked, program will cycle through various filters: completed, incomplete, all
        //Program will look through current ArrayList of Maps -> search for Maps with correct "status" value
        //All filtered results will be added to a temporary ArrayList and populated in ListView
    }
}

