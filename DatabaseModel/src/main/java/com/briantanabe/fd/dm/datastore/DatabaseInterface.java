package com.briantanabe.fd.dm.datastore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;

/**
 * Created by Brian on 10/17/2014.
 */
public class DatabaseInterface {
    private static int BATCH_SIZE = 1;
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(new File("./DatabaseModel/src/main/resources/hibernate/hibernate.cfg.xml"));
            BATCH_SIZE = new Integer(configuration.getProperty("hibernate.jdbc.batch_size")).intValue();

            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session startSession() {
        Session session = ourSessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public static void closeSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }

    public void close(SessionFactory sessionFactory){
        sessionFactory.close();
    }
}
