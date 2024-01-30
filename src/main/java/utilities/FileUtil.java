package utilities;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	
	public static void writeToFile(String filePath, String data) { 
        try { 
            FileWriter writer = new FileWriter(filePath); 
            writer.write(data); 
            writer.close(); 
            System.out.println("Data written to file: " + filePath); 
        } catch (IOException e) { 
            System.out.println("An error occurred while writing data to file: " + filePath); 
            e.printStackTrace(); 
        } 
    } 


}

//filePath : A String representing the path to the file where the data will be written.
//data : A String containing the data that will be written to the file.