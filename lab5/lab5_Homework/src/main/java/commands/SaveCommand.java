package commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import homework.Catalog;

import java.io.File;
import java.io.IOException;

public class SaveCommand extends Command{
    public SaveCommand(Catalog catalog) {
        super(catalog);
    }

    /**
     * saves the catalog in a xml file
     */
    public void run(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(fileName), this.catalog);
            String catalogJson = mapper.writeValueAsString(this.catalog);
        } catch (JsonProcessingException e) {
            System.out.println("processing exception in save method");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I/O exception in save method");
            e.printStackTrace();
        }

    }
}
