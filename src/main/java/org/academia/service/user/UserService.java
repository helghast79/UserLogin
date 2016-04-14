package org.academia.service.user;

import org.academia.model.User;
import org.academia.service.Service;

/**
 * Created by codecadet on 15/03/16.
 */
public interface UserService extends Service {

    /**
     * Authenticates the user using the given username and password
     * @param username the user name
     * @param password the user password
     * @return true if authenticated
     */
    boolean authenticate(String username, String password);

    /**
     * Adds a new user
     * @param user the new user to add
     */
    public void addUser(User user);

    /**
     * Adds a role to a user
     *
     * @param username the name of the user to add the role to
     * @param role the role to add to the user
     */
    public void addUserRole(String username, String role);


    /**
     * Finds a user by name
     * @param username the user name used to lookup a user
     * @return a new User if found, null otherwise
     */
    public User findByName(String username);

    /**
     * Count the number of existing users
     * @return the number of users
     */
    public long count();

}
