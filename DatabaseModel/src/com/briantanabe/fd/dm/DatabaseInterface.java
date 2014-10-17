package com.briantanabe.fd.dm;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Brian on 10/16/2014.
 */
public class DatabaseInterface {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;
    protected static int BATCH_SIZE;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);

            BATCH_SIZE = new Integer(configuration.getProperty("hibernate.jdbc.batch_size")).intValue();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void close(SessionFactory sessionFactory){
        sessionFactory.close();
    }

    public static int getBatchSize(){
        return BATCH_SIZE;
    }
}
