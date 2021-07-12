/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Khoa Phan
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class mainController {
    private ObservableList<String> nameList = FXCollections.observableArrayList();

    @FXML
    private ListView ListOfTodoList = new ListView(nameList);

    @FXML
    public void initialize() {
        ArrayList<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>> bigList = file.getBigList();

        for (int i = 0; i < bigList.size(); i++) { //loops through ArrayList to add todoLists to display
            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> todoList = bigList.get(i);
            String todoListName = (String) todoList.keySet().toArray()[0];
            nameList.add(todoListName);
        }
        ListOfTodoList.setItems(nameList);
    }

    @FXML
    void openFile(ActionEvent event) {
        todo todo = new todo();
        todo.changeScene("openTodo.fxml");
    }

    @FXML
    void exportFile(ActionEvent event) {
        todo todo = new todo();
        todo.changeScene("saveTodoList.fxml");
    }

    @FXML
    void newList(ActionEvent event) {
        file.addTodoList("New Todo List (edit to change name)");
        ListOfTodoList.getItems().add("New Todo List (edit to change name)");
    }

    @FXML
    void openList(ActionEvent event) {
        String selectedList = (String) ListOfTodoList.getSelectionModel().getSelectedItem(); //gets name of currently selected item in ListView
        file.setCurrentTodoList(selectedList);
        todo todo = new todo();
        todo.changeScene("editTodo.fxml");
    }

    @FXML
    void deleteList(ActionEvent event) { //removes list from both ArrayList and ListView
        file.removeTodoList((String) ListOfTodoList.getSelectionModel().getSelectedItem());
        ListOfTodoList.getItems().remove(ListOfTodoList.getSelectionModel().getSelectedItem());
    }

    @FXML
    void getHelp(ActionEvent event) { //instructions on how to use program
        todo todo = new todo();
        todo.changeScene("help.fxml");
    }
}
