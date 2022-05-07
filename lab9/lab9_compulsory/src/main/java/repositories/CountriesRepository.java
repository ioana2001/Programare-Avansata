package repositories;

import entity.ContinentsEntity;
import entity.CountriesEntity;
import manager.Manager;

import javax.persistence.EntityManager;
import java.util.List;

public class CountriesRepository {
    private final EntityManager em = Manager.getEntityManager();

    /**
     * inserts a country into the database
     * @param country : the country to be inserted
     */
    public void create(CountriesEntity country) {
        if (findByName(country.getName()).isEmpty()) {
            em.getTransaction().begin();
            em.createNativeQuery("INSERT INTO countries(name,code,continent) VALUES (?,?,?)")
                    .setParameter(1, country.getName())
                    .setParameter(2, country.getCode())
                    .setParameter(3, country.getContinent())
                    .executeUpdate();
            em.getTransaction().commit();
        }
    }

    /**
     * finds the country by its id and returns it
     */
    public CountriesEntity findById(int id) {
        return (CountriesEntity) em.createNamedQuery("Country.findById")
                .setParameter(1, id)
                .getSingleResult();
    }

    /**
     * finds the countries with the given name and returns them in a list
     */
    public List<CountriesEntity> findByName(String name) {
        return em.createNamedQuery("Country.findByName")
                .setParameter(1, name)
                .getResultList();
    }

    /**
     * return a list of the countries that have the name with the specified pattern
     */
    public List<ContinentsEntity> findByNamePattern(String pattern) {
        return em.createNamedQuery("Country.findByNamePattern")
                .setParameter(1, pattern)
                .getResultList();
    }
}
