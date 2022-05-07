package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "cities", schema = "public", catalog = "homework8")
@NamedQueries({
        @NamedQuery(name = "City.findAll",
                query = "select e from CitiesEntity e order by e.name"),
        @NamedQuery(name = "City.findByCountry",
                query = "select e from CitiesEntity e where e.id_country = ?1"),
        @NamedQuery(name = "City.findByName",
                query = "select e from CitiesEntity e where e.name = ?1"),
        @NamedQuery(name = "City.findById",
                query = "select e from CitiesEntity e where e.id = ?1"),
        @NamedQuery(name = "City.findByNamePattern",
                query = "select e from CitiesEntity e where e.name like ?1")
})
public class CitiesEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name", unique = true)
    private String name;
    @Basic
    @Column(name = "capital")
    private Boolean capital;
    @Basic
    @Column(name = "latitude")
    private Double latitude;
    @Basic
    @Column(name = "longitude")
    private Double longitude;
    @Basic
    @Column(name = "id_country")
    private Integer id_country;

    public CitiesEntity() {
    }

    public CitiesEntity(String name, Boolean capital, Double latitude, Double longitude, Integer id_country) {
        this.name = name;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id_country = id_country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCapital() {
        return capital;
    }

    public void setCapital(Boolean capital) {
        this.capital = capital;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getIdCountry() {
        return id_country;
    }

    public void setIdCountry(Integer id_country) {
        this.id_country = id_country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CitiesEntity that = (CitiesEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (capital != null ? !capital.equals(that.capital) : that.capital != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (id_country != null ? !id_country.equals(that.id_country) : that.id_country != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (capital != null ? capital.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (id_country != null ? id_country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capital=" + capital +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", id_country=" + id_country +
                '}';
    }
}
