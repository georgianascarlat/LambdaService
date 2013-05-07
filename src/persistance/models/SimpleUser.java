package persistance.models;

import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/7/13
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Set<String>  serviceNames;

    public SimpleUser() {
    }

    public SimpleUser(String name, Set<String> serviceNames) {
        this.name = name;
        this.serviceNames = serviceNames;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getServiceNames() {
        return serviceNames;
    }

    public void setServiceNames(Set<String> serviceNames) {
        this.serviceNames = serviceNames;
    }
}
