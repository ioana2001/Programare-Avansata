import DAO.CitiesDAO;
import DAO.ContinentDAO;
import DAO.CountryDAO;
import DAO.SistersDAO;
import data.SistersRelations;
import data.ThreadPool;
import graph.CitiesGraph;
import org.jgrapht.alg.BronKerboschCliqueFinder;

import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * @author Mareci Ioana Amalia A2
 * @author Petrariu Ioana A2
 * Main class of the project
 */
public class Main {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String args[]) throws SQLException {
        var continents = new ContinentDAO();
        var countries = new CountryDAO();
        var cities = new CitiesDAO();
        var sisters = new SistersDAO();

        ThreadPool th = new ThreadPool();
        th.executePool();

        SistersRelations.relations();

        //prints all the continents
        continents.printContinents();

        //prints all the countries
        countries.printCountries();

        //prints the countries from Europe
        cities.printCities();

        //prints the sisters
        sisters.printSisters();

        //finding maximal cliques
        var citiesGraph = new CitiesGraph();
        System.out.println(citiesGraph.toString());

        var cliques = new BronKerboschCliqueFinder(citiesGraph.getGraph());
        System.out.println("Biggest Maximal Cliques are: " + cliques.getBiggestMaximalCliques());
    }
}

