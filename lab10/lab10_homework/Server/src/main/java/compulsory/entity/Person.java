package compulsory.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(targetEntity = Person.class, fetch = FetchType.EAGER)
    private Set<Person> friends = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person")
    private List<Message> messages;

    public Person(String name) {
        this.name = name;
    }
    public void addFriend(Person person) {
        friends.add(person);
    }

    public void addMessage(String message) {
        Message messageObj = new Message(getId(), message);
        messages.add(messageObj);
    }

    @Override
    public String toString() {
        return "\nPerson{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
