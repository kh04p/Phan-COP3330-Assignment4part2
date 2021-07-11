/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Khoa Phan
 */
package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.LinkedHashMap;

public class editController {
    //Limit of 100 items total
    //private static ArrayList<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>> bigList = file.getBigList();
    private static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> todoList = file.getCurrentTodoList();
    private static LinkedHashMap<String, LinkedHashMap<String, String>> currentTodo = todoList.get((String) todoList.keySet().toArray()[0]);
    private final LinkedHashMap<String, LinkedHashMap<String, String>> originalList = currentTodo;

    @FXML
    private ListView ListOfTodos = new ListView();

    @FXML
    private CheckBox markCompletedButton;

    @FXML //YYYY-MM-DD format
    private TextField dueDateField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField todoNameField;

    @FXML
    private TextField nameListField;

    @FXML
    private ChoiceBox<String> filterChoiceBox;

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
        filterChoiceBox.getItems().clear();
        filterChoiceBox.getItems().addAll("Completed", "Incomplete", "Unfiltered");
    }

    public static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> getTodoList() {
        return todoList;
    }

    @FXML
    void saveNameList(ActionEvent event) {
        String oldListName = (String) todoList.keySet().toArray()[0];
        String newListName = nameListField.getText();
        LinkedHashMap<String, LinkedHashMap<String, String>> todo = currentTodo;
        todoList.remove(oldListName);
        todoList.put(newListName, todo);
        currentTodo = todoList.get((String) todoList.keySet().toArray()[0]);
        ListOfTodos.getItems().clear();
        initialize();
    }

    @FXML
    void exportList(ActionEvent event) {
        todo todo = new todo();
        todo.changeScene("saveTodo.fxml");
    }

    @FXML
    void filterStatus(ActionEvent event) {
        //Every time filterButton is clicked, program will cycle through various filters: completed, incomplete, all
        //Program will look through current ArrayList of Maps -> search for Maps with correct "status" value
        //All filtered results will be added to a temporary ArrayList and populated in ListView

        LinkedHashMap<String, LinkedHashMap<String, String>> filteredList = new LinkedHashMap<>();
        if (filterChoiceBox.getValue().equals("Completed")) {
            filteredList = filter("true");
        } else if (filterChoiceBox.getValue().equals("Incomplete")){
            filteredList = filter("false");
        } else {
            filteredList = originalList;
        }

        currentTodo = filteredList;
        todoList.put((String) todoList.keySet().toArray()[0], currentTodo);
        ListOfTodos.getItems().clear();
        initialize();
    }

    private LinkedHashMap<String, LinkedHashMap<String, String>> filter(String status) {
        LinkedHashMap<String, LinkedHashMap<String, String>> temp = currentTodo;
        for (int i = 0; i < temp.size(); i++) {
            LinkedHashMap<String, String> todoValues = temp.get(temp.keySet().toArray()[i]);
            if (!todoValues.get("status").equals(status)) {
                temp.remove(temp.keySet().toArray()[i]);
            }
        }
        return temp;
    }

    @FXML
    void goBack(ActionEvent event) {
        todo todo = new todo();
        todo.changeScene("mainTodo.fxml");
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
        String todoName = (String) ListOfTodos.getSelectionModel().getSelectedItem();
        for (int i = 0; i < currentTodo.size(); i++) {
            currentTodo.remove(todoName);
        }
        todoList.put((String) todoList.keySet().toArray()[0], currentTodo);
        ListOfTodos.getItems().clear();
        initialize();
    }

    @FXML
    void deleteAll(ActionEvent event) {
        editController.currentTodo.clear();
        editController.todoList.put((String) todoList.keySet().toArray()[0], editController.currentTodo);
        ListOfTodos.getItems().clear();
        initialize();
    }

    @FXML
    void editTodoName(ActionEvent event) {
        String oldName = (String) ListOfTodos.getSelectionModel().getSelectedItem();
        String newName = todoNameField.getText();
        LinkedHashMap<String, String> newValues = currentTodo.get(oldName);
        currentTodo.remove(oldName);
        currentTodo.put(newName, newValues);

        todoList.put((String) todoList.keySet().toArray()[0], currentTodo);
        ListOfTodos.getItems().clear();
        initialize();
    }

    @FXML
    void saveTodoChanges(ActionEvent event) {
        String selectedTodo = (String) ListOfTodos.getSelectionModel().getSelectedItem();
        LinkedHashMap<String, String> newValues = currentTodo.get(selectedTodo);

        String newDescription = descriptionField.getText();
        String newDate = dueDateField.getText();

        if (file.isValidDate(newDate)) {
            newValues.put("date", newDate);
            currentTodo.put(selectedTodo, newValues);

            if (newDescription.length() <= 256) {
                newValues.put("description", newDescription);
                currentTodo.put(selectedTodo, newValues);

                if (markCompletedButton.isSelected()) {
                    newValues.put("status", "true");
                } else {
                    newValues.put("status", "false");
                }

                todoList.put((String) todoList.keySet().toArray()[0], currentTodo);
                ListOfTodos.getItems().clear();
                initialize();
            }
            else {
                descriptionField.setText("Entered description was over 256-character limit.");
            }

            if (markCompletedButton.isSelected()) {
                newValues.put("status", "true");
            } else {
                newValues.put("status", "false");
            }

            todoList.put((String) todoList.keySet().toArray()[0], currentTodo);
            ListOfTodos.getItems().clear();
            initialize();
        }
        else {
            dueDateField.setText("Invalid date format.");
            if (newDescription.length() <= 256) {
                newValues.put("description", newDescription);
                currentTodo.put(selectedTodo, newValues);

                if (markCompletedButton.isSelected()) {
                    newValues.put("status", "true");
                } else {
                    newValues.put("status", "false");
                }

                todoList.put((String) todoList.keySet().toArray()[0], currentTodo);
                ListOfTodos.getItems().clear();
                initialize();
            }
            else {
                descriptionField.setText("Entered description was over 256-character limit.");

                if (markCompletedButton.isSelected()) {
                    newValues.put("status", "true");
                } else {
                    newValues.put("status", "false");
                }

                todoList.put((String) todoList.keySet().toArray()[0], currentTodo);
                ListOfTodos.getItems().clear();
                initialize();
            }
        }
    }
}

