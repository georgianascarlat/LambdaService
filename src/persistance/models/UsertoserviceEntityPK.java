package persistance.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/6/13
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class UsertoserviceEntityPK implements Serializable {
    private int userId;

    @Id
    @Column(name = "userId", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int serviceId;

    @Id
    @Column(name = "serviceId", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsertoserviceEntityPK that = (UsertoserviceEntityPK) o;

        if (serviceId != that.serviceId) return false;
        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + serviceId;
        return result;
    }
}
