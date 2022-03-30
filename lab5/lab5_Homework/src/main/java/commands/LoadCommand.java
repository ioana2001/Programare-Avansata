package commands;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import homework.Catalog;

import java.io.File;
import java.io.IOException;

public class LoadCommand extends Command{

    public LoadCommand(Catalog catalog) {
        super(catalog);
    }

    /**
     * loads the catalog from a xml file and returns it
     */
    public Catalog run(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            catalog = objectMapper.readValue(new File(fileName), Catalog.class);
        } catch (JsonParseException e) {
            System.out.println("parse exception in load method");
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            System.out.println("mapping exception in load method");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("I/O exception in load method");
            e.printStackTrace();
        }
        return catalog;
    }
}
