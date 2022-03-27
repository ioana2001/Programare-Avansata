package compulsory;

/**
 * @author ioana
 */

public class Main {

    public static void main(String[] args) {

        Item i1 = new Item("knuth67", "The Art of Computer Programming",
                "d:/books/programming/tacp.ps");
        Item i2 = new Item("java17", "The Java Language Specification",
                "https://docs.oracle.com/javase/specs/jls/se17/html/index.html");

        i1.save("target/i1.json");
        Item i3 = new Item();
        i3.load("target/i1.json");
        System.out.println("item 1: " + i1);
        System.out.println("load item 1: " + i3 + "\n");

        Catalog catalog = new Catalog();
        catalog.add(i1);
        catalog.add(i2);
        catalog.save("target/catalog.json");

        Catalog catalogLoad = new Catalog();
        catalogLoad.load("target/catalog.json");

        System.out.println(catalogLoad);
    }

}
