package compulsory.controller;

import compulsory.entity.Person;
import compulsory.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @GetMapping()
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(
                person -> {
                    persons.add(new Person(person.getId(), person.getName()));
                }
        );
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Person> postPerson(@RequestBody Person person) {
        Person newPerson = new Person(person.getName());
        personRepository.save(newPerson);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> putPerson(@PathVariable int id, @RequestParam String name) {
        Person person = personRepository.findById(id);
        if(person == null) {
            return new ResponseEntity<>("Person does not exist", HttpStatus.NOT_FOUND);
        } else {
            person.setName(name);
            personRepository.save(person);
            return new ResponseEntity<>("Person updated", HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> putPerson(@PathVariable int id) {
        Person person = personRepository.findById(id);
        if (person == null) {
            return new ResponseEntity<>("Person does not exist", HttpStatus.NOT_FOUND);
        } else {
            personRepository.deleteById(id);
            return new ResponseEntity<>("Person deleted", HttpStatus.OK);
        }
    }
}
