package com.briantanabe.fd.du.datastore;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by btanabe on 10/9/2014.
 */
public class DatabaseUpdater extends DatabaseInterface {

    public <T> void insert(List<T> objectsToAdd){
        Session session = startSession(sessionFactory);
        try {
            for(int index = 0; index < objectsToAdd.size(); index++){
                T databaseObject = objectsToAdd.get(index);
                session.save(databaseObject);

                if (index % BATCH_SIZE == 0) {
                    session.flush();
                    session.clear();
                }
            }
        } catch (HibernateException ex){
            ex.printStackTrace();
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
