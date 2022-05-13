package compulsory.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    public Person(String name) {
        this.name = name;
    }

    @ManyToMany(targetEntity = Person.class, fetch = FetchType.EAGER)
    private List<Person> friends = new ArrayList<>();

    public void addFriend(Person person) {
        friends.add(person);
    }

    @Override
    public String toString() {
        return "\nPerson{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
