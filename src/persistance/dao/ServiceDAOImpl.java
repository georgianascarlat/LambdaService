package persistance.dao;

import persistance.models.ServicesEntity;
import persistance.models.UsersEntity;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/6/13
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServiceDAOImpl extends GenericDAOImpl<ServicesEntity> implements ServiceDAO {

    private UserDAO userDAO;

    public ServiceDAOImpl(EntityManager entityManager) {
        super(entityManager);

    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void delete(ServicesEntity entity) {

        if (userDAO == null)
            throw new IllegalStateException("UserDAO not initialized.");

        UsersEntity user;
        Collection<UsersEntity> users = entity.getUsers();

        for (UsersEntity usersEntity : users) {

            user = userDAO.read(UsersEntity.class,usersEntity.getUsername());
            if (user != null) {

                user.getServices().remove(entity);
                userDAO.update(user);
            }
        }
        super.delete(entityManager.getReference(ServicesEntity.class, entity.getName()));
    }
}
