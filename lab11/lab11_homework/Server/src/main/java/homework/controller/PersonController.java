package homework.controller;

import homework.entity.Person;
import homework.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Person>> getPersons() {
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(
                person -> {
                    persons.add(new Person(person.getId(), person.getName()));
                }
        );
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<String> getPerson(@PathVariable String name) {
        Person person = personRepository.findFirstByName(name);

        if (person == null) {
            return new ResponseEntity<>("Person not found", HttpStatus.OK); //
        }

        return new ResponseEntity<>("You are logged in!", HttpStatus.OK);
    }

    @GetMapping("{id}/friends")
    public ResponseEntity<Set<Person>> getFriends(@PathVariable int id) {
        Person person = personRepository.findById(id);

        if (person == null) {
            return new ResponseEntity<>(null, HttpStatus.OK); //
        }

        Set<Person> friends = getFriends(person);

        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    /**
     * get first k most popular persons
     */
    @GetMapping("popular")
    public ResponseEntity<List<Person>> getMostPopular(@RequestParam int k) {
        List<Person> personsList = (List<Person>) personRepository.findAll();
        HashMap<Person, Integer> nrFriends = new HashMap<>();
        for(Person person: personsList) {
            nrFriends.put(person, getFriends(person).size());
        }

        personsList.sort(Comparator.comparing(nrFriends::get));
        Collections.reverse(personsList);

        return new ResponseEntity<>(personsList.subList(0, k), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Person> postPerson(@RequestBody Person person) {
        Person newPerson = new Person(person.getName());
        personRepository.save(newPerson);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @PostMapping("{name}/friends")
    public ResponseEntity<String> postFriend(@PathVariable String name, @RequestBody Person friendPerson) {
        Person person = personRepository.findFirstByName(name);
        Person friend = personRepository.findFirstByName(friendPerson.getName());

        if (person == null) {
            return new ResponseEntity<>("You are not registered", HttpStatus.OK); //
        }

        if (friend == null) {
            return new ResponseEntity<>(friendPerson.getName() + " does not exist", HttpStatus.OK); //
        }

        if (personRepository.findNameByFriends(person).contains(friend) ||
                person.getFriends().contains(friend)) {
            return new ResponseEntity<>("You are already friends with " + friendPerson.getName(), HttpStatus.OK); //
        }

        person.addFriend(friend);
        personRepository.save(person);

        return new ResponseEntity<>("You added " + friend.getName() + "!", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> putPerson(@PathVariable int id, @RequestParam String name) {
        Person person = personRepository.findById(id);
        if (person == null) {
            return new ResponseEntity<>("Person does not exist", HttpStatus.OK); //
        } else {
            person.setName(name);
            personRepository.save(person);
            return new ResponseEntity<>("Person updated", HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePerson(@PathVariable int id) {
        Person person = personRepository.findById(id);
        if (person == null) {
            return new ResponseEntity<>("Person does not exist", HttpStatus.OK); //
        } else {
            personRepository.deleteById(id);
            return new ResponseEntity<>("Person deleted", HttpStatus.OK);
        }
    }

    public Set<Person> getFriends(Person person) {
        Set<Person> friends = personRepository.findNameByFriends(person);
        friends.addAll(person.getFriends());
        return friends;
    }
}
