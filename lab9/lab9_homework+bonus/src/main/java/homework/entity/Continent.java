package homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "continents")
public class Continent implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(targetEntity = Country.class)
    private List<Country> countries;

    public Continent(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nContinents{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
