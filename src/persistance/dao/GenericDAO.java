package persistance.dao;

/**
 * Created with IntelliJ IDEA.
 * User: gia
 * Date: 5/6/13
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Query;

public interface GenericDAO<T> {

    public void create(T entity);

    public T read(Class<T> clazz, Object primaryKey);

    public void update(T entity);

    public void delete(T entity);

    public <K> List<K> findAll(Class<K> clazz);

    public <K> List<K> findByColumnValue(Class<K> clazz, String columnName, Object value);
}

