package manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Singleton class for managing the EntityManageFactory object
 */
public class Manager {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamplePU");
    private static final EntityManager em = emf.createEntityManager();

    private Manager() {
    }

    public static EntityManager getEntityManager() {
        return em;
    }

    public static void close() {
        em.close();
        emf.close();
    }
}
