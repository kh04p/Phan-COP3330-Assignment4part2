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

    public static void setCurrentTodoList(String todoList) {
        for (int i = 0; i < file.bigList.size(); i++) {
            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> temp = file.bigList.get(i);
            String todoListName = (String) temp.keySet().toArray()[0];
            if (todoListName.equals(todoList)) {
                file.currentTodoList = temp;
            }
        }
    }

    public static void removeTodoList(String todoList) {
        for (int i = 0; i < bigList.size(); i++) {
            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> temp = bigList.get(i);
            String todoListName = (String) temp.keySet().toArray()[0];
            if (todoListName.equals(todoList)) {
                bigList.remove(i);
            }
        }
    }

    public static void addTodoList(String todoListName) {
        LinkedHashMap<String,LinkedHashMap<String, LinkedHashMap<String, String>>> todoList = new LinkedHashMap<>();
        LinkedHashMap<String, LinkedHashMap<String, String>> todo = new LinkedHashMap<>();
        todoList.put(todoListName, todo);
        bigList.add(todoList);
    }

    public static ArrayList<LinkedHashMap<String,LinkedHashMap<String, LinkedHashMap<String, String>>>> read(String filePath) throws IOException {
        File file = new File(filePath);
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read file.");
        }

        while (in.hasNextLine()) {
            LinkedHashMap<String,LinkedHashMap<String, LinkedHashMap<String, String>>> todoList = new LinkedHashMap<>();
            LinkedHashMap<String, LinkedHashMap<String, String>> todo = new LinkedHashMap<>();
            todoList.put(in.nextLine(), todo);
            todoLoop: while (in.hasNextLine()) {
                Scanner lineIn = new Scanner(in.nextLine()); //reads individual characters in current line
                lineIn.useDelimiter(","); //separates different words using comma
                while (lineIn.hasNext()) {
                    String temp = lineIn.next();
                    if (temp.equals("*")) {
                        todoList.put((String) todoList.keySet().toArray()[0], todo);
                        bigList.add(todoList);
                        break todoLoop;
                    }
                    LinkedHashMap<String, String> todoValues = new LinkedHashMap<>();
                    todo.put(temp, todoValues);
                    todoValues.put("status", lineIn.next());
                    String date = lineIn.next();
                    if (isValidDate(date)) {
                        todoValues.put("date", date);
                    }
                    else {
                        todoValues.put("date", "Invalid date found.");
                    }
                    todoValues.put("description", lineIn.next());
                }
            }
        }

        return bigList;
    }

    public static void printEverything() {
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

    public static String importFile(String filePath) {
        try {
            file.bigList = file.read(filePath);
            return "File imported successfully. Please click \"Go Back\" once done.";
        } catch (IOException e) {
            return "Unable to read file";
        }
    }

    public static String exportList(String filePath) {
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < bigList.size(); i++) {
            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> todoList = bigList.get(i);
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

    public static String exportTodo(String filePath) {
        StringBuilder content = new StringBuilder();

        ArrayList<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>> bigList2 = new ArrayList<>();
        bigList2.add(editController.getTodoList());

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

    public static boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
