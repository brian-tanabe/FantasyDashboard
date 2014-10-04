package com.briantanabe.fd.du.updater;

import com.briantanabe.fd.dp.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.dp.fantasy.player.NflPlayer;
import com.briantanabe.fd.dp.fantasy.player.NumberFireCurrentWeekProjection;
import com.briantanabe.fd.dp.fantasy.player.NumberFireRemainingSeasonProjection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by BTanabe on 9/30/2014.
 */
public class DatabaseInterface {
    private static SessionFactory sessionFactory;
    private static DatabaseInterface updater;

    private DatabaseInterface(){
        sessionFactory = createSessionFactory();
    }

    public static DatabaseInterface getInstance(){
        if(updater == null)
            updater = new DatabaseInterface();

        return updater;
    }

    public boolean isConnected(){
        return !sessionFactory.isClosed();
    }

    public <T> void insert(ArrayList<T> objectsToAdd){
        Session session = startSession();
        for(T newObject : objectsToAdd){
            session.save(newObject);
        }
        closeSession(session);
    }

    public ArrayList<NflPlayer> getAllNflPlayersFromDatabase(){
        Session session = startSession();
        ArrayList<NflPlayer> players = new ArrayList<NflPlayer>(session.createQuery("from com.briantanabe.fd.dp.fantasy.player.NflPlayer").list());
        closeSession(session);
        return players;
    }

    public ArrayList<EspnNflPlayer> getAllEspnNflPlayersFromDatabase(){
        Session session = startSession();
        ArrayList<EspnNflPlayer> players = new ArrayList<EspnNflPlayer>(session.createQuery("from com.briantanabe.fd.dp.fantasy.player.EspnNflPlayer").list());
        closeSession(session);
        return players;
    }

    public ArrayList<NumberFireCurrentWeekProjection> getAllNumberFireCurrentWeekProjectionsFromDatabase(){
        Session session = startSession();
        ArrayList<NumberFireCurrentWeekProjection> players = new ArrayList<NumberFireCurrentWeekProjection>(session.createQuery("from com.briantanabe.fd.dp.fantasy.player.NumberFireCurrentWeekProjection").list());
        closeSession(session);
        return players;
    }

    public ArrayList<NumberFireRemainingSeasonProjection> getAllNumberFireRemainingSeasonProjectionsFromDatabase(){
        Session session = startSession();
        ArrayList<NumberFireRemainingSeasonProjection> players = new ArrayList<NumberFireRemainingSeasonProjection>(session.createQuery("from com.briantanabe.fd.dp.fantasy.player.NumberFireRemainingSeasonProjection").list());
        closeSession(session);
        return players;
    }

    private static SessionFactory createSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure(new File("./DatabaseUpdater/src/main/resources/hibernate/hibernate.cfg.xml"));
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static void close(){
        sessionFactory.close();
        sessionFactory = null;
        updater = null;
    }

    private static Session startSession(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    private static void closeSession(Session session){
        session.getTransaction().commit();
        session.close();
    }
}
