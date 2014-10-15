package com.briantanabe.fd.dp.tests.unit.scrapers;

import com.briantanabe.fd.dp.fantasy.player.NflPlayer;
import com.briantanabe.fd.dp.fantasy.player.NflPlayerPositionAndTeam;
import com.briantanabe.fd.dp.nfl.position.Position;
import com.briantanabe.fd.dp.nfl.team.NflTeam;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by BTanabe on 9/25/2014.
 */
public class PlayerFinder {

    public static <T extends NflPlayer> List<T> findAllPlayersByPlayerName(String name, List<T> players){
        List<T> playersWithMatchingName = new ArrayList<T>();

        for(T player : players){
            if(player.getName().contains(name))
                playersWithMatchingName.add(player);
        }

        return playersWithMatchingName;
    }

    public static <T extends NflPlayer> T findPlayerByPlayerName(String name, List<T> players){
        T playerToFind = null;
        for(T player : players){
            if(player.getName().contains(name))
                playerToFind = player;
        }

        return playerToFind;
    }

    public static List<NflPlayerPositionAndTeam> findAllPlayersByPosition(Position position, List<NflPlayerPositionAndTeam> players){
        ArrayList<NflPlayerPositionAndTeam> playerAtPosition = new ArrayList<>();
        for(NflPlayerPositionAndTeam player : players) {
            if(player.getAllEligiblePositions().contains(position))
                playerAtPosition.add(player);
        }

        return playerAtPosition;
    }

    public static List<NflTeam> findAllUniqueNflTeamsInListOfPlayers(List<NflPlayerPositionAndTeam> players){
        LinkedHashSet<NflTeam> allTeams = new LinkedHashSet<>(32);
        for(NflPlayerPositionAndTeam player : players){
            if(player.getNflTeam() != NflTeam.UNKNOWN)
                allTeams.add(player.getNflTeam());
        }

        return new ArrayList<>(allTeams);
    }

    public static List<NflPlayerPositionAndTeam> findAllPlayersByNflTeam(NflTeam team, List<NflPlayerPositionAndTeam> players){
        ArrayList<NflPlayerPositionAndTeam> playerAtPosition = new ArrayList<>();
        for(NflPlayerPositionAndTeam player : players) {
            if(player.getNflTeam() == team)
                playerAtPosition.add(player);
        }

        return playerAtPosition;
    }
}
