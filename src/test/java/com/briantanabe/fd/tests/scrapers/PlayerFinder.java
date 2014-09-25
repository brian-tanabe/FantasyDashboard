package com.briantanabe.fd.tests.scrapers;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;

import java.util.ArrayList;

/**
 * Created by BTanabe on 9/25/2014.
 */
public class PlayerFinder {

    public static NumberFireRanking findPlayerByPlayerName(String name, ArrayList<NumberFireRanking> players){
        NumberFireRanking playerToFind = null;
        for(NumberFireRanking player : players){
            if(player.getName().contains(name))
                playerToFind = player;
        }

        return playerToFind;
    }
}
