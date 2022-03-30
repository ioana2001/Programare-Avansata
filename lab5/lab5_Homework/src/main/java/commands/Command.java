package commands;

import homework.Catalog;

abstract public class Command {
    Catalog catalog;

    public Command(Catalog catalog) {
        this.catalog = catalog;
    }
}
