package homework;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Book.class, name = "book")
})
abstract public class Item {
    protected String id;
    protected String title;
    protected String location;

    /**
     * constructors
     */
    public Item(String id, String title, String location, String year, String author) {
        this.id = id;
        this.title = title;
        this.location = location;
    }

    public Item() {}

    /**
     * getters and setters
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "{" + "\"id\":\"" + id + "\", \"title\":\"" + title
                + "\" \"location\":\"" + location + "\"}";
    }
}
