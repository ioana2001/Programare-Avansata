package homework;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Catalog {
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * adds an item to catalog
     */
    public void add(Item i) {
        items.add(i);
    }

    /**
     * gets the items from the catalog
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
