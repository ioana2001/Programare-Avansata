package compulsory;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Catalog {
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * add an item in catalog
     */
    public void add(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * save a catalog in a file
     */
    public void save(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(fileName), this);
            String catalogJson = mapper.writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load a catalog from a file
     */
    public void load(String fileName) {
        Catalog catalogLoad = new Catalog();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            catalogLoad = objectMapper.readValue(new File(fileName), Catalog.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.items = catalogLoad.items;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Item i: items) {
            s.append(i).append("\n");
        }
        return s.toString();
    }
}
