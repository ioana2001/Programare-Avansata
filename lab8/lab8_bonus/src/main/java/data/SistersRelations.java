package data;

import DAO.CitiesDAO;
import DAO.SistersDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 * Class made for inserting relationships into sisters
 */
public class SistersRelations {
    private static final Random random = new Random();

    public static void relations() throws SQLException {
        var sisters = new SistersDAO();
        var cities = new CitiesDAO();
        List<Integer> ids = cities.idCities();
        for (int index = 0; index < 100; index++) {
            int index1 = random.nextInt(ids.size());
            int index2 = random.nextInt(ids.size());
            while (index1 == index2) index2 = random.nextInt(ids.size());
            sisters.create(ids.get(index1), ids.get(index2));
        }
    }
}
