import DAO.CitiesDAO;
import DAO.ContinentDAO;
import DAO.CountryDAO;
import data.ParseCSV;
import map.Distance;
import map.DrawMap;
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
        var parser = new ParseCSV();
        parser.parseFile();

        //prints all the continents
        continents.printContinents();

        //prints all the countries
        countries.printCountries();

        //prints the countries from Europe
        cities.printCities();

        //print the distance between two cities
        Distance.printDistance(10, 12);

        //draw the map with cities
        var canvas = new DrawMap();
        canvas.draw();

    }
}

