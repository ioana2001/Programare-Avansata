package homework;

import homework.entity.City;
import homework.entity.Continent;
import homework.entity.Country;
import homework.jdbc.dao.CityDAO;
import homework.jdbc.dao.ContinentDAO;
import homework.jdbc.dao.CountryDAO;
import homework.jdbc.database.Database;
import homework.repository.CitiesRepository;
import homework.repository.ContinentsRepository;
import homework.repository.CountriesRepository;
import homework.solver.Solver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@SpringBootApplication
public class FinalLab9Application {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    String command = "JPA";

    /**
     * chooses between JPA and JDBC implementation
     */
    public static void main(String[] args) {
        SpringApplication.run(FinalLab9Application.class, args);
    }

    @Bean
    public CommandLineRunner run(ContinentsRepository continentsRepository, CitiesRepository citiesRepository, CountriesRepository countriesRepository) {
        if (command.equals("JDBC"))
            return runJDBC();
        else if (command.equals("JPA"))
            return args -> {
                continentsRepository.saveAndFlush(new Continent("Europe2"));
                //System.out.println(countriesRepository.findAllByIdContinent(1));

                countriesRepository.saveAndFlush(new Country("Romania", "RO", continentsRepository.findByName("Europe").get(0).getId()));
                //System.out.println(continentsRepository.findByName("Europe"));
                int romaniaId = countriesRepository.findByName("Romania").get(0).getId();

//                final long startTime = System.nanoTime();
//                for (int i = 0; i < 5000; i++) {
//                    citiesRepository.saveAndFlush(new City("City " + i, false, 0.0, 0.0, 1000000, romaniaId));
//                }
//                final double duration = TimeUnit.SECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
//                System.out.println("Execution time of queries: " + duration);

                citiesRepository.saveAndFlush(new City("City1", false, 0.0, 0.0, 30, romaniaId));
                citiesRepository.saveAndFlush(new City("City2", false, 0.0, 0.0, 40, romaniaId));
                citiesRepository.saveAndFlush(new City("City3", false, 0.0, 0.0, 200, romaniaId));

                // create sister relations
                City city = citiesRepository.findByName("City1").get(0);
                List<City> sisters = new ArrayList<>(citiesRepository.findByName("City 2"));
                city.setSisters(sisters);
                citiesRepository.save(city);

                // use choco solver
                // Solver.solve(citiesRepository);

                // prints the cities
                // System.out.println(citiesRepository.findAll());

            };
        else return args -> System.out.println("Command not found");
    }

    public CommandLineRunner runJDBC() {
        // JDBC
        return args -> {
            ContinentDAO continentDAO = new ContinentDAO();
            CountryDAO countryDAO = new CountryDAO();
            CityDAO citiesDAO = new CityDAO();

            try {
                var continents = new ContinentDAO();
                try {
                    continents.create("Europe");
                    continents.create("Asia");

                } catch (SQLException e) {
                    LOGGER.warning("SQLException");
                    e.printStackTrace();
                }

                int asiaId = continents.findByName("Asia");

                try {
                    countryDAO.create("Japan", "JP", asiaId);
                } catch (SQLException e) {
                    LOGGER.warning("SQLException");
                    e.printStackTrace();
                }

                //prints all the continents
                continents.printContinents();

                //prints all the countries
                countryDAO.printCountries();

                Database.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

}

