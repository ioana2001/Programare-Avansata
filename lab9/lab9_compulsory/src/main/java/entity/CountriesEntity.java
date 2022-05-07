package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "countries", schema = "public", catalog = "homework8")
@NamedQueries({
        @NamedQuery(name = "Country.findAll",
                query = "select e from CountriesEntity e order by e.name"),
        @NamedQuery(name = "Country.findById",
                query = "select e from CountriesEntity e where e.id = ?1"),
        @NamedQuery(name = "Country.findByName",
                query = "select e from CountriesEntity e where e.name = ?1"),
        @NamedQuery(name = "Country.findByNamePattern",
                query = "select e from CountriesEntity e where e.name like ?1")
})
public class CountriesEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name", unique = true)
    private String name;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "continent")
    private Integer continent;

    public CountriesEntity() {
    }

    public CountriesEntity(String name, String code, Integer continent) {
        this.name = name;
        this.code = code;
        this.continent = continent;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getContinent() {
        return continent;
    }

    public void setContinent(Integer continent) {
        this.continent = continent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountriesEntity that = (CountriesEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (continent != null ? !continent.equals(that.continent) : that.continent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (continent != null ? continent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CountriesEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", continent=" + continent +
                '}';
    }
}
