package org.academia.model.dao;

import org.academia.model.User;

public interface UserDao extends Dao<User> {

    public User findByName(String name);

}
