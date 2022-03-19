

public class Street {
    private String name;
    private int length;
    private Intersection intersection1;
    private Intersection intersection2;

    /**
     * constructor
     */
    public Street(String name, int length) {
        this.name = name;
        this.length = length;
    }

    /**
     * getters and setters for name and length
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Intersection getIntersection1() {
        return intersection1;
    }

    public Intersection getIntersection2() {
        return intersection2;
    }

    /**
     * sets the intersections for the street
     */
    public void setIntersections(Intersection intersection1, Intersection intersection2) {
        this.intersection1 = intersection1;
        this.intersection2 = intersection2;
    }

    @Override
    public String toString() {
        return "Street{" + "name='" + name + '\'' + ", length=" + length + ", " + intersection1 + " - " + intersection2 + '}';
    }

}
