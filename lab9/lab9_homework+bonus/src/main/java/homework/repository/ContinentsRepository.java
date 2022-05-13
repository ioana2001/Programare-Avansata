package homework.repository;

import homework.entity.City;
import homework.entity.Continent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContinentsRepository extends CrudRepository<Continent, Integer> {
    List<Continent> findByName(String name);

    Continent findById(int id);

    void saveAndFlush(Continent europe);
}
