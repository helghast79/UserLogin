package org.academia.persistence.hibernate;

import org.academia.persistence.TransactionManager;
import org.hibernate.Session;

/**
 * Created by codecadet on 08/04/16.
 */
public class HibernateTransactionManager implements TransactionManager {


    private HibernateSessionManager hibernateSessionManager;


    public void setHibernateSessionManager(HibernateSessionManager hibernateSessionManager) {
        this.hibernateSessionManager = hibernateSessionManager;
    }

    @Override
    public void begin() {
        //HibernateSessionManager.beginTransaction();
        hibernateSessionManager.beginTransaction();

    }

    @Override
    public void commit() {
        //HibernateSessionManager.commitTransaction();
        hibernateSessionManager.commitTransaction();
    }

    @Override
    public void rollback() {
        //HibernateSessionManager.rollbackTransaction();
        hibernateSessionManager.rollbackTransaction();
    }
}
