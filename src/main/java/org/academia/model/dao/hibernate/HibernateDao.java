package org.academia.model.dao.hibernate;

import org.academia.model.dao.Dao;
import org.academia.persistence.hibernate.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.util.List;

public abstract class HibernateDao<T> implements Dao<T> {

    private Class<T> type;

    public HibernateDao(Class<T> type) {
        this.type = type;
    }

    /**
     * @see Dao#save(Object)
     */
    @Override
    public void save(T dao) {}

    /**
     * @see Dao#delete(Object)
     */
    @Override
    public void delete(T dao) {}

    /**
     * @see Dao#findOne(Query)
     */
    @Override
    public T findOne(Query query) {
        return null;
    }

    /**
     * @see Dao#findMany(Query)
     */
    @Override
    public List<T> findMany(Query query) {
        return null;
    }

    public List<T> findAll() {
       return null;
    }

    /**
     * @see Dao#count()
     */
    public long count() {
        return 1L;
    }

}
