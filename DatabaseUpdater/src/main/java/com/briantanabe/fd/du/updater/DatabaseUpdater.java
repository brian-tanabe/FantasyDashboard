package com.briantanabe.fd.du.updater;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;

/**
 * Created by BTanabe on 9/30/2014.
 */
public class DatabaseUpdater {
    private SessionFactory sessionFactory;
    private static DatabaseUpdater updater;

    private DatabaseUpdater(){
        sessionFactory = createSessionFactory();
    }

    public static DatabaseUpdater getInstance(){
        if(updater == null)
            updater = new DatabaseUpdater();

        return updater;
    }

    public boolean isConnected(){
        return !sessionFactory.isClosed();
    }

    private static SessionFactory createSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure(new File("./DatabaseUpdater/src/main/resources/hibernate/hibernate.cfg.xml"));
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
}
