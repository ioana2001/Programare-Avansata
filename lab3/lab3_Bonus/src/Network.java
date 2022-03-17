import java.util.ArrayList;
import java.util.Collections;

public class Network {
    ArrayList<Node> nodeList = new ArrayList<>();

    /**
     * adds a node to the list;
     * @param node : a Computer, Switch or Router
     */
    public void addNode(Node node) {
        nodeList.add(node);
    }

    /**
     * @return the number of nodes in the network
     */
    public int getSize() {
        return nodeList.size();
    }

    @Override
    public String toString() {
        return "Network: " + nodeList;
    }

    /**
     * prints the cost of each edge from the network
     */
    public void printCost() {
        for (Node node : nodeList)
            node.printAdjacentNodes();
    }

    /**
     * prints the name and location of each identifiable node
     */
    public void printIdentifiableNodes() {
        ArrayList<Node> identifiableNodes = new ArrayList<>();
        for(Node node : nodeList)
            if(node instanceof Identifiable)
                identifiableNodes.add(node);
        Collections.sort(identifiableNodes);
        for(Node node : identifiableNodes)
            System.out.println(node.getName() + " - " + node.getLocation());
    }

    /**
     * getter and setter for the nodeList
     */
    public ArrayList<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(ArrayList<Node> nodeList) {
        this.nodeList = nodeList;
    }
}
