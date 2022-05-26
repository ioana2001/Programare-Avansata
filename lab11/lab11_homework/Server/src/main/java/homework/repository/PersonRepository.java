package homework.repository;

import homework.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Component
public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findById(int id);
    Person findFirstByName(String name);
    Set<Person> findNameByFriends(Person friend);

}
