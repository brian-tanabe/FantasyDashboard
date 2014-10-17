package com.briantanabe.fd.du.datastore;


import com.briantanabe.fd.dm.DatabaseInterface;
import com.briantanabe.fd.dp.fantasy.player.NflPlayer;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Brian on 10/16/2014.
 */
public class DatabaseCreator {
    private static DatabaseInterface databaseInterface = new DatabaseInterface();

    public static <T extends NflPlayer> void insertAllPlayers(List<T> rowsToAdd){
        Session session = databaseInterface.getSession();
        try {
            for(int index = 0; index < rowsToAdd.size(); index++){
                T databaseObject = rowsToAdd.get(index);
                session.save(databaseObject);

                if (index % databaseInterface.getBatchSize() == 0) {
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
