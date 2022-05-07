package repositories;

import entity.ContinentsEntity;
import manager.Manager;

import javax.persistence.EntityManager;
import java.util.List;

public class ContinentsRepository {
    private final EntityManager em = Manager.getEntityManager();

    /**
     * inserts a continent into the database
     * @param continent : the continent to be inserted
     */
    public void create(ContinentsEntity continent) {
        if (findByName(continent.getName()).isEmpty()) {
            em.getTransaction().begin();
            em.createNativeQuery("INSERT INTO continents(name) VALUES (?)")
                    .setParameter(1, continent.getName())
                    .executeUpdate();
            em.getTransaction().commit();
        }
    }

    /**
     * @return : the city with the specified id
     */
    public ContinentsEntity findById(int id) {
        return (ContinentsEntity) em.createNamedQuery("Continent.findById")
                .setParameter(1, id)
                .getSingleResult();
    }

    /**
     * @return : finds the continents with the given name and returns them in a list
     */
    public List<ContinentsEntity> findByName(String name) {
        return em.createNamedQuery("Continent.findByName")
                .setParameter(1, name)
                .getResultList();
    }

    /**
     * return a list of the continents that have the name with the specified pattern
     */
    public List<ContinentsEntity> findByNamePattern(String pattern) {
        return em.createNamedQuery("Continent.findByNamePattern")
                .setParameter(1, pattern)
                .getResultList();
    }
}
