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
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDAOImpl extends GenericDAOImpl<UsersEntity> implements UserDAO {

    private ServiceDAO serviceDAO;

    public UserDAOImpl(EntityManager entityManager) {
        super(entityManager);

    }

    public void setServiceDAO(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @Override
    public void create(UsersEntity entity) {

        checkStateIntegrity();

        super.create(entity);

        ServicesEntity service;
        Collection<ServicesEntity> services = entity.getServices();

        for (ServicesEntity servicesEntity : services) {

            service = serviceDAO.read(ServicesEntity.class,servicesEntity.getName());
            if (service != null) {

                service.getUsers().add(entity);
                serviceDAO.update(service);
            } else {
                serviceDAO.create(servicesEntity);
            }
        }
    }

    private void checkStateIntegrity() {
        if (serviceDAO == null)
            throw new IllegalStateException("ServiceDAO not initialized.");
    }

    @Override
    public void delete(UsersEntity entity) {

        checkStateIntegrity();

        ServicesEntity service;
        Collection<ServicesEntity> services = entity.getServices();

        for (ServicesEntity servicesEntity : services) {

            service = serviceDAO.read(ServicesEntity.class,servicesEntity.getName());
            if (service != null) {

                service.getUsers().remove(entity);
                serviceDAO.update(service);
            }
        }

        super.delete(entityManager.getReference(UsersEntity.class, entity.getUsername()));
    }
}
