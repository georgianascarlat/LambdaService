package lambda;

import persistance.manager.UsersServicesManagerImpl;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/5/13
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class LambdaWebService {


    public boolean loginUser(String username, String userType, String password, String services[]) {

        return new UsersServicesManagerImpl().loginUser(username,userType,password, Arrays.asList(services));
    }

    public String[] relevantUsers(String type,String serviceName){

        return new UsersServicesManagerImpl().relevantUsers(type,serviceName).toArray(new String[0]);
    }

    public String[] logoutUser(String user){

        return new UsersServicesManagerImpl().logoutUser(user).toArray(new String[0]);
    }

}
