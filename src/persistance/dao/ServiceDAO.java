package persistance.dao;

import persistance.models.ServicesEntity;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/6/13
 * Time: 10:07 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ServiceDAO extends GenericDAO<ServicesEntity> {
    public void setUserDAO(UserDAO userDAO);
}
