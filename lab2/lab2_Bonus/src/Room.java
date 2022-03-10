import java.util.Objects;

public abstract class Room {
    String name;
    int size;

    public Room() {}

    public Room(String name, int size) {
        this.name = name;
        this.size = size;
    }

    /**
     * getters and setters
     */

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return name.equals(room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
