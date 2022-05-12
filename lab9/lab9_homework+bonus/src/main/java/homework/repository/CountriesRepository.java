package homework.repository;

import homework.entity.City;
import homework.entity.Continent;
import homework.entity.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountriesRepository extends CrudRepository<Country, Integer> {
    List<Country> findByName(String name);

    List<Country> findAllByIdContinent(int continent);

    Country findById(int id);

    void saveAndFlush(Country country);
}
