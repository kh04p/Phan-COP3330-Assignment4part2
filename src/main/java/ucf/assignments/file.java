package ucf.assignments;

import java.io.*;
import java.util.*;

public class file {
    private static ArrayList<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>> bigList = new ArrayList<>();

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
                    todoValues.put("date", lineIn.next());
                    todoValues.put("description", lineIn.next());
                }
            }
        }

        return bigList; //returns arraylist back to main
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

    public static String create(String filePath) throws IOException {
        //contents of the file being generated
        String content = "";

        //catch any IO exceptions when attempting to create html file.
        try {
            File file = new File(filePath);
            //creates bufferedWriter to write to html file and close afterwards.
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(content);
            output.close();
            return String.format("Generated file at %s%n", filePath); //returns confirmation after creating file.
        } catch (IOException e ) {
            return "Unable to generate HTML file."; //returns confirmation if unable to create file.
        }
    }

    public static String export(String filePath) {
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

        //catch any IO exceptions when attempting to create html file.
        try {
            File fileExport = new File(filePath);
            //creates bufferedWriter to write to html file and close afterwards.
            BufferedWriter output = new BufferedWriter(new FileWriter(fileExport));
            output.write(content.toString());
            output.close();
            return String.format("Generated todo list at %s%n", filePath);
        } catch (IOException e ) {
            return "Unable to generate todo list.";
        }
    }
}
