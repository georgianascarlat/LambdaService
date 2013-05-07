package lambda;

import persistance.manager.UsersServicesManagerImpl;
import persistance.models.SimpleUser;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/5/13
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class LambdaWebService {


    public boolean loginUser(String username, String userType, String password, List<String> services) {

        return new UsersServicesManagerImpl().loginUser(username,userType,password,services);
    }

    public SimpleUser[] relevantUsers(String username){

        Map<String,Set<String>> resultMAp =  new UsersServicesManagerImpl().relevantUsers(username);
        SimpleUser result[] = new SimpleUser[resultMAp.size()];
        int i=0;

        for(Map.Entry<String,Set<String>> entry:resultMAp.entrySet()){
            result[i] = new SimpleUser(entry.getKey(),entry.getValue());
            i++;

        }

        return result;
    }

    public String[] logoutUser(String username){
        return (String[]) new UsersServicesManagerImpl().logoutUser(username).toArray();
    }

}
