package org.academia.model.dao;

import org.hibernate.Query;

import java.util.List;

public interface Dao<T> {

    /**
     * Persists the dao object as a user table row
     * @param dao the dao object to persist
     */
    public void save(T dao);

    /**
     * Deletes the row corresponding to the dao object from the database table
     * @param dao
     */
    public void delete(T dao);

    /**
     * Returns a new dao object corresponding to the first row found
     * in the database table according to the provided query
     * or null if no record was found
     *
     * @param query the object query to use
     * @return the dao object
     */
    public T findOne(Query query);

    /**
     * Returns a new dao object corresponding to the database table row matching provided name
     * @param name
     * @return
     */
    public T findByName(String name);

    /**
     * Returns a collection view of dao objects corresponding to all database table rows
     * matching the provided object query
     *
     * @param query the object query to use
     * @return the list view
     */
    public List<T> findMany(Query query);

    /**
     * Returns a collection view of the entire database table
     *
     * @return the list view
     */
    public List<T> findAll();

    /**
     * Returns the number of rows on the database table
     * @return
     */
    public long count();

}
