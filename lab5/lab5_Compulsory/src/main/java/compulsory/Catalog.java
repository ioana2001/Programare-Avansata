package compulsory;

import java.util.ArrayList;

public class Catalog {
    private ArrayList<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
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
