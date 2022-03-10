
import java.util.Comparator;
import java.util.Vector;

public class Problem {
    Vector<Room> rooms = new Vector<>();
    Vector<Event> events = new Vector<>();

    void addRoom(Room r) {
        rooms.add(r);
    }

    void addEvent(Event e) {
        events.add(e);
    }

    /**
     * getters and setters
     */
    public Vector<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Vector<Room> rooms) {
        this.rooms = rooms;
    }

    public Vector<Event> getEvents() {
        return events;
    }

    public void setEvents(Vector<Event> events) {
        this.events = events;
    }


    @Override
    public String toString() {
        return "Rooms: " + rooms + "\nEvents: " + events;
    }

    public void sortRoomCapacity() {
        rooms.sort(new RoomCapacityComparator());
    }

    public void sortEventCapacity() {
        events.sort(new EventCapacityComparator());
    }
}

/**
 * comparators used for sort
 */
class RoomCapacityComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        Room r1 = (Room)o1;
        Room r2 = (Room)o2;
        return Integer.compare(r1.getSize(), r2.getSize());
    }
}

class EventCapacityComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        Event e1 = (Event)o1;
        Event e2 = (Event)o2;
        return Integer.compare(e1.getSize(), e2.getSize());
    }
}