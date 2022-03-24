public class MinimumRoute {

    /**
     * calls the recursive function tsp
     */
    public int maintenanceCarRouteLength (int[][] graph, int nrNodes) {
        boolean[] visited = new boolean[nrNodes];
        visited[0] = true;
        int ans = Integer.MAX_VALUE;
        return tsp(graph, visited, 0, nrNodes, 1, 0, ans);
    }

    /**
     * computes the cost for the smallest route
     */
    private int tsp(int[][] graph, boolean[] visited, int currentPosition, int nrNodes, int count, int length, int answer) {
        if (count == nrNodes && graph[currentPosition][0] > 0)
        {
            answer = Math.min(answer, length + graph[currentPosition][0]);
            return answer;
        }

        for (int i = 0; i < nrNodes; i++)
        {
            if (!visited[i])// && graph[currentPosition][i] > 0)
            {
                visited[i] = true;
                answer = tsp(graph, visited, i, nrNodes, count + 1,
                        length + graph[currentPosition][i], answer);
                visited[i] = false;
            }
        }

        return answer;
    }

}
