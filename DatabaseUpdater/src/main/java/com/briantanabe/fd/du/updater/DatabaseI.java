package com.briantanabe.fd.du.updater;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;

/**
 * Created by Brian on 10/11/14.
 */
public class DatabaseI {
    protected static int BATCH_SIZE;
    protected static SessionFactory sessionFactory;

    protected DatabaseI(){
        sessionFactory = createSessionFactory();
    }

    protected static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure(new File("./DatabaseUpdater/src/main/resources/hibernate/hibernate.cfg.xml"));
        BATCH_SIZE = new Integer(configuration.getProperty("hibernate.jdbc.batch_size")).intValue();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    protected Session startSession(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    protected void closeSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }

    public void close(SessionFactory sessionFactory){
        sessionFactory.close();
    }
}
