package org.academia.service.user;

import org.academia.model.User;

/**
 * Created by codecadet on 15/03/16.
 */
public interface UserService {


    boolean authenticate(String userName, String password);

    void addUser(User name);

    User findByName(String name);

    int count();


}
