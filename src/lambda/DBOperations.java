package lambda;

import persistance.HibernateUtil;
import persistance.dao.ServiceDAO;
import persistance.dao.ServiceDAOImpl;
import persistance.dao.UserDAO;
import persistance.dao.UserDAOImpl;
import persistance.models.ServicesEntity;
import persistance.models.UsersEntity;
import org.hibernate.ejb.HibernatePersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.spi.PersistenceProvider;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/5/13
 * Time: 9:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBOperations {

    static final String SELECT_QUERY_USER = "from UsersEntity where username = :username";
    static final String SELECT_QUERY_SERVICE = "from ServicesEntity where name = :name";

    private EntityManager entityManager;

    DBOperations() {

        entityManager = getEntityManager();
    }

    void endOperations() {
        entityManager.close();
    }


    public static void main(String args[]) {

        EntityManager entityManager = HibernateUtil.getManager();

        UserDAO userDAO = new UserDAOImpl(entityManager);
        ServiceDAO serviceDAO = new ServiceDAOImpl(entityManager);

        userDAO.setServiceDAO(serviceDAO);
        serviceDAO.setUserDAO(userDAO);


        /////////////////


        UsersEntity user1 = new UsersEntity("didi", "", "", 1);
        UsersEntity user2 = new UsersEntity("lili","","",1);

        ServicesEntity service0 = new ServicesEntity("serv0");
        ServicesEntity service1 = new ServicesEntity("serv1");
        ServicesEntity service2 = new ServicesEntity("serv2");


        service0.getUsers().add(user2);
        user2.getServices().add(service0);

        user1.getServices().add(service1);
        user1.getServices().add(service2);
        service1.getUsers().add(user1);
        service2.getUsers().add(user1);

        /////////////////


        EntityTransaction transaction = HibernateUtil.getTransaction(entityManager);

        transaction.begin();


         //userDAO.create(user1);

        //userDAO.delete(user1);

        // serviceDAO.delete(service1);

        //serviceDAO.create(service0);




        transaction.commit();

        HibernateUtil.closeManager(entityManager);

    }

    void persistUser(String username, String userType, String password, List<String> services) {

        UsersEntity usersEntity = new UsersEntity(username, userType, password, 1);
        List<ServicesEntity> servicesEntities = new LinkedList<ServicesEntity>();

        for (String name : services) {
            ServicesEntity service = new ServicesEntity(name);
            servicesEntities.add(service);
        }

        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {

            entityTransaction.begin();

            //TODO: Persist everything

            entityTransaction.commit();


        } catch (RuntimeException e) {

            entityTransaction.rollback();

            throw e;
        }

    }

    UsersEntity getUserByName(String username) {

        UsersEntity user = null;

        List<UsersEntity> usersEntities = entityManager.createQuery(SELECT_QUERY_USER, UsersEntity.class)
                .setParameter("username", username).getResultList();

        if (usersEntities.size() > 0)
            user = usersEntities.get(0);

        return user;
    }

    ServicesEntity getServiceByName(String servicename) {

        ServicesEntity service = null;

        List<ServicesEntity> serviceEntities = entityManager.createQuery(SELECT_QUERY_SERVICE, ServicesEntity.class)
                .setParameter("name", servicename).getResultList();

        if (serviceEntities.size() > 0)
            service = serviceEntities.get(0);

        return service;
    }

    private static EntityManager getEntityManager() {

        PersistenceProvider persistenceProvider = new HibernatePersistence();
        EntityManagerFactory entityManagerFactory = persistenceProvider.
                createEntityManagerFactory("PersistenceUnit", new HashMap());
        return entityManagerFactory.createEntityManager();

    }
}
