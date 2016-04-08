package org.academia.persistence.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public final class HibernateSessionManager {


    private final static String HIBERNATE_CONFIG = "/persistence/hibernate.cfg.xml";

    /**
     * Factory of Hibernate Sessions, which consist on single-threaded,
     * short-lived objects, conceptually modeling a "Unit of Work"
     */
    private static SessionFactory sessionFactory;

    static{

        try {

            // Hold services needed by Hibernate
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure(HIBERNATE_CONFIG) // Load settings from hibernate.cfg.xml
                    .build();

            sessionFactory = new MetadataSources(serviceRegistry)
                    .buildMetadata() // Tell Hibernate about sources of metadata (mappings)
                    .buildSessionFactory();

        } catch (HibernateException ex) {

            ex.printStackTrace();
            throw new ExceptionInInitializerError("Error creating hibernate session factory: " + ex.getMessage());
        }
    }

    private HibernateSessionManager() {

    }

    /**
     * Initiates a new transaction
     *
     * @return the session associated with the transaction
     */
    public static Session beginTransaction() {

        Session session = getSession();
        session.beginTransaction();

        return session;

    }

    /**
     * Terminates the current transaction
     */
    public static void commitTransaction() {
        getSession().getTransaction().commit();
    }

    /**
     * Rollback the current transaction
     */
    public static void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }

    /**
     * Obtains the current session from the Hibernate session factory
     *
     * @return The current session
     */
    private static Session getSession() {

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
    public static void close() {
        sessionFactory.close();
    }

}

