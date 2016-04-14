package org.academia.model.dao.hibernate;

import org.academia.model.User;
import org.academia.model.dao.Dao;
import org.academia.model.dao.UserDao;
import org.academia.persistence.TransactionException;
import org.academia.persistence.hibernate.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Dao Implementation for the User model class
 * @see User
 */
public class HibernateUserDao extends HibernateDao<User> implements UserDao {





    public HibernateUserDao(HibernateSessionManager hibernateSessionManager) {
        super(User.class,hibernateSessionManager);
    }



    /**
     * @see Dao#findByName(String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public User findByName(String name) {

        try {
            List<User> users = getHibernateSessionManager().getSession().createCriteria(User.class)
                    .add(Restrictions.eq("username", name)).list();

            return users.isEmpty() ? null : users.get(0);

        } catch (HibernateException hex) {
            throw new TransactionException(hex);
        }
    }
}

