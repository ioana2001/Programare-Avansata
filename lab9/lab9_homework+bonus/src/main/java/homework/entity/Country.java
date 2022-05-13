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
@Table(name = "countries")
public class Country implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String code;

    @Column(name = "continent")
    private int idContinent;

    @OneToMany(targetEntity = City.class)
    private List<City> cities;

    public Country(String name, String code, int continent) {
        this.name = name;
        this.code = code;
        this.idContinent = continent;
    }

    @Override
    public String toString() {
        return "\nCountry{" + "id=" + id + ", name='" + name + '\'' + ", code='" + code + '\'' + ", continent=" + idContinent + '}';
    }
}
