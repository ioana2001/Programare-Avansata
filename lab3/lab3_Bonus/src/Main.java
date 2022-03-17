/**
 * @author ioana
 */

public class Main {

    public static void main(String[] args) {
        // create the nodes
        Computer node1 = new Computer("Computer A", "v1", "123", "1.1.1", 200);
        Router node2 = new Router("Router A", "v2", "134", "1.2.1");
        Switch node3 = new Switch("Switch A", "v3", "143", "1.1.3");
        Switch node4 = new Switch("Switch B", "v4", "123", "6.1.1");
        Router node5 = new Router("Router B", "v5", "123", "1.7.1");
        Computer node6 = new Computer("Computer B", "v6", "123", "1.2.5", 500);

        // create the edges between nodes
        node1.addAdjacentNode(node2, 10, 0.10);
        node1.addAdjacentNode(node3, 50, 0.20);
        node2.addAdjacentNode(node1, 10, 0.10);
        node2.addAdjacentNode(node3, 20, 0.34);
        node2.addAdjacentNode(node4, 20, 0.12);
        node2.addAdjacentNode(node5, 20, 0.45);
        node3.addAdjacentNode(node1, 50, 0.20);
        node3.addAdjacentNode(node2, 20, 0.34);
        node3.addAdjacentNode(node4, 10, 0.32);
        node4.addAdjacentNode(node2, 20, 0.12);
        node4.addAdjacentNode(node3, 10, 0.32);
        node4.addAdjacentNode(node5, 30, 0.46);
        node4.addAdjacentNode(node6, 10, 0.31);
        node5.addAdjacentNode(node2, 20, 0.45);
        node5.addAdjacentNode(node4, 30, 0.46);
        node5.addAdjacentNode(node6, 20, 0.23);
        node6.addAdjacentNode(node4, 10, 0.31);
        node6.addAdjacentNode(node5, 20, 0.23);

        // creates the network
        Network network = new Network();
        network.addNode(node1);
        network.addNode(node2);
        network.addNode(node3);
        network.addNode(node4);
        network.addNode(node5);
        network.addNode(node6);

        // prints the edges between nodes from the network
        System.out.println(network);
        network.printCost();

        // prints the nodes that are identifiable
        System.out.println();
        network.printIdentifiableNodes();

        // gets the shortest path between 2 nodes (node1 and node3)
        ShortestPath minPaths = new ShortestPath(network);
        System.out.println("\nShortest and safest path from " + node1.getLocation() + " to " + node3.getLocation() + " is: ");
        minPaths.printShortestPath(node1, node3);

    }
}
