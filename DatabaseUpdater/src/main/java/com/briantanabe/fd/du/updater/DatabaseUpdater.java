package com.briantanabe.fd.du.updater;

import com.briantanabe.fd.dp.fantasy.player.NflPlayer;
import com.briantanabe.fd.dp.providers.EspnLeaguePlayerOwnershipProvider;
import com.briantanabe.fd.dp.providers.NumberFireCurrentWeekProjectionsProvider;
import com.briantanabe.fd.dp.providers.NumberFireRemainingSeasonProjectionsProvider;
import com.briantanabe.fd.dp.providers.PlayerIdProvider;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by btanabe on 10/9/2014.
 */
public class DatabaseUpdater extends DatabaseI {

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
