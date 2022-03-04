
public class Room {
    String name;
    int size;
    RoomType type;

    public Room() {}

    public Room(String name, int size, RoomType type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }

    @Override
    public String toString() {
        if (type == RoomType.COMPUTER_LAB)
            return name + '(' + "cap=" + size + ", lab)";
        if (type == RoomType.LECTURE_HALL)
            return name + '(' + "cap=" + size + ", lecture hall)";
        return name + '(' + "cap=" + size + "," + type + ")";
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

    public RoomType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setType(RoomType type) {
        this.type = type;
    }
}
