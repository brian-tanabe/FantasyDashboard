package com.briantanabe.fd.du.updater;

import com.briantanabe.fd.dp.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.dp.fantasy.player.NflPlayer;
import com.briantanabe.fd.dp.fantasy.player.NumberFireCurrentWeekProjection;
import com.briantanabe.fd.dp.fantasy.player.NumberFireRemainingSeasonProjection;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by btanabe on 10/9/2014.
 */
public class DatabaseAccessor {
    private static DatabaseInterface databaseInterface;

    public static List<NflPlayer> getAllNflPlayersFromThePlayerIdTable(){
        databaseInterface = DatabaseInterface.getInstance();

        Session session = databaseInterface.startSession();
        ArrayList<NflPlayer> players = new ArrayList<NflPlayer>(session.createQuery("from com.briantanabe.fd.dp.fantasy.player.NflPlayer").list());
        databaseInterface.closeSession(session);

        databaseInterface.close();

        return players;
    }

    public static NflPlayer getNflPlayerFromThePlayerIdTableByTheirEspnPlayerId(int playerId){

        return null;
    }

    public static List<EspnNflPlayer> getAllEspnPlayerOwnershipInfosFromTheEspnPlayerOwnershipTable(){

        return new ArrayList<EspnNflPlayer>();
    }

    public static List<NumberFireCurrentWeekProjection> getAllNumberFireCurrentWeekProjectionsFromTheDatabase(){

        return new ArrayList<NumberFireCurrentWeekProjection>();
    }

    public static List<NumberFireRemainingSeasonProjection> getAllNumberFireRemainingWeekProjectionsFromTheDatabase(){

        return new ArrayList<NumberFireRemainingSeasonProjection>();
    }
}
