package repositories;

import entity.CitiesEntity;
import manager.Manager;

import javax.persistence.*;
import java.util.List;

public class CitiesRepository {
    private final EntityManager em = Manager.getEntityManager();

    public List<CitiesEntity> findByCountry(int id) {
        return em.createNamedQuery("City.findByCountry")
                .setParameter(1, id)
                .getResultList();
    }

    /**
     * inserts a city into the database
     * @param city : the city to be inserted (id is not taken)
     */
    public void create(CitiesEntity city) {
        em.getTransaction().begin();
        if (findByName(city.getName()).isEmpty()) {
            em.createNativeQuery("INSERT INTO cities(id_country,name,capital,latitude,longitude) VALUES (?,?,?,?,?)")
                    .setParameter(1, city.getIdCountry())
                    .setParameter(2, city.getName())
                    .setParameter(3, city.getCapital())
                    .setParameter(4, city.getLatitude())
                    .setParameter(5, city.getLongitude())
                    .executeUpdate();
            em.getTransaction().commit();
        }
    }

    /**
     * finds a city by its id and returns it
     */
    public CitiesEntity findById(int id) {
        return (CitiesEntity) em.createNamedQuery("City.findById")
                .setParameter(1, id)
                .getSingleResult();
    }

    /**
     * finds a city by its name and returns its ids
     */
    public List<CitiesEntity> findByName(String name) {
        return em.createNamedQuery("City.findByName")
                .setParameter(1, name)
                .getResultList();
    }

    /**
     * return a list of the cities that have the name with the specified pattern
     * @param pattern
     * @return
     */
    public List<CitiesEntity> findByNamePattern(String pattern) {
        return em.createNamedQuery("City.findByNamePattern")
                .setParameter(1, pattern)
                .getResultList();
    }
}