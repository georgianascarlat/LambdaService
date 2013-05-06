package persistance.dao;

import persistance.models.UsersEntity;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/6/13
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserDAO extends GenericDAO<UsersEntity> {
    public void setServiceDAO(ServiceDAO serviceDAO);


}
