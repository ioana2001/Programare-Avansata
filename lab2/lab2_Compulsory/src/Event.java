
public class Event {
    String name;
    int size;
    int start;
    int end;

    public Event(String name, int size, int start, int end) {
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

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
