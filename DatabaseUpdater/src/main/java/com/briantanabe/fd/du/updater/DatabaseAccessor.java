package com.briantanabe.fd.du.updater;

import com.briantanabe.fd.dp.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.dp.fantasy.player.NflPlayer;
import com.briantanabe.fd.dp.fantasy.player.NumberFireCurrentWeekProjection;
import com.briantanabe.fd.dp.fantasy.player.NumberFireRemainingSeasonProjection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by btanabe on 10/9/2014.
 */
public class DatabaseAccessor extends DatabaseI {

    public List<NflPlayer> getAllNflPlayersFromThePlayerIdTable() {
        Session session = startSession(sessionFactory);
        ArrayList<NflPlayer> players = new ArrayList<NflPlayer>(session.createQuery("from com.briantanabe.fd.dp.fantasy.player.NflPlayer").list());
        closeSession(session);

        return players;
    }

    public static NflPlayer getNflPlayerFromThePlayerIdTableByTheirEspnPlayerId(int playerId) {

        return null;
    }

    public List<EspnNflPlayer> getAllEspnPlayerOwnershipInfosFromTheEspnPlayerOwnershipTable() {
        Session session = startSession(sessionFactory);
        ArrayList<EspnNflPlayer> players = new ArrayList<EspnNflPlayer>(session.createQuery("from com.briantanabe.fd.dp.fantasy.player.EspnNflPlayer").list());
        closeSession(session);
        return players;
    }

    public List<NumberFireCurrentWeekProjection> getAllNumberFireCurrentWeekProjectionsFromTheDatabase() {
        Session session = startSession(sessionFactory);
        ArrayList<NumberFireCurrentWeekProjection> players = new ArrayList<NumberFireCurrentWeekProjection>(session.createQuery("from com.briantanabe.fd.dp.fantasy.player.NumberFireCurrentWeekProjection").list());
        closeSession(session);
        return players;
    }

    public List<NumberFireRemainingSeasonProjection> getAllNumberFireRemainingWeekProjectionsFromTheDatabase() {
        Session session = startSession(sessionFactory);
        ArrayList<NumberFireRemainingSeasonProjection> players = new ArrayList<NumberFireRemainingSeasonProjection>(session.createQuery("from com.briantanabe.fd.dp.fantasy.player.NumberFireRemainingSeasonProjection").list());
        closeSession(session);
        return players;
    }
}
