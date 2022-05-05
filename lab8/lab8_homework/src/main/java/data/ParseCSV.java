package data;

import DAO.CitiesDAO;
import DAO.ContinentDAO;
import DAO.CountryDAO;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * parse the data from the file concap.csv and adds it in the database
 */
public class ParseCSV {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void parseFile() {
        String fileName = "concap.csv";
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> data = reader.readAll();
            data.forEach(line -> {
                String countryName = line[0];
                String cityName = line[1];
                double latitude = Double.parseDouble(line[2]);
                double longitude = Double.parseDouble(line[3]);
                String code = line[4];
                String continentName = line[5];
                var cities = new CitiesDAO();
                var countries = new CountryDAO();
                var continents = new ContinentDAO();
                try {
                    continents.create(continentName);
                    countries.create(countryName, code, continents.findByName(continentName));
                    cities.create(cityName, true, latitude, longitude, countries.findByName(countryName));
                } catch (SQLException e) {
                    LOGGER.warning("SQLException");
                    e.printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            LOGGER.warning("FileNotFoundException in ParseCSV");
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.warning("IOException in ParseCSV");
            e.printStackTrace();
        } catch (CsvException e) {
            LOGGER.warning("CsvException in ParseCSV");
            e.printStackTrace();
        }
    }
}
