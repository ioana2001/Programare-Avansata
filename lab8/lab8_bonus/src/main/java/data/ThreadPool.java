package data;

import DAO.CitiesDAO;
import DAO.ContinentDAO;
import DAO.CountryDAO;
import DAO.SistersDAO;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * a class representing a thread pool
 */
public class ThreadPool {
    public void executePool() throws SQLException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        var country = new CountryDAO();
        var continent = new ContinentDAO();

        continent.create("Europe");
        country.create("Country1", "CO", continent.findByName("Europe"));
        for (int i = 1; i <= 10; i++) {
            Task task = new Task("Task " + i);
            executor.execute(task);
        }
        executor.shutdown();
    }
}




