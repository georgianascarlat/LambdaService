package lambda;

import persistance.HibernateUtil;
import persistance.dao.ServiceDAO;
import persistance.dao.ServiceDAOImpl;
import persistance.dao.UserDAO;
import persistance.dao.UserDAOImpl;
import persistance.manager.UsersServicesManager;
import persistance.manager.UsersServicesManagerImpl;
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



    public static void main(String args[]) {

        UsersServicesManager manager = new UsersServicesManagerImpl();

        List<String> services = new LinkedList<String>();
        services.add("serv2");
        services.add("serv0");

        manager.loginUser("didi","buyer","didi",services);

        services.clear();
        services.add("serv1");
        services.add("serv2");

        manager.loginUser("lili","seller","lili",services);

        services.clear();
        services.add("serv0");
        services.add("serv3");

        manager.loginUser("kiki","seller","kiki",services);

        System.out.println(manager.relevantUsers("lili"));
        System.out.println(manager.relevantUsers("didi"));

        System.out.println("After logout: " + manager.logoutUser("lili"));
        System.out.println(manager.relevantUsers("didi"));



    }


}
