package persistance.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/6/13
 * Time: 8:50 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "users", schema = "", catalog = "lambdadb")
@Entity
public class UsersEntity {

    public UsersEntity() {
        services = new LinkedList<ServicesEntity>();
    }

    public UsersEntity(String username, String usertype, String password, int active) {
        this();
        this.username = username;
        this.usertype = usertype;
        this.password = password;
        this.active = active;
    }

    private String username;

    @javax.persistence.Column(name = "username", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String usertype;

    @javax.persistence.Column(name = "usertype", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    private String password;

    @javax.persistence.Column(name = "password", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private int active;

    @javax.persistence.Column(name = "active", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    public Set<String> makeServicesIntoStringList(){
        Set<String> servs = new HashSet<String>();

        for(ServicesEntity service:services){
            servs.add(service.getName());
        }

        return servs;
    }

    private Collection<ServicesEntity> services;

    @ManyToMany(mappedBy = "users")
    public Collection<ServicesEntity> getServices() {
        return services;
    }

    public void setServices(Collection<ServicesEntity> services) {
        this.services = services;
    }
}
