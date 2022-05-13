package homework.repository;

import homework.entity.City;
import org.chocosolver.solver.variables.IntVar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CitiesRepository extends CrudRepository<City, Integer> {
    List<City> findByName(String name);

    City findById(int id);

    List<City> findAllByIdCountry(int id);

    void saveAndFlush(City city);

    List<City> findCityBySisters(City city);

    @Query("select id from City")
    int[] getAllIds();

}
