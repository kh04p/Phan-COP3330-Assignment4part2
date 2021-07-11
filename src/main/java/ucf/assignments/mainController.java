/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Khoa Phan
 */
package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class mainController {
    //Limit of 100 lists total

    @FXML
    private ListView ListOfTodoList = new ListView();

    @FXML
    public void initialize() {
        ArrayList<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>> bigList = file.getBigList();
        int counter = 0;

        for (int i = 0; i < bigList.size(); i++) {
            if (counter > 100) {
                break;
            }
            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> todoList = bigList.get(i);
            String todoListName = (String) todoList.keySet().toArray()[0];
            ListOfTodoList.getItems().add(todoListName);
            counter++;
        }

        System.out.println(bigList.size());
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
        String selectedList = (String) ListOfTodoList.getSelectionModel().getSelectedItem();
        file.setCurrentTodoList(selectedList);
        todo todo = new todo();
        todo.changeScene("editTodo.fxml");
    }

    @FXML
    void deleteList(ActionEvent event) {
        file.removeTodoList((String) ListOfTodoList.getSelectionModel().getSelectedItem());
        ListOfTodoList.getItems().remove(ListOfTodoList.getSelectionModel().getSelectedItem());
    }
}
