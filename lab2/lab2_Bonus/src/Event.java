import java.sql.Time;
import java.util.Objects;

public class Event {
    String name;
    int size;
    Time start;
    Time end;

    public Event(String name, int size, Time start, Time end) {
        this.name = name;
        this.size = size;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return name + '(' + "size=" + size + ", start=" + start + ", end=" + end + ')';
    }

    /**
     * getters and setters
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return name.equals(event.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
