package compulsory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Logger;

public class Dictionary {

    private static final Logger LOGGER = Logger.getLogger(Dictionary.class.getName());
    private HashSet<String> words = new HashSet<>();

    public Dictionary() {
        try {
            File myObj = new File("words.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                words.add(data.toLowerCase());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            LOGGER.warning("The file containing words was not found");
            e.printStackTrace();
        }
    }

    public boolean isWord(String str) {
        return words.contains(str);
    }
}
