package org.academia.service.user;

import org.academia.model.Role;
import org.academia.model.User;


import org.academia.model.dao.RoleDao;
import org.academia.model.dao.UserDao;
import org.academia.persistence.TransactionManager;
import org.hibernate.TransactionException;

/**
 * Created by codecadet on 31/03/16.
 */
public class UserServiceImpl implements UserService {

    UserDao userDao;
    RoleDao roleDao;
    TransactionManager tx;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, TransactionManager tx) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.tx = tx;
    }

    /**
     * Authenticates the user using the given username and password
     *
     * @param username the user name
     * @param password the user password
     * @return true if authenticated
     */
    @Override
    public boolean authenticate(String username, String password) {

        boolean result = false;

        try {

            tx.begin();

            User user = userDao.findByName(username);
            result = (user != null && user.getPassword().equals(password));

            tx.commit();

        } catch (TransactionException ex) {

            System.out.println(ex.getMessage());
            tx.rollback();

        }

        return result;

    }

    /**
     * Adds a new user
     *
     * @param user the new user to add
     */
    @Override
    public void addUser(User user) {

        try {

            tx.begin();

            if (userDao.findByName(user.getUsername()) == null) {
                userDao.save(user);
            }

            tx.commit();

        } catch (TransactionException ex) {

            System.out.println(ex.getMessage());
            tx.rollback();

        }

    }

    @Override
    public void addUserRole(String username, String role) {

        try {

            tx.begin();

            User user = userDao.findByName(username);
            Role r = roleDao.findByName(role);

            if (user != null && r != null) {
                user.getRoleSet().add(r);
                userDao.save(user);
            }

            tx.commit();

        } catch (TransactionException ex) {

            System.out.println(ex.getMessage());
            tx.rollback();

        }

    }

    /**
     * Finds a user by name
     *
     * @param username the user name used to lookup a user
     * @return a new User if found, null otherwise
     */
    @Override
    public User findByName(String username) {

        User user = null;

        try {

            tx.begin();

            user = userDao.findByName(username);

            tx.commit();

        } catch (TransactionException ex) {

            System.out.println(ex.getMessage());
            tx.rollback();

        }

        return user;

    }

    /**
     * Count the number of existing users
     *
     * @return the number of users
     */
    @Override
    public long count() {

        long size = 0;

        try {

            tx.begin();

            size = userDao.count();

            tx.commit();

        } catch (TransactionException ex) {

            System.out.println(ex.getMessage());
            tx.rollback();

        }

        return size;

    }


}
