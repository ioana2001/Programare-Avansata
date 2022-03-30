package commands;

import homework.Catalog;
import homework.Item;

public class AddCommand extends Command {

    public AddCommand(Catalog catalog) {
        super(catalog);
    }

    /**
     * adds an element to the catalog
     */
    public void run(Item item){
        catalog.add(item);
    }
}
