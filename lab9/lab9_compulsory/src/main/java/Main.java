import entity.CitiesEntity;
import entity.ContinentsEntity;
import entity.CountriesEntity;
import manager.Manager;
import repositories.CitiesRepository;
import repositories.ContinentsRepository;
import repositories.CountriesRepository;

/**
 * The main class of the project
 */
public class Main {
    public static void main(String[] args) {

        ContinentsRepository continentsRepository = new ContinentsRepository();
        CountriesRepository countriesRepository = new CountriesRepository();
        CitiesRepository citiesRepository = new CitiesRepository();

        ContinentsEntity continent = new ContinentsEntity("Europe");
        continentsRepository.create(continent);

        int europeId = continentsRepository.findByName("Europe").get(0).getId();
        CountriesEntity country = new CountriesEntity("Romania", "RO", europeId);
        countriesRepository.create(country);

        int romaniaId = countriesRepository.findByName("Romania").get(0).getId();
        CitiesEntity city = new CitiesEntity("Bucharest", true, 0.0, 0.0, romaniaId);
        citiesRepository.create(city);

        System.out.println("The inserted values: ");
        System.out.println(continentsRepository.findByName("Europe"));
        System.out.println(countriesRepository.findByName("Romania"));
        System.out.println(citiesRepository.findByName("Bucharest"));

        // Finds the cities, countries and continents by a specified pattern
        System.out.println("\nValues found based on a pattern: ");
        System.out.println(continentsRepository.findByNamePattern("Euro_e"));
        System.out.println(countriesRepository.findByNamePattern("%ia"));
        System.out.println(citiesRepository.findByNamePattern("B%"));

        Manager.close();
    }
}
