import java.util.ArrayList;
import java.util.Collections;

public class City {
    private ArrayList<Intersection> intersections;
    private int[][] adjacencyMatrix;

    /**
     * constructor for given intersections and streets between them
     */
    public City(Intersection[] intersections) {
        ArrayList<Intersection> listIntersection = new ArrayList<>();
        Collections.addAll(listIntersection, intersections);
        this.intersections = listIntersection;
        makeAdjacencyMatrix();
    }

    /**
     * constructor that generates nrIntersections Intersections and streets between them
     */
    public City(int nrIntersections) {
        intersections = new ArrayList<>();

        for(int i = 0; i < nrIntersections; i++) {
            Intersection a = new Intersection(RandomGenerator.generateIntersectionName());
            intersections.add(a);
        }

        for(int i = 0; i < nrIntersections - 1; i++)
            for(int j =  i + 1; j < nrIntersections; j++) {
                Street s = new Street(RandomGenerator.generateStreetName(), RandomGenerator.generateLength());
                intersections.get(i).addNeighbor(intersections.get(j), s);
            }

        makeAdjacencyMatrix();
    }

    /**
     * makes the adjacency matrix for the intersections;
     */
    private void makeAdjacencyMatrix() {
        int n = intersections.size();
        adjacencyMatrix = new int[n][n];

        for (int i = 0; i < intersections.size(); i++) {
            for (int j = 0; j < intersections.size(); j++) {
                Street aux = intersections.get(i).getNeighbor(intersections.get(j));
                if (aux != null) {
                    adjacencyMatrix[i][j] = aux.getLength();
                    adjacencyMatrix[j][i] = aux.getLength();
                }
            }
        }
    }

    int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int getNrVertices() {
        return intersections.size();
    }

    public int getNumberOfAdjacentStreets(Street street) {
        int nr = 0;

        for(Intersection i: intersections)
            if(i.hasStreet(street))
                nr += (i.getNrNeighbors() - 1);

        return nr;
    }

    /**
     * gets the minimum route using the class MinimumRoute
     */
    public int getMinimumRoute() {
        MinimumRoute r = new MinimumRoute();
        int nrIntersections = intersections.size();
        int[][] completeAdjacencyMatrix = new int[nrIntersections][nrIntersections];
        for(int i = 0; i < nrIntersections - 1; i++)
            for(int j =  i + 1; j < nrIntersections; j++)
                if(adjacencyMatrix[i][j] == 0) {
                    completeAdjacencyMatrix[i][j] = Integer.MAX_VALUE;
                    completeAdjacencyMatrix[j][i] = Integer.MAX_VALUE;
                } else {
                    completeAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
                    completeAdjacencyMatrix[j][i] = adjacencyMatrix[i][j];
                }

        return r.maintenanceCarRouteLength(completeAdjacencyMatrix, nrIntersections);
    }

}
