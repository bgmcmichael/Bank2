package ben.money.bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by fenji on 8/19/2016.
 */
public class FileHandler {
    public String readFile (String myFile){
        String fileContent = "";

        System.out.println("reading file: " + myFile);
        File aFile = new File(myFile);
        Scanner scan = new Scanner(myFile);
        while (scan.hasNext()) {
            String currentLine = scan.nextLine();
            fileContent += currentLine;
        }
        return fileContent;
    }

    public void writeFile(String fileName, String fileContents) {
        FileWriter writer = null;
        System.out.println("Saving " + fileName + " to file.");
        try{
            File bankFile = new File(fileName + ".txt");
            writer = new FileWriter(bankFile);
            writer.write(fileContents);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
