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

        ArrayList<Map<String, Map<String, Map<String, String>>>> bigList = new ArrayList<>();

        while (in.hasNextLine()) {
            Map<String,Map<String, Map<String, String>>> todoList = new HashMap<>();
            Map<String, Map<String, String>> todo = new HashMap<>();
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
                    Map<String, String> todoValues = new HashMap<>();
                    todo.put(temp, todoValues);
                    //System.out.printf("Todo name is %s%n", temp);
                    todoValues.put("status", lineIn.next());
                    //System.out.printf("status is %s%n", (todo.get(temp)).get("status"));
                    todoValues.put("date", lineIn.next());
                    //System.out.printf("date is %s%n", (todo.get(temp)).get("date"));
                    todoValues.put("description", lineIn.next());
                    //System.out.printf("desc is %s%n", (todo.get(temp)).get("description"));
                }
            }
            todoList.put((String) todoList.keySet().toArray()[0], todo);
            bigList.add(todoList);
        }

        System.out.println(bigList);

    }
}
