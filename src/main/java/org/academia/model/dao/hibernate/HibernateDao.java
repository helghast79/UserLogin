package org.academia.model.dao.hibernate;

import org.academia.model.dao.Dao;
import org.academia.persistence.TransactionException;
import org.academia.persistence.hibernate.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;

import java.util.List;

public abstract class HibernateDao<T> implements Dao<T> {

    private Class<T> type;

  private HibernateSessionManager hibernateSessionManager;


    public HibernateDao(Class<T> type, HibernateSessionManager hibernateSessionManager) {
        this.type = type;
        this.hibernateSessionManager = hibernateSessionManager;
    }


    public HibernateSessionManager getHibernateSessionManager() {
        return hibernateSessionManager;
    }

    /**
     * @see Dao#save(Object)
     */
    @Override
    public void save(T dao) {

        try {

            hibernateSessionManager.getSession().saveOrUpdate(dao);

        } catch (HibernateException hex) {
            throw new TransactionException(hex);
        }

    }

    /**
     * @see Dao#delete(Object)
     */
    @Override
    public void delete(T dao) {

        try {

            hibernateSessionManager.getSession().delete(dao);

        } catch (HibernateException hex) {
            throw new TransactionException(hex);
        }

    }

    /**
     * @see Dao#findOne(Query)
     */
    @SuppressWarnings("unchecked")
    @Override
    public T findOne(Query query) {

        try {

            return (T) query.uniqueResult();

        } catch (HibernateException hex) {
            throw new TransactionException(hex);
        }

    }

    /**
     * @see Dao#findMany(Query)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findMany(Query query) {

        try {

            return (List<T>) query.list();

        } catch (HibernateException hex) {
            throw new TransactionException(hex);
        }

    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {

        try {

            return hibernateSessionManager.getSession().createCriteria(type)
                    .list();

        } catch (HibernateException hex) {
            throw new TransactionException(hex);
        }

    }

    /**
     * @see Dao#count()
     */
    public long count() {

        try {

            return (Long) hibernateSessionManager.getSession().createCriteria(type)
                    .setProjection(Projections.rowCount())
                    .uniqueResult();

        } catch (HibernateException hex) {
            throw new TransactionException(hex);
        }

    }

}
