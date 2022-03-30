package commands;

import homework.Catalog;

public class ListCommand extends Command {

    public ListCommand(Catalog catalog) {
        super(catalog);
    }

    /**
     * prints the content of the catalog
     */
    public void run() {
        System.out.println(catalog);
    }
}
