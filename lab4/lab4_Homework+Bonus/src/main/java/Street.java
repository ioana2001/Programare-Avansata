

public class Street {
    private final String name;
    private final int length;

    /**
     * constructor
     */
    public Street(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return name + ", length=" + length;
    }

}
