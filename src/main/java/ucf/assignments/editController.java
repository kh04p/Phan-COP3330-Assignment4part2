/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Khoa Phan
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.LinkedHashMap;

public class editController {
    private static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> todoList = file.getCurrentTodoList();
    private LinkedHashMap<String, LinkedHashMap<String, String>> todo = todoList.get((String) todoList.keySet().toArray()[0]);
    private ObservableList<String> nameList = FXCollections.observableArrayList();

    @FXML
    private ListView ListOfTodos = new ListView(nameList);

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
        todoList = file.getCurrentTodoList(); //get name of current todolist and its contents
        todo = todoList.get((String) todoList.keySet().toArray()[0]); //will contain list of actual todos and its contents
        nameListField.setText((String) todoList.keySet().toArray()[0]); //sets name for current todolist

        for (int j = 0; j < todo.size(); j++) { //adds individual todos to observableList to display later
            String todoName = (String) todo.keySet().toArray()[j];
            nameList.add(todoName);
        }
        ListOfTodos.setItems(nameList); //adds observableList from above to listView

        filterChoiceBox.getItems().clear(); //clears all current items in choiceBox
        filterChoiceBox.getItems().addAll("Completed", "Incomplete", "Unfiltered"); //for filtering purposes
    }

    public static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> getTodoList() {
        return todoList; //to export only this todoList instead of entire file.
    }

    @FXML
    void saveNameList(ActionEvent event) {
        String oldListName = (String) todoList.keySet().toArray()[0]; //get old name of todoList before changing name
        String newListName = nameListField.getText(); //get new name through user input

        LinkedHashMap<String, LinkedHashMap<String, String>> todo = this.todo; //temp variable to contain values of todoList
        todoList.remove(oldListName); //remove old hashmap with old name
        todoList.put(newListName, todo); //add new hashmap from temp variable with new name

        //this.todo = todoList.get((String) todoList.keySet().toArray()[0]); //adds all individual todos to new hashmap
        ListOfTodos.getItems().clear(); //clear listView
        initialize(); //adds everything back to listView
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
    }

    private LinkedHashMap<String, LinkedHashMap<String, String>> filter(String status) {
        LinkedHashMap<String, LinkedHashMap<String, String>> temp = todo;
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
    void selectTodo() { //get currently selected item from listView and show all of its data
        String todoName = (String) ListOfTodos.getSelectionModel().getSelectedItem();
        todoNameField.setText(todoName);
        LinkedHashMap<String, String> todoValues = todo.get(todoName);

        markCompletedButton.setSelected(Boolean.parseBoolean(todoValues.get("status")));
        dueDateField.setText(todoValues.get("date"));
        descriptionField.setText(todoValues.get("description"));
    }

    @FXML
    void newTodo(ActionEvent event) {
        String todoName = todoNameField.getText().trim(); //gets name from todoNameField if user wishes to name new item before adding

        if (todoName.isBlank()) { //if user did not enter anything into todoNameField
            todoName = String.format("Todo item %d", todo.size() + 1);
        }

        for (int i = 0; i < todo.size(); i++) { //if todoNameField contains duplicate name
            if (todoName.equals(todo.keySet().toArray()[i])) {
                todoName = String.format("Todo item %d", todo.size() + 1);
            }
        }

        LinkedHashMap<String, String> todoValues = new LinkedHashMap<>(); //initializes empty values for new item
        todoValues.put("status", "");
        todoValues.put("date", "");
        todoValues.put("description", "");

        todo.put(todoName, todoValues); //puts new name and empty values into new item
        todoList.put((String) todoList.keySet().toArray()[0], todo); //adds empty item into todoList
        ListOfTodos.getItems().clear(); //clears ListView
        initialize(); //add everything back into ListView
    }

    @FXML
    void deleteTodo(ActionEvent event) {
        String todoName = (String) ListOfTodos.getSelectionModel().getSelectedItem(); //get name of currently selected item
        for (int i = 0; i < todo.size(); i++) { //loops thru list to find matching item
            todo.remove(todoName); //removes matching item
        }
        todoList.put((String) todoList.keySet().toArray()[0], todo); //adds individual items back into todoList
        ListOfTodos.getItems().clear(); //clears ListView
        initialize(); //add everything back into ListView
    }

    @FXML
    void deleteAll(ActionEvent event) {
        todo.clear(); //clears every individual item
        todoList.put((String) todoList.keySet().toArray()[0], todo); //adds empty list back into todoList
        ListOfTodos.getItems().clear(); //clears ListView
        initialize(); //add everything back into ListView
    }

    @FXML
    void editTodoName(ActionEvent event) {
        String oldName = (String) ListOfTodos.getSelectionModel().getSelectedItem(); //get name of currently selected item
        String newName = todoNameField.getText(); //get new name from user input

        if (newName.length() >= 1 && newName.length() <= 256) { //check if name meets character requirements
            LinkedHashMap<String, String> newValues = todo.get(oldName); //duplicates data from old item to temp variable
            todo.remove(oldName); //removes old item
            todo.put(newName, newValues); //adds new item back to list with new name and same data

            todoList.put((String) todoList.keySet().toArray()[0], todo); //puts items back into todoList
            ListOfTodos.getItems().clear(); //clears ListView
            initialize(); //add everything back into ListView
        } else { //if name does not meet character requirements
            todoNameField.setText("Todo name has to be between 1 and 256 characters.");
        }
    }

    @FXML
    void saveTodoChanges(ActionEvent event) {
        try {
            String selectedTodo = (String) ListOfTodos.getSelectionModel().getSelectedItem(); //gets name of currently selected item
            LinkedHashMap<String, String> newValues = todo.get(selectedTodo); //duplicates values of currently selected item

            String newDescription = descriptionField.getText(); //gets new description from user input
            String newDate = dueDateField.getText(); //gets new date from user input

            if (file.isValidDate(newDate)) { //checks if date is in valid format
                newValues.put("date", newDate);
                todo.put(selectedTodo, newValues); //updates date field for currently selected item

                if (newDescription.length() <= 256 && newDescription.length() >= 1) { //checks if description meets character requirements
                    newValues.put("description", newDescription);
                    todo.put(selectedTodo, newValues); //updates description field for currently selected item

                    if (markCompletedButton.isSelected()) { //checks if checkBox is checked
                        newValues.put("status", "true"); //updates status field for currently selected item
                    } else {
                        newValues.put("status", "false");
                    }

                    todoList.put((String) todoList.keySet().toArray()[0], todo); //adds all items back into todoList
                    ListOfTodos.getItems().clear(); //clears ListView
                    initialize(); //add everything back into ListView
                }
                else {
                    descriptionField.setText("Description has to be between 1 and 256 characters.");
                }

                if (markCompletedButton.isSelected()) { //checks if checkBox is checked
                    newValues.put("status", "true"); //updates status field for currently selected item
                } else {
                    newValues.put("status", "false");
                }

                todoList.put((String) todoList.keySet().toArray()[0], todo);
                ListOfTodos.getItems().clear(); //clears ListView
                initialize(); //add everything back into ListView
            }
            else {
                dueDateField.setText("Invalid date format.");

                if (newDescription.length() <= 256) {
                    newValues.put("description", newDescription);
                    todo.put(selectedTodo, newValues);

                    if (markCompletedButton.isSelected()) {
                        newValues.put("status", "true");
                    } else {
                        newValues.put("status", "false");
                    }

                    todoList.put((String) todoList.keySet().toArray()[0], todo);
                    ListOfTodos.getItems().clear(); //clears ListView
                    initialize(); //add everything back into ListView
                }
                else {
                    descriptionField.setText("Description has to be between 1 and 256 characters.");

                    if (markCompletedButton.isSelected()) {
                        newValues.put("status", "true");
                    } else {
                        newValues.put("status", "false");
                    }

                    todoList.put((String) todoList.keySet().toArray()[0], todo);
                    ListOfTodos.getItems().clear(); //clears ListView
                    initialize(); //add everything back into ListView
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Unable to update todo values.");
        }
    }

    @FXML
    void getHelp(ActionEvent event) {
        todo todo = new todo();
        todo.changeScene("help.fxml");
    }
}

