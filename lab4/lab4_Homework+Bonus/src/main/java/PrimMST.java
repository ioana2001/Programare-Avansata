
public class PrimMST {
    private int nrVertices;
    private int[][] adjacencyMatrix = new int[nrVertices][nrVertices];

    public PrimMST(City c) {
        this.nrVertices = c.getNrVertices();
        this.adjacencyMatrix = c.getAdjacencyMatrix();
    }

    void printMST(int[] parent) {
        System.out.println("Edge \tLength");
        for (int i = 1; i < nrVertices; i++)
            System.out.println(parent[i] + " - " + i + "\t" + adjacencyMatrix[i][parent[i]]);
    }

    /**
     * gets the minimum cost length to a node that is not visited
     */
    int minKey(int[] key, Boolean[] visited) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < nrVertices; v++)
            if (!visited[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    /**
     * uses Prim algorithm for finding the minimum spanning tree
     */
    void primMST() {
        int[] parent = new int[nrVertices];
        int[] key = new int[nrVertices];
        Boolean[] visited = new Boolean[nrVertices];

        for (int i = 0; i < nrVertices; i++) {
            key[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < nrVertices - 1; count++) {
            int u = minKey(key, visited);

            if (u != -1) {
                visited[u] = true;
                for (int v = 0; v < nrVertices; v++)
                    if (adjacencyMatrix[u][v] != 0 && !visited[v] && adjacencyMatrix[u][v] < key[v]) {
                        parent[v] = u;
                        key[v] = adjacencyMatrix[u][v];
                    }
            }
        }

        printMST(parent);
    }

    public void printAdjacencyMatrix() {
        for (int i = 0; i < nrVertices; i++) {
            for (int j = 0; j < nrVertices; j++)
                System.out.print(adjacencyMatrix[i][j] + " ");
            System.out.println();
        }
    }
}
