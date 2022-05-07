package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "continents", schema = "public", catalog = "homework8")
@NamedQueries({
        @NamedQuery(name = "Continent.findAll",
                query = "select e from ContinentsEntity e order by e.name"),
        @NamedQuery(name = "Continent.findById",
                query = "select e from ContinentsEntity e where e.id = ?1"),
        @NamedQuery(name = "Continent.findByName",
                query = "select e from ContinentsEntity e where e.name = ?1"),
        @NamedQuery(name = "Continent.findByNamePattern",
                query = "select e from ContinentsEntity e where e.name like ?1")
})
public class ContinentsEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name", unique = true)
    private String name;

    public ContinentsEntity() {
    }

    public ContinentsEntity(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContinentsEntity that = (ContinentsEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContinentsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
