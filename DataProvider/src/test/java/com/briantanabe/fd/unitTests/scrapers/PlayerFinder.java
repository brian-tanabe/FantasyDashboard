package com.briantanabe.fd.unitTests.scrapers;

import com.briantanabe.fd.fantasy.player.NflPlayer;

import java.util.ArrayList;

/**
 * Created by BTanabe on 9/25/2014.
 */
public class PlayerFinder {

    public static <T extends NflPlayer> T findPlayerByPlayerName(String name, ArrayList<T> players){
        T playerToFind = null;
        for(T player : players){
            if(player.getName().contains(name))
                playerToFind = player;
        }

        return playerToFind;
    }
}
