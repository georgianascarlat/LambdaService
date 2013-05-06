package lambda;

import persistance.models.UsersEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/5/13
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class LambdaWebService {


    public boolean loginUser(String username, String userType, String password, List<String> services) {

        DBOperations operations = new DBOperations();

        UsersEntity user = operations.getUserByName(username);

        boolean ok = true;

        try {
            if (null != user) {
                operations.persistUser(username, userType, password, services);
            } else {
                //TODO: Modify access
            }

        } catch (RuntimeException e) {
            ok = false;
        } finally {
            operations.endOperations();
        }


        return ok;
    }

    public String sayHelloWorldFrom(String from) {
        String result = "Hello, world, from " + from;
        System.out.println(result);
        return result;
    }
}
