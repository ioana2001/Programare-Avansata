package compulsory.repository;

import compulsory.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findById(int id);

    List<Message> findByIdPerson(int id);
}
