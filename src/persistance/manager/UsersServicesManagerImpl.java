package persistance.manager;

import persistance.HibernateUtil;
import persistance.models.ServicesEntity;
import persistance.models.UsersEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/7/13
 * Time: 10:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class UsersServicesManagerImpl extends UsersServicesManager {

    @Override
    public boolean loginUser(String name, String type, String pass, List<String> services) {

        // init entity manager and userDAO, serviceDAO
        EntityManager manager = initContext();
        boolean  ok = true;
        // obtain an entity transaction object
        EntityTransaction transaction = HibernateUtil.getTransaction(manager);

        // create model objects
        UsersEntity usersEntity = new UsersEntity(name,type,pass,1);
        List<ServicesEntity> servicesEntities = new LinkedList<ServicesEntity>();

        for(String serviceName:services){
            ServicesEntity service = new ServicesEntity(serviceName);
            service.getUsers().add(usersEntity);
            servicesEntities.add(service);
        }

        usersEntity.getServices().addAll(servicesEntities);


        try{
            transaction.begin();


            UsersEntity foundUser = userDAO.read(UsersEntity.class,name);

            if(null == foundUser){
                userDAO.create(usersEntity);
            } else {
                if(!pass.equals(foundUser.getPassword()) ||
                        !type.equals(foundUser.getUsertype())) {
                    throw new RuntimeException();
                }
                usersEntity.setActive(1);
                userDAO.update(usersEntity);
            }

            transaction.commit();

        } catch (RuntimeException e){
            transaction.rollback();
            ok = false;
            System.err.println("Cannot login user "+name);
        }  finally {
            HibernateUtil.closeManager(manager);
        }

        return ok;
    }

    @Override
    public Map<String, Set<String>> relevantUsers(String name) {

        Map<String, Set<String>> relUsers = new HashMap<String, Set<String>>();

        // init entity manager and userDAO, serviceDAO
        EntityManager manager = initContext();
        // obtain an entity transaction object
        EntityTransaction transaction = HibernateUtil.getTransaction(manager);


        try{

            transaction.begin();
            UsersEntity usersEntity = userDAO.read(UsersEntity.class,name);
            if(null == usersEntity)
                throw new RuntimeException();
            Set<String> services = usersEntity.makeServicesIntoStringList();
            String type = usersEntity.getUsertype();

            for(String serviceName:services){
                ServicesEntity service = serviceDAO.read(ServicesEntity.class,serviceName);
                if(null == service)
                    throw new RuntimeException();
                Collection<UsersEntity> users = service.getUsers();

                for(UsersEntity user:users){
                    if(!type.equals(user.getUsertype()) && user.getActive() == 1){
                            relUsers.put(new String(user.getUsername()),user.makeServicesIntoStringList());
                    }
                }

            }

            transaction.commit();

        } catch (RuntimeException e){
            transaction.rollback();
            relUsers = null;
            System.err.println("Cannot obtain relevant users for user "+name);
        }  finally {
            HibernateUtil.closeManager(manager);
        }



        return relUsers;
    }

    @Override
    public Set<String> logoutUser(String name) {

        Set<String> affectedUsers = new HashSet<String>();

        // init entity manager and userDAO, serviceDAO
        EntityManager manager = initContext();
        // obtain an entity transaction object
        EntityTransaction transaction = HibernateUtil.getTransaction(manager);




        try{
            transaction.begin();

            UsersEntity usersEntity = userDAO.read(UsersEntity.class,name);
            if(null == usersEntity)
                throw new RuntimeException();
            Set<String> services = usersEntity.makeServicesIntoStringList();
            String type = usersEntity.getUsertype();

            for(String serviceName:services){
                ServicesEntity service = serviceDAO.read(ServicesEntity.class,serviceName);
                if(null == service)
                    throw new RuntimeException();
                Collection<UsersEntity> users = service.getUsers();

                for(UsersEntity user:users){
                    if(!type.equals(user.getUsertype()) && user.getActive() == 1){
                            affectedUsers.add(user.getUsername());
                    }
                }

            }

            usersEntity.setActive(0);
            userDAO.update(usersEntity);


            transaction.commit();


        } catch (RuntimeException e){
            transaction.rollback();
            affectedUsers = null;
            System.err.println("Cannot logout user "+name);
        }  finally {
            HibernateUtil.closeManager(manager);
        }


        return affectedUsers;

    }
}
