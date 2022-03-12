
public abstract class Node {
    String name;
    String location;
    String macAddress;

    public Node(String name, String location, String macAddress) {
        this.name = name;
        this.location = location;
        this.macAddress = macAddress;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", macAddress='" + macAddress + '\'' +
                '}';
    }
}
