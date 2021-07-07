package ucf.assignments;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class file {
    public static Map<String,Map<String, Map<String, String>>> read(String filePath) throws IOException {
        Map<String,Map<String, Map<String, String>>> todoList = new HashMap<>();
        File file = new File(filePath); //takes in file path to read
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read file.");
        }

        Map<String, Map<String, String>> todo = new HashMap<>(); //puts all information into a Map that represents a single employee
        todoList.put(in.nextLine(), todo);


        while (in.hasNextLine()) {//reads individual lines
            Scanner lineIn = new Scanner(in.nextLine()); //reads individual characters in current line
            lineIn.useDelimiter(","); //separates different words using comma



            while (lineIn.hasNext()) {
                Map<String, String> todoValues = new HashMap<>();
                todo.put(lineIn.next(), todoValues);
                todoValues.put("status", lineIn.next());
                todoValues.put("date", lineIn.next());
                todoValues.put("description", lineIn.next());
            }

        }

        return todoList; //returns arraylist back to main
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
}
