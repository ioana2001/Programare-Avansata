package graph;

import DAO.CitiesDAO;
import DAO.SistersDAO;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.sql.SQLException;
import java.util.List;

/**
 * class for creating a graph representing the cites where the edges represent the sister relationship
 */
public class CitiesGraph {
    private Graph<Integer, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

    public CitiesGraph() throws SQLException {
        var cities = new CitiesDAO();
        var sisters = new SistersDAO();
        List<Integer> ids = cities.idCities();
        for (Integer id : ids) {
            graph.addVertex(id);
        }
        for (Integer id : ids) {
            if (sisters.findById(id) != 0) {
                List<Integer> idSisters = sisters.findAllById(id);
                for (Integer idSister : idSisters) {
                    graph.addEdge(id, idSister);
                    graph.addEdge(idSister, id);
                }
            }
        }
    }

    public Graph<Integer, DefaultEdge> getGraph() {
        return graph;
    }

    @Override
    public String toString() {
        return "CitiesGraph{" + "graph=" + graph + '}';
    }
}
