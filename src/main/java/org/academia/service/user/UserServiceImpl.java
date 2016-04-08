package org.academia.service.user;

import org.academia.model.User;


import org.academia.model.dao.hibernate.HibernateUserDao;
import org.academia.persistence.hibernate.HibernateTransactionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by codecadet on 31/03/16.
 */
public class UserServiceImpl implements UserService {

    HibernateUserDao hbUser;

    public UserServiceImpl() {
        hbUser = new HibernateUserDao();
    }

    @Override
    public boolean authenticate(String userName, String password) {
       User user = hbUser.findByName(userName);

        if(user == null) return false;

        return user.getPassword().equals(password);

    }

    @Override
    public void addUser(User name) {
        hbUser.update(name);
    }

    @Override
    public User findByName(String name) {
        return hbUser.findByName(name);
    }

    @Override
    public int count() {
        return 0;
    }


/*

    public boolean authenticate(String userName, String password) {

        try {

            Session session = HibernateTransactionManager.getInstance().beginTransaction();

            User userFromDB = (User)session.createCriteria( User.class ).
                    add( Restrictions.eq("username", userName) ).
                    uniqueResult();

            if(userFromDB==null){
                return false;
            }

            if(!userFromDB.getPassword().equals(password)){
                return false;
            }


            model.HibernateSessionManager.getInstance().commitTransaction();

        } catch (HibernateException ex) {

            System.out.println(ex.getMessage());
            HibernateTransactionManager.getInstance().rollbackTransaction();

        }


        return true;
    }

    public void addUser(User user) {
        try {

            Session session = HibernateTransactionManager.getInstance().beginTransaction();

            session.save(user);

            HibernateTransactionManager.getInstance().commitTransaction();

        } catch (HibernateException ex) {

            System.out.println(ex.getMessage());
            HibernateTransactionManager.getInstance().rollbackTransaction();

        }


    }

    public User findByName(String name) {

        User user = null;

        try {

            Session session = HibernateTransactionManager.getInstance().beginTransaction();

            User userFromDB = (User)session.createCriteria( User.class ).
                    add( Restrictions.eq("username", name) ).
                    uniqueResult();


            HibernateTransactionManager.getInstance().commitTransaction();

        } catch (HibernateException ex) {

            System.out.println(ex.getMessage());
            HibernateTransactionManager.getInstance().rollbackTransaction();

        }


        return user;
    }

    public int count() {
        return 0;
    }*/
}
