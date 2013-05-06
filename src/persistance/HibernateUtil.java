package persistance;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/6/13
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */

import org.hibernate.ejb.HibernatePersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.spi.PersistenceProvider;
import java.util.HashMap;

/**
 * @author leonidas
 */
public class HibernateUtil {

    private static final EntityManagerFactory entityManagerFactory;

    static {
        PersistenceProvider persistenceProvider = new HibernatePersistence();
        entityManagerFactory = persistenceProvider.
                createEntityManagerFactory("PersistenceUnit", new HashMap());
    }

    public static EntityManager getManager() {

        return entityManagerFactory.createEntityManager();
    }

    public static EntityTransaction getTransaction(EntityManager entityManager) {

        return entityManager.getTransaction();
    }

    public static void closeManager(EntityManager entityManager) {
        entityManager.close();
    }
}

