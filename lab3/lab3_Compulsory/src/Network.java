import java.util.ArrayList;

public class Network {
    ArrayList<Node> nodeList = new ArrayList();

    /**
     * adds a node to the list;
     * @param node : a Computer, Switch or Router
     */
    public void addNode(Node node) {
        nodeList.add(node);
    }

    @Override
    public String toString() {
        return "Network: " + nodeList;
    }
}
