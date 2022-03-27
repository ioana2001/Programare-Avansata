package compulsory;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Item i1 = new Item("knuth67", "The Art of Computer Programming",
                "d:/books/programming/tacp.ps");
        Item i2 = new Item("java17", "The Java Language Specification",
                "https://docs.oracle.com/javase/specs/jls/se17/html/index.html");

        Catalog catalog = new Catalog();
        catalog.add(i1);
        catalog.add(i2);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("target/catalog.json"), catalog);
            String catalogJson = mapper.writeValueAsString(catalog);
            System.out.println(catalogJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println(catalog);
    }

}
