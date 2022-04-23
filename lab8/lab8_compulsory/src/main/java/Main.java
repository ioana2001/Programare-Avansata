
import DAO.CountryDAO;
import DAO.ContinentDAO;
import database.Database;
import java.sql.SQLException;

/**
 * Creates a continent in the database (Europe)
 * and two countries (Romania, Ukraine).
 */
public class Main {
    public static void main(String[] args) {
        try {
            var continents = new ContinentDAO();
            continents.create("Europe");
            Database.getConnection().commit();
            var countries = new CountryDAO();
            int europeId = continents.findByName("Europe");
            countries.create("Romania", europeId);
            countries.create("Ukraine", europeId);
            Database.getConnection().commit();
            countries.printCountriesFromContinent(europeId);
            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }
}
