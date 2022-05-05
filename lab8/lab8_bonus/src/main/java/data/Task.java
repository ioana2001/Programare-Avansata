package data;

import com.github.javafaker.Faker;
import DAO.CitiesDAO;
import DAO.SistersDAO;

import java.sql.SQLException;

/**
 * class made for inserting fake cities
 */
public class Task implements Runnable {
    private final String name;
    private final Faker faker = new Faker();

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void run() {
        try {
            var cities = new CitiesDAO();
            var sisters = new SistersDAO();
            for (int index = 0; index < 100; index++) {
                String cityName = faker.address().streetName();
                while (cityName.contains("'") || cityName.length() >= 20) {
                    cityName = faker.address().streetName();
                }
                cities.create(cityName, false, 0, 0, 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}