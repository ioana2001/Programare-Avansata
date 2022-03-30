package commands;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import homework.Catalog;
import homework.Main;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand extends Command{

    public ReportCommand(Catalog catalog) {
        super(catalog);
    }

    /**
     * creates a html report with the catalog and opens it
     */
    public void run(){

        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassForTemplateLoading(Main.class, "/");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("catalog", catalog.toString());
        templateData.put("title", "html report");

        Template template = null;
        try {
            template = cfg.getTemplate("test.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringWriter out = new StringWriter();
        try {
            assert template != null;
            template.process(templateData, out);
        } catch (TemplateException e) {
            System.out.println("template exception in ReportCommand class");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO exception in ReportCommand class");
            e.printStackTrace();
        }

        try (FileWriter f = new FileWriter("catalog.html");
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);) {
            p.println(out.getBuffer().toString());
        } catch (IOException i) {
            i.printStackTrace();
        }

        Desktop desktop = null;
        desktop = Desktop.getDesktop();
        try {
            desktop.open(new File("catalog.html"));
        } catch (IOException e) {
            System.out.println("IO exception in ReportCommand class");
            e.printStackTrace();
        }

        out.flush();
    }

}
