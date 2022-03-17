import java.util.ArrayList;
import java.util.Stack;

public class ShortestPath {
    Network network;
    ArrayList<Node> nodeList;
    ArrayList<Node> lastNodeOnPath = new ArrayList<>();

    /**
     * constructor
     */
    public ShortestPath(Network network) {
        this.network = network;
        this.nodeList = network.getNodeList();
        for (int i = 0; i < network.getSize(); i++) {
            lastNodeOnPath.add(nodeList.get(i));
        }
    }

    /**
     * @param pathValue : the cost of the path from source to each node
     * @param visited   : true if node number i is visited, false otherwise
     * @return : the index of a node with the minimum cost path from the source that is not visited
     */
    int minDistance(double[] pathValue, boolean[] visited) {
        double minDistance = Double.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < network.getSize(); i++)
            if (!visited[i] && pathValue[i] < minDistance) {
                minDistance = pathValue[i];
                minIndex = i;
            }

        return minIndex;
    }

    /**
     * @return : the cost of the shortest path from source to finish
     */
    public double getShortestPath(Node source, Node finish) {
        int nrNodes = network.getSize();
        int sourceIndex = nodeList.indexOf(source);
        int finishIndex = nodeList.indexOf(finish);
        double[] pathValue = new double[nrNodes];
        boolean[] visited = new boolean[nrNodes];
        visited[sourceIndex] = true;

        for (int i = 0; i < network.getSize(); i++)
            if (source.getCost(nodeList.get(i)) != Integer.MAX_VALUE) {
                lastNodeOnPath.set(i, source);
            }

        for (int i = 0; i < nrNodes; i++) {
            pathValue[i] = source.getCost(nodeList.get(i)) * source.getProbability(nodeList.get(i));
            visited[i] = false;
        }

        for (int i = 0; i < nrNodes - 1; i++) {
            int u = minDistance(pathValue, visited);
            visited[u] = true;
            for (int j = 0; j < nrNodes; j++)
                if (!visited[j] &&
                        nodeList.get(u).getCost(nodeList.get(j)) * nodeList.get(u).getProbability(nodeList.get(j)) < Integer.MAX_VALUE &&
                        pathValue[u] < Integer.MAX_VALUE &&
                        pathValue[u] + nodeList.get(u).getCost(nodeList.get(j)) * nodeList.get(u).getProbability(nodeList.get(j)) < pathValue[j]) {
                    pathValue[j] = pathValue[u] + nodeList.get(u).getCost(nodeList.get(j)) * nodeList.get(u).getProbability(nodeList.get(j));
                    lastNodeOnPath.set(j, nodeList.get(u));
                }
        }

        return pathValue[finishIndex];
    }

    /**
     * prints the shortest and safest path from source to finish
     */
    public void printShortestPath(Node source, Node finish) {
        Node node = lastNodeOnPath.get(nodeList.indexOf(finish));
        Stack<Node> path = new Stack<>();
        double shortestPathValue = getShortestPath(source, finish);

        while (node != source) {
            path.push(node);
            node = lastNodeOnPath.get(nodeList.indexOf(node));
        }
        path.push(source);

        while(!path.empty()) {
            node = path.pop();
            System.out.print(node.getLocation() + " ");
        }

        System.out.print(" (with value " + shortestPathValue + ")");

    }

}
