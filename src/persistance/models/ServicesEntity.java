package persistance.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/6/13
 * Time: 8:50 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "services", schema = "", catalog = "lambdadb")
@Entity
public class ServicesEntity {

    public ServicesEntity() {
        users = new LinkedList<UsersEntity>();
    }

    public ServicesEntity(String name) {
        this();
        this.name = name;
    }

    public ServicesEntity(String resourceName, String name) {
        this();
        this.resourceName = resourceName;
        this.name = name;
    }

    private String name;

    @javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String resourceName;

    @javax.persistence.Column(name = "resource_name", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServicesEntity that = (ServicesEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    private Collection<UsersEntity> users;

    @javax.persistence.JoinTable(name = "usertoservice", catalog = "lambdadb", schema = "", joinColumns = @javax.persistence.JoinColumn(name = "service_name", referencedColumnName = "name", nullable = false), inverseJoinColumns = @javax.persistence.JoinColumn(name = "user_name", referencedColumnName = "username", nullable = false))
    @ManyToMany
    public Collection<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(Collection<UsersEntity> users) {
        this.users = users;
    }
}
