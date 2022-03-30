package commands;

import homework.Catalog;
import homework.Item;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.*;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;

public class InfoCommand extends Command {

    public InfoCommand(Catalog catalog) {
        super(catalog);
    }

    /**
     * creates and prints metadata
     */
    public void run() {
        File file = new File("target/catalog.json");

        for (Item i : catalog.getItems()) {
            Metadata metadata = new Metadata();

            metadata.add(Metadata.TITLE, i.getTitle());
            metadata.add(Metadata.IDENTIFIER, i.getId());
            metadata.add(Metadata.LOCATION, i.getLocation());

            Parser parser = new EmptyParser();
            BodyContentHandler handler = new BodyContentHandler();

            try (FileInputStream inputstream = new FileInputStream(file);) {
                ParseContext context = new ParseContext();
                parser.parse(inputstream, handler, metadata, context);
                System.out.println(handler.toString());
            } catch (SAXException e) {
                System.out.println("sax exception in InfoCommand class");
                e.printStackTrace();
            } catch (TikaException e) {
                System.out.println("tika exception in InfoCommand class");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IO exception in InfoCommand class");
                e.printStackTrace();
            }

            String[] metadataNames = metadata.names();

            for (String name : metadataNames) {
                System.out.println(name + ": " + metadata.get(name));
            }
        }
    }

}