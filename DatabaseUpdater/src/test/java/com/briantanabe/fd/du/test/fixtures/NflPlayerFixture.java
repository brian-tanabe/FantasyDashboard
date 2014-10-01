package com.briantanabe.fd.du.test.fixtures;

import com.briantanabe.fd.fantasy.player.NflPlayer;

import java.util.ArrayList;

/**
 * Created by BTanabe on 10/1/2014.
 */
public class NflPlayerFixture {
    public static ArrayList<NflPlayer> getSampleNflPlayers(int numberOfPlayers){
        ArrayList<NflPlayer> players = new ArrayList<>(numberOfPlayers);
        for(int index = 0; index < numberOfPlayers; index++){
            players.add(new NflPlayer(1000 + index, 5000 + index, String.format("Player-%d", index)));
        }

        return players;
    }
}
