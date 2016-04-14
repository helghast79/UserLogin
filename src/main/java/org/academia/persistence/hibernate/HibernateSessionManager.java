package org.academia.persistence.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public final class HibernateSessionManager {


    private final String HIBERNATE_CONFIG = "/persistence/hibernate.cfg.xml";

    /**
     * Factory of Hibernate Sessions, which consist on single-threaded,
     * short-lived objects, conceptually modeling a "Unit of Work"
     */
    private  SessionFactory sessionFactory;
    
    private HibernateSessionManager() {

    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Initiates a new transaction
     *
     * @return the session associated with the transaction
     */
    public  Session beginTransaction() {

        Session session = getSession();
        session.beginTransaction();

        return session;

    }

    /**
     * Terminates the current transaction
     */
    public  void commitTransaction() {
        getSession().getTransaction().commit();
    }

    /**
     * Rollback the current transaction
     */
    public  void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }

    /**
     * Obtains the current session from the Hibernate session factory
     *
     * @return The current session
     */
    public Session getSession() {

        /*

         Due to automatic session context management,
         (current_session_context_class = thread)
         Hibernate will automatically open a new session if needed.

         Closing the session is not required, Hibernate will close
         the session when transaction is committed or rollback.

         */

        return sessionFactory.getCurrentSession();

    }

    /**
     * Closes the Hibernate Session factory,
     * necessary for application to quit
     */
    public  void close() {
        sessionFactory.close();
    }

}

