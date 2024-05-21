package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class IOSearcher {

    
    public static boolean search(String word, String... fileNames) {
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(word) + "\\b"); 
        for (String fileName : fileNames) {
            File file = new File(fileName);
            if (!file.exists()) {
                System.err.println("File not found: " + fileName);
                continue;  
            }
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (pattern.matcher(line).find()) {
                        return true;  
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("Error accessing file: " + fileName + " - " + e.getMessage());
               
            }
        }
        return false;  
    }
}
