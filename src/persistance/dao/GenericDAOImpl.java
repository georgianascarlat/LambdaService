package persistance.dao;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/6/13
 * Time: 4:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericDAOImpl<T> implements GenericDAO<T> {


    protected EntityManager entityManager;

    public GenericDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(T entity) {


        entityManager.persist(entity);

    }

    @Override
    public T read(Class<T> clazz, Object primaryKey) {

        return entityManager.find(clazz,primaryKey);

    }


    @Override
    public void update(T entity) {


        entityManager.merge(entity);


    }

    @Override
    public void delete(T entity) {

        entityManager.remove(entity);

    }

    @Override
    public <K> List<K> findAll(Class<K> clazz) {


        List<K> result = entityManager.
                createQuery("from " + clazz.getCanonicalName(), clazz).getResultList();


        return result;
    }

    @Override
    public <K> List<K> findByColumnValue(Class<K> clazz, String columnName, Object value) {

        String queryString = "from " + clazz.getName() + " where " + columnName + " = " + value;
        System.out.println(queryString);

        List<K> result = entityManager.createQuery(queryString, clazz).getResultList();

        return result;
    }


}
