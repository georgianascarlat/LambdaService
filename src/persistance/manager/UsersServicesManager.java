package persistance.manager;

import persistance.HibernateUtil;
import persistance.dao.ServiceDAO;
import persistance.dao.ServiceDAOImpl;
import persistance.dao.UserDAO;
import persistance.dao.UserDAOImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/7/13
 * Time: 10:08 AM
 * To change this template use File | Settings | File Templates.
 */
public  abstract class UsersServicesManager {

    protected UserDAO userDAO;
    protected ServiceDAO serviceDAO;

    public EntityManager initContext(){
        EntityManager entityManager = HibernateUtil.getManager();

        userDAO = new UserDAOImpl(entityManager);
        serviceDAO = new ServiceDAOImpl(entityManager);

        userDAO.setServiceDAO(serviceDAO);
        serviceDAO.setUserDAO(userDAO);

        return entityManager;
    }

    public abstract boolean loginUser(String name,String type,  String pass, List<String> services);

    public abstract Map<String,Set<String>> relevantUsers(String name);

    public abstract Set<String> logoutUser(String name);
}
