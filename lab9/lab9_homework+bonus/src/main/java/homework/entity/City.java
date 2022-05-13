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
@Table(name = "cities")
public class City implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private boolean capital;

    private double latitude;

    private double longitude;

    private int population;

    @Column(name = "id_country")
    private int idCountry;

    @ManyToMany(targetEntity = City.class)
    private List<City> sisters;

    public City(String name, boolean capital, double latitude, double longitude, int population, int idCountry) {
        this.name = name;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
        this.idCountry = idCountry;
    }

    @Override
    public String toString() {
        return "\nCity{" + "id=" + id + ", name='" + name + '\'' + ", capital=" + capital + ", latitude=" + latitude + ", longitude=" + longitude + ", population=" + population + ", idCountry=" + idCountry + '}';
    }
}
