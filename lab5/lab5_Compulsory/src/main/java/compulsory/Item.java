package compulsory;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Item implements ItemInterface {
    private String id;
    private String title;
    private String location;

    /**
     * constructors
     */
    public Item(String id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
    }

    public Item() {}

    /**
     * load an item from a file
     */
    public void load(String fileName) {
        Item i = new Item();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            i = objectMapper.readValue(new File(fileName), Item.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.id = i.id;
        this.title = i.title;
        this.location = i.location;
    }

    /**
     * save an item in a file
     */
    public void save(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(fileName), this);
            String itemJson = mapper.writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getters and setters
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\", \"title\":\"" + title + "\", \"location\":\"" + location + "\"}";
    }
}
