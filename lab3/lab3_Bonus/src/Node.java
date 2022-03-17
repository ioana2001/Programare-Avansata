import java.util.HashMap;
import java.util.Map;

public abstract class Node implements Comparable<Node> {
    String name;
    String location;
    String macAddress;
    Map<Node, Double> adjacentNodesProbabilities = new HashMap<>();
    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(String name, String location, String macAddress) {
        this.name = name;
        this.location = location;
        this.macAddress = macAddress;
    }

    /**
     * adds a node to the list
     */
    public void addAdjacentNode(Node n, int cost, double probability) {
        adjacentNodes.put(n, cost);
        adjacentNodesProbabilities.put(n, probability);
    }

    /**
     * @return : the number of adjacent nodes
     */
    public int getNumberOfAdjacentNodes() {
        return adjacentNodes.size();
    }

    /**
     * @return the cost to node n or Integer.MAX_VALUE if there isn't an edge
     */
    public int getCost(Node n) {
        if(adjacentNodes.containsKey(n))
            return adjacentNodes.get(n);
        if(this.equals(n))
            return 0;
        return Integer.MAX_VALUE;
    }

    /**
     * @return the probability of failure for the edge to node n or 0 if there isn't an edge
     */
    public double getProbability(Node n) {
        if(adjacentNodesProbabilities.containsKey(n))
            return adjacentNodesProbabilities.get(n);
        return 1.0;
    }

    public void printAdjacentNodes() {
        for (Node key : adjacentNodes.keySet())
            System.out.println(this.location + "--" + key.location + ", cost: " + adjacentNodes.get(key));
    }

    @Override
    public String toString() {
        return "Node{" + "name='" + name + '\'' + ", location='" + location + '\'' + ", macAddress='" + macAddress + '\'' + '}';
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }


    /**
     * function used for sorting
     */
    @Override
    public int compareTo(Node other) {
        if (other.name == null) {
            throw new IllegalArgumentException("null name");
        }
        return this.location.compareTo(other.location);
    }

}