/**
 * @author ioana
 */

public class Main {

    public static void main(String[] args) {
        Computer node1 = new Computer("Computer A", "v1", "123", "1.1.1", 200);
        Router node2 = new Router("Router A", "v2", "134", "1.2.1");
        Switch node3 = new Switch("Switch A", "v3", "143", "1.1.3");
        Switch node4 = new Switch("Switch B", "v4", "123", "6.1.1");
        Router node5 = new Router("Router B", "v5", "123", "1.7.1");
        Computer node6 = new Computer("Computer B", "v6", "123", "1.2.5", 500);

        Network network = new Network();
        network.addNode(node1);
        network.addNode(node2);
        network.addNode(node3);
        network.addNode(node4);
        network.addNode(node5);
        network.addNode(node6);

        System.out.println(network);
    }
}
