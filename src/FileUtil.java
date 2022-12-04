package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
                                                                                                    
public class FileUtil {   
    
    private FileUtil() {}
                                                                        
    public static List<String> readFile(String fileName) {                                         
        List<String> fileContents = new ArrayList<String>();                                        
        try (FileReader fr = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fr)) 
        {
            String line;
            while ((line = bufferedReader.readLine()) != null) {                                               
                fileContents.add(line);                                                             
            }
        } catch (Exception e) {                                                         
            System.out.println("Couldn't find that file, my man.");                                               
            // e.printStackTrace();                                                                    
        }
        return fileContents;                                                                        
    }  

    public static List<Integer> stoi(List<String> inputList) {
        List<Integer> data = new ArrayList<>();
        for (String s : inputList) {
            data.add(Integer.valueOf(s));
        }
        return data;
    }

}  