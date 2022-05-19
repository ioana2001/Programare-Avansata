package compulsory.repository;

import compulsory.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findById(int id);

    List<Person> findByName(String name);

    Person findFirstByName(String name);

}
