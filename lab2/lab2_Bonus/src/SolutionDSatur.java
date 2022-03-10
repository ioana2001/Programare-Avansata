import java.sql.Time;

public class SolutionDSatur {
    Problem p;
    boolean[][] adjacencyMatrix;
    int nrRooms;
    int nrEvents;
    int[] degreeArray;
    int[] coloring;
    int[] saturation;
    int nrColors;

    /**
     * constructor
     * @param p : a problem instance for which we create a schedule
     */
    public SolutionDSatur(Problem p) {
        this.p = p;
        nrRooms = p.getRooms().size();
        nrEvents = p.getEvents().size();
        nrColors = 1;
        adjacencyMatrix = new boolean[nrEvents][nrEvents];
        degreeArray = new int[nrEvents];
        coloring = new int[nrEvents];
        saturation = new int[nrEvents];
        for (int i = 0; i < nrEvents; i++)
            for (int j = 0; j < nrEvents; j++)
                adjacencyMatrix[i][j] = false;
        for (int i = 0; i < nrEvents; i++) {
            degreeArray[i] = 0;
            coloring[i] = 0;
            saturation[i] = 0;
        }
    }

    /**
     * colors the graph using DSatur algorithm
     */
    void colorDSatur() {
        createAdjacencyMatrix();
        createDegreeArray();
        int coloredNodes = 0;

        while (coloredNodes < nrEvents) {
            int i = getMaxSaturation();
            coloring[i] = getUnusedMinColor(i);
            increaseSaturation(i);
            coloredNodes++;
        }
    }

    /**
     * creates the adjacency matrix where events are nodes and edges are between conflicting events
     */
    void createAdjacencyMatrix() {
        for (int i = 0; i < nrEvents; i++)
            for (int j = 0; j < nrEvents; j++)
                adjacencyMatrix[i][j] = checkEventsConflict(i, j);
    }

    /**
     * checks if event number e1 is conflicting with event number e2 and returns true if they are and false otherwise
     */
    boolean checkEventsConflict(int e1, int e2) {
        if (e1 == e2)
            return false;

        Time e1Start = p.getEvents().get(e1).getStart();
        Time e1End = p.getEvents().get(e1).getEnd();

        Time e2Start = p.getEvents().get(e2).getStart();
        Time e2End = p.getEvents().get(e2).getEnd();

        if ((e1Start.after(e2Start) && e2End.after(e1Start)) ||
                (e1End.after(e2Start) && e2End.after(e1End)) ||
                ((e2Start.after(e1Start) || e2Start.equals(e1Start)) && (e1End.after(e2End) || e1End.equals(e2End))))
            return true;

        return false;
    }

    /**
     * compute the degree for each node from the adjacencyMatrix and stores them in degreeArray
     */
    void createDegreeArray() {
        for (int i = 0; i < nrEvents - 1; i++)
            for (int j = i + 1; j < nrEvents; j++)
                if (adjacencyMatrix[i][j]) {
                    degreeArray[i]++;
                    degreeArray[j]++;
                }
    }

    /**
     * increases the saturation for the nodes that are adjacent with node
     */
    void increaseSaturation(int node) {
        for (int i = 0; i < nrEvents; i++)
            if (adjacencyMatrix[node][i]) {
                saturation[i]++;
            }
    }

    /**
     * gets the node with the max saturation that is not already visited;
     * if two unvisited nodes have the same saturation then choose the one with higher degree
     */
    int getMaxSaturation() {
        int maxSat = 0;
        int indexMax = 0;
        for (int i = 0; i < nrEvents; i++)
            if (saturation[i] >= maxSat && degreeArray[i] >= maxSat && coloring[i] == 0) {
                maxSat = saturation[i];
                indexMax = i;
            }

        return indexMax;
    }

    int getUnusedMinColor(int node) {
        boolean[] used = new boolean[nrColors + 1];
        for (int i = 0; i < nrColors; i++)
            used[i] = false;
        for (int i = 0; i < nrEvents; i++)
            if (adjacencyMatrix[i][node])
                used[coloring[i]] = true;
        for (int i = 1; i <= nrColors; i++)
            if (!used[i])
                return i;
        nrColors++;
        return nrColors;
    }

    void printAdjacencyMatrix() {
        for (int i = 0; i < nrEvents; i++) {
            for (int j = 0; j < nrEvents; j++)
                System.out.print(adjacencyMatrix[i][j] + " ");
            System.out.println();
        }
    }

    void printSolution() {
        if (nrColors > nrRooms) {
            System.out.println("Nu avem destule camere.");
        } else {
            for (int i = 0; i < nrEvents; i++) {
                System.out.println(p.getRooms().get(coloring[i]).getName() + " -> " + p.getEvents().get(i).getName());
            }
        }
    }

}
