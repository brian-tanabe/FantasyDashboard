package com.briantanabe.fd.du.datastore;

import com.briantanabe.fd.dp.fantasy.player.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by btanabe on 10/9/2014.
 */
public class DatabaseAccessor extends DatabaseInterface {

    public List<NflPlayer> getAllNflPlayersFromThePlayerIdTable() {
        Session session = startSession(sessionFactory);
        ArrayList<NflPlayer> players = new ArrayList<NflPlayer>(session.createQuery("from com.briantanabe.fd.dp.fantasy.player.NflPlayerId").list());
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

    public List<NflPlayerPositionAndTeam> getAllPlayerPositionAndTeamObjectsFromTheDatabase() {
        Session session = startSession(sessionFactory);
        ArrayList<NflPlayerPositionAndTeam> players = new ArrayList<NflPlayerPositionAndTeam>(session.createQuery("from com.briantanabe.fd.dp.fantasy.player.NflPlayerPositionAndTeam").list());
        closeSession(session);
        return players;
    }
}
