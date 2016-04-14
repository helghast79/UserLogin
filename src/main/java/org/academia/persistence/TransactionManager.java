package org.academia.persistence;

import org.hibernate.Session;

/**
 * Created by codecadet on 08/04/16.
 */
public interface TransactionManager {

   void begin();

    void commit();

    void rollback();

}
