import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Intersection {
    private final String name;
    private final Map<Intersection, Street> neighborIntersection = new HashMap<>();

    /**
     * constructor
     */
    public Intersection(String name) {
        this.name = name;
    }

    public void addNeighbor(Intersection i, Street s) {
        neighborIntersection.put(i, s);
        i.neighborIntersection.put(this, s);
    }

    int getNrNeighbors() {
        return neighborIntersection.size();
    }

    /**
     * verifies if the intersection has the street s
     */
    boolean hasStreet(Street s) {
        for(Map.Entry<Intersection, Street> entry : neighborIntersection.entrySet())
            if(entry.getValue().equals(s))
                return true;
        return false;
    }

    /**
     * gets the street between this intersection and s
     */
    Street getNeighbor(Intersection s) {
        for(Map.Entry<Intersection, Street> entry : neighborIntersection.entrySet())
            if(entry.getKey().equals(s))
                return entry.getValue();
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersection that = (Intersection) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
