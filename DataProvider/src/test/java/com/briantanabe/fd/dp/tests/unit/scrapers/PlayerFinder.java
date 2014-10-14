package com.briantanabe.fd.dp.tests.unit.scrapers;

import com.briantanabe.fd.dp.fantasy.player.NflPlayer;

import java.util.List;

/**
 * Created by BTanabe on 9/25/2014.
 */
public class PlayerFinder {

    public static <T extends NflPlayer> T findPlayerByPlayerName(String name, List<T> players){
        T playerToFind = null;
        for(T player : players){
            if(player.getName().contains(name))
                playerToFind = player;
        }

        return playerToFind;
    }
}
