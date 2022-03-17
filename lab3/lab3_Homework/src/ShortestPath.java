import java.util.ArrayList;


public class ShortestPath {
    Network network;
    ArrayList<Node> nodeList;

    /**
     * constructor
     */
    public ShortestPath(Network network) {
        this.network = network;
        this.nodeList = network.getNodeList();
    }

    /**
     * @param distance : the cost of the path from source to each node
     * @param visited : true if node number i is visited, false otherwise
     * @return : the index of a node with the minimum cost path from the source that is not visited
     */
    int minDistance(int[] distance, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE, minIndex = -1;

        for(int i = 0; i < network.getSize(); i++)
            if(!visited[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minIndex = i;
            }

        return minIndex;
    }

    /**
     * @return : the cost of the shortest path from source to finish
     */
    public int getShortestPath(Node source, Node finish) {
        int nrNodes = network.getSize();
        int sourceIndex = nodeList.indexOf(source);
        int finishIndex = nodeList.indexOf(finish);
        int[] distance = new int[nrNodes];
        boolean[] visited = new boolean[nrNodes];
        visited[sourceIndex] = true;

        for(int i = 0; i < nrNodes; i++) {
            distance[i] = source.getCost(nodeList.get(i));
            visited[i] = false;
        }

        for(int i = 0; i < nrNodes - 1; i++) {
            int u = minDistance(distance, visited);
            visited[u] = true;
            for(int j = 0; j < nrNodes; j++)
                if(!visited[j] && nodeList.get(u).getCost(nodeList.get(j)) != Integer.MAX_VALUE &&
                        distance[u] != Integer.MAX_VALUE && distance[u] + nodeList.get(u).getCost(nodeList.get(j)) < distance[j])
                    distance[j] = distance[u] + nodeList.get(u).getCost(nodeList.get(j));
        }

        return distance[finishIndex];
    }

}
