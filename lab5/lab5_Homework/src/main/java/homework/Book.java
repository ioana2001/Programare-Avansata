package homework;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Book extends Item {
    private String year;
    private String author;

    /**
     * constructors
     */
    public Book(String id, String title, String location,
                String year, String author) {
        super(id, title, location, year, author);
        this.year = year;
        this.author = author;
    }

    public Book() {}

    /**
     * getters and setters
     */
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "{" + "\"id\":\"" + id + "\", \"title\":\"" + title
                + "\", \"location\":\"" + location + "\", \"year\":\""
                + year + ", \"author\":\"" + author + "\"}";
    }

}
