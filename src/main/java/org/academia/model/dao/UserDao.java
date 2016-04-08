package org.academia.model.dao;

/**
 * Created by codecadet on 07/04/16.
 */
public interface UserDao<T> extends Dao<T> {





    /**
     * Returns a new dao object corresponding to the database table row matching provided name
     * @param name
     * @return
     */
    T findByName(String name);
}
