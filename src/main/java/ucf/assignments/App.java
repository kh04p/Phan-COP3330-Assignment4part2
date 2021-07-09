package ucf.assignments;

import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\khoa1\\Desktop\\random_todo_list.txt";

        //Map<String, Map<String, String>> todo;

        /*for (int i = 0; i < todoList.size(); i++) {
            String listName = (String) todoList.keySet().toArray()[i];
            todo = todoList.get(listName);
            for (int j = 0; j < todo.size(); j++) {
                String todoName = (String) todo.keySet().toArray()[j];
                System.out.println(todoName);
            }
        }*/

        File file = new File(filePath); //takes in file path to read
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read file.");
        }

        ArrayList<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>> bigList = new ArrayList<>();

        while (in.hasNextLine()) {
            LinkedHashMap<String,LinkedHashMap<String, LinkedHashMap<String, String>>> todoList = new LinkedHashMap<>();
            LinkedHashMap<String, LinkedHashMap<String, String>> todo = new LinkedHashMap<>();
            todoList.put(in.nextLine(), todo);
            todoLoop: while (in.hasNextLine()) {
                Scanner lineIn = new Scanner(in.nextLine()); //reads individual characters in current line
                lineIn.useDelimiter(","); //separates different words using comma
                while (lineIn.hasNext()) {
                    String temp = lineIn.next();
                    //System.out.printf("Current word is %s%n", temp);
                    if (temp.equals("*")) {
                        break todoLoop;
                        //continue;
                    }
                    LinkedHashMap<String, String> todoValues = new LinkedHashMap<>();
                    todo.put(temp, todoValues);
                    todoValues.put("status", lineIn.next());
                    todoValues.put("date", lineIn.next());
                    todoValues.put("description", lineIn.next());
                }
            }
            todoList.put((String) todoList.keySet().toArray()[0], todo);
            bigList.add(todoList);
        }


        //contents of the file being generated
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
