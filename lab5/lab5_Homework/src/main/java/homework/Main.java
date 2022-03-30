package homework;

import commands.*;

public class Main {

    public static void main(String[] args) {
        Book b1 = new Book("knuth67", "The Art of Computer Programming", "d:/books/programming/tacp.ps",
                "1967", "Donald E. Knuth");
        Book b2 = new Book("java17", "The Java Language Specification",
                "https://docs.oracle.com/javase/specs/jls/se17/html/index.html",
                "2021", "James Gosling & others");

        Catalog catalog = new Catalog();

        AddCommand addCommand = new AddCommand(catalog);
        addCommand.run(b1);
        addCommand.run(b2);

        SaveCommand saveCommand = new SaveCommand(catalog);
        saveCommand.run("target/catalog.json");

        Catalog catalog2 = new Catalog();

        LoadCommand loadCommand = new LoadCommand(catalog2);
        catalog2 = loadCommand.run("target/catalog.json");

        ListCommand listCommand = new ListCommand(catalog2);
        listCommand.run();

        ViewCommand viewCommand = new ViewCommand(catalog);
        viewCommand.run();

        ReportCommand reportCommand = new ReportCommand(catalog);
        reportCommand.run();

        InfoCommand infoCommand = new InfoCommand(catalog);
        infoCommand.run();

    }
}


