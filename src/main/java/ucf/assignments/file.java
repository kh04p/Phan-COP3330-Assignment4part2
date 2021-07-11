package ucf.assignments;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class file {
    private static ArrayList<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>> bigList = new ArrayList<>();
    private static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> currentTodoList = new LinkedHashMap<>();

    public static ArrayList<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>> getBigList() {
        return bigList;
    }

    public static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> getCurrentTodoList() {
        return currentTodoList;
    }

    public static void setCurrentTodoList(String todoList) { //to let program know which todoList is currently being opened
        for (int i = 0; i < file.bigList.size(); i++) { //loops through ArrayList to find matching todoList and update variable
            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> temp = file.bigList.get(i);
            String todoListName = (String) temp.keySet().toArray()[0];
            if (todoListName.equals(todoList)) {
                file.currentTodoList = temp;
            }
        }
    }

    public static void removeTodoList(String todoList) { //loops through ArrayList to find todoList to remove if name matches
        for (int i = 0; i < bigList.size(); i++) {
            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> temp = bigList.get(i);
            String todoListName = (String) temp.keySet().toArray()[0];
            if (todoListName.equals(todoList)) {
                bigList.remove(i);
            }
        }
    }

    public static void addTodoList(String todoListName) { //ads an empty todoList to ArrayList
        LinkedHashMap<String,LinkedHashMap<String, LinkedHashMap<String, String>>> todoList = new LinkedHashMap<>();
        LinkedHashMap<String, LinkedHashMap<String, String>> todo = new LinkedHashMap<>();
        todoList.put(todoListName, todo);
        bigList.add(todoList);
    }

    public static ArrayList<LinkedHashMap<String,LinkedHashMap<String, LinkedHashMap<String, String>>>> read(String filePath) throws IOException {
        File file = new File(filePath);
        Scanner in = null;
        try {
            in = new Scanner(file); //reads in file from file path
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read file."); //prints to Run window in IDE if file cannot be read
        }

        while (in.hasNextLine()) {
            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> todoList = new LinkedHashMap<>();
            LinkedHashMap<String, LinkedHashMap<String, String>> todo = new LinkedHashMap<>();
            todoList.put(in.nextLine(), todo); //puts name of list containing individual todos
            todoLoop: while (in.hasNextLine()) { //scans in individual todos
                Scanner lineIn = new Scanner(in.nextLine());
                lineIn.useDelimiter(","); //separates different words using comma
                while (lineIn.hasNext()) {
                    String temp = lineIn.next();
                    if (temp.equals("*")) { //if * is found, scanner will be at end of todoList
                        todoList.put((String) todoList.keySet().toArray()[0], todo);
                        bigList.add(todoList);
                        break todoLoop;
                    }

                    //otherwise, continue scanning in values of individual todos as strings
                    LinkedHashMap<String, String> todoValues = new LinkedHashMap<>();
                    todo.put(temp, todoValues);
                    todoValues.put("status", lineIn.next());
                    String date = lineIn.next();
                    if (isValidDate(date)) { //check to see if date is valid
                        todoValues.put("date", date);
                    }
                    else {
                        todoValues.put("date", "Invalid date found.");
                    }
                    todoValues.put("description", lineIn.next());
                }
            }
        }

        return bigList; //returns ArrayList
    }

    public static String importFile(String filePath) {
        try {
            bigList = read(filePath);
            return "File imported successfully. Please click \"Go Back\" once done.";
        } catch (IOException e) {
            return "Unable to read file";
        }
    }

    public static String exportList(String filePath) {
        StringBuilder content = new StringBuilder(); //content in new file for exporting

        for (int i = 0; i < bigList.size(); i++) {
            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> todoList = bigList.get(i);
            String todoListName = (String) todoList.keySet().toArray()[0];
            content.append(String.format("%s%n", todoListName)); //name of todoList
            LinkedHashMap<String, LinkedHashMap<String, String>> todo = todoList.get(todoListName);

            for (int j = 0; j < todo.size(); j++) {
                String todoName = (String) todo.keySet().toArray()[j];
                content.append(String.format("%s,", todoName)); //name of individual todos

                //values of individual todos
                LinkedHashMap<String, String> todoValues = todo.get(todoName);
                content.append(String.format("%s,%s,%s%n", todoValues.get("status"), todoValues.get("date"), todoValues.get("description")));
            }

            content.append("*\n"); //* to mark end of todoList
        }

        try { //tries to write content and export to new file using file path from user input
            File fileExport = new File(filePath);
            BufferedWriter output = new BufferedWriter(new FileWriter(fileExport));
            output.write(content.toString());
            output.close();
            return String.format("Generated todo list at %s%n. Click \"Go Back\" once done.", filePath);
        } catch (IOException e ) {
            return "Unable to generate todo list.";
        }
    }

    public static String exportTodo(String filePath) { //exports just a single todoList that is currently being opened
        StringBuilder content = new StringBuilder();

        ArrayList<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>> bigList2 = new ArrayList<>();
        bigList2.add(editController.getTodoList()); //creates an empty ArrayList and add current todoList for exporting

        for (int i = 0; i < bigList2.size(); i++) {
            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> todoList = bigList2.get(i);
            String todoListName = (String) todoList.keySet().toArray()[0];
            content.append(String.format("%s%n", todoListName));
            LinkedHashMap<String, LinkedHashMap<String, String>> todo = todoList.get(todoListName);

            for (int j = 0; j < todo.size(); j++) {
                String todoName = (String) todo.keySet().toArray()[j];
                content.append(String.format("%s,", todoName));
                LinkedHashMap<String, String> todoValues = todo.get(todoName);

                content.append(String.format("%s,%s,%s%n", todoValues.get("status"), todoValues.get("date"), todoValues.get("description")));
            }

            content.append("*\n");
        }

        try {
            File fileExport = new File(filePath);
            BufferedWriter output = new BufferedWriter(new FileWriter(fileExport));
            output.write(content.toString());
            output.close();
            return String.format("Generated todo list at %s%n. Click \"Go Back\" once done.", filePath);
        } catch (IOException e ) {
            return "Unable to generate todo list.";
        }
    }

    public static boolean isValidDate(String date) { //check if String is a valid date format using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static void printEverything() { //to check if everything is imported properly.
        System.out.printf("Big List size is %d%n%n", bigList.size());
        for (int i = 0; i < bigList.size(); i++) {
            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> todoList = bigList.get(i);
            String todoListName = (String) todoList.keySet().toArray()[0];
            System.out.printf("Todo List name is %s%n", todoListName);

            LinkedHashMap<String, LinkedHashMap<String, String>> todo = todoList.get(todoListName);
            System.out.printf("Todo List size is %d%n%n", todo.size());

            for (int j = 0; j < todo.size(); j++) {
                String todoName = (String) todo.keySet().toArray()[j];
                System.out.printf("Name: %s%n", todoName);
                LinkedHashMap<String, String> todoValues = todo.get(todoName);

                System.out.printf("Date: %s%n", todoValues.get("date"));
                System.out.printf("Description: %s%n", todoValues.get("description"));
                System.out.printf("Status: %s%n", todoValues.get("status"));

                System.out.println();
            }
        }
    }
}

