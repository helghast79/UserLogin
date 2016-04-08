package org.academia.model.dao.hibernate;


import org.academia.model.User;
import org.academia.model.dao.DataAccessLayerException;
import org.academia.persistence.hibernate.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.util.List;


/**
 * Created by codecadet on 07/04/16.
 */
public class HibernateUserDao extends HibernateDao<User> {

    public HibernateUserDao() {
        super((Class)User.class);
    }


    /**
     * Delete a detached Event from the database.
     * @param user
     */
    public void delete(User user) throws DataAccessLayerException {
        //super.delete((T)user);

        try {
            HibernateSessionManager.beginTransaction().delete(user);

            HibernateSessionManager.commitTransaction();
        } catch (HibernateException e) {
            //handleException(e);
        } finally {
            //HibernateSessionManager.close();
        }

    }

    /**
     * Find an Event by its primary key.
     * @param id
     * @return
     */
    public User find(Long id) throws DataAccessLayerException {

        /*Query query = HibernateSessionManager.getSession().createQuery("from users where uid = :id");
        query.setParameter("id", id);
        return (User) super.findOne(query);*/

        User object = null;
        try {
            Query query = HibernateSessionManager.beginTransaction().createQuery("from User where uid = :id");

            query.setParameter("id", id);
            object =  (User)query.uniqueResult();

            HibernateSessionManager.commitTransaction();
        } catch (HibernateException e) {
            //handleException(e);
        } finally {
            //HibernateSessionManager.close();
        }
        return object;


    }

    /**
     * Updates the state of a detached Event.
     *
     * @param user
     */
    public void update(User user) throws DataAccessLayerException {
        //super.save((T)user);

        try {
            HibernateSessionManager.beginTransaction().saveOrUpdate(user);;

            HibernateSessionManager.commitTransaction();
        } catch (HibernateException e) {
            //handleException(e);
        } finally {
            //HibernateSessionManager.close();
        }

    }

    @Override
    public  User findByName(String name) {
        /*Query query = HibernateSessionManager.getSession().createQuery("from users where username = :username ");
        query.setParameter("username", name);
        return (User) super.findOne(query);*/


        User object = null;
        try {
            Query query =HibernateSessionManager.beginTransaction().createQuery("from User where username = :username ");;

            query.setParameter("username", name);
            object =  (User)query.uniqueResult();

            HibernateSessionManager.commitTransaction();
        } catch (HibernateException e) {
            //handleException(e);
        } finally {
            //HibernateSessionManager.close();
        }
        return object;



    }

    /**
     * Finds all Events in the database.
     * @return
     */
    public List<User> findAll() throws DataAccessLayerException {
         List<User> list = null;
        try {

            Query query = HibernateSessionManager.beginTransaction().createQuery("from User ");
            list = query.list();

            HibernateSessionManager.commitTransaction();
        } catch (HibernateException e) {
            //handleException(e);
        } finally {
            //HibernateSessionManager.close();
        }
        return list;
    }
}
