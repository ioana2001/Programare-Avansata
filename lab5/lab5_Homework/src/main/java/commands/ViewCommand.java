package commands;

import homework.Catalog;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;

public class ViewCommand extends Command {

    public ViewCommand(Catalog catalog) {
        super(catalog);
    }

    /**
     * run the command view that opens a text file with the catalog
     */
    public void run() {
        try {
            File path = new File("target\\catalog.txt");
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }
            if (desktop != null) {
                FileWriter fr = new FileWriter(path);
                fr.write(catalog.toString());
                fr.close();
                desktop.open(path);
            }
        } catch (Exception evt) {
            JOptionPane.showMessageDialog(new JFrame(), evt.getMessage());
        }
    }

}
