package com.briantanabe.fd.du.tests.fixtures;

import com.briantanabe.fd.dp.fantasy.player.EspnNflPlayer;

import java.util.ArrayList;

/**
 * Created by BTanabe on 10/1/2014.
 */
public class EspnNflPlayerFixture {
    public static ArrayList<EspnNflPlayer> getSampleEspnNflPlayers(int numberOfPlayers, int numberOfTeams){
        ArrayList<EspnNflPlayer> players = new ArrayList<>(numberOfPlayers);

        for(int index = 0; index < numberOfPlayers; index++){
            players.add(new EspnNflPlayer(1000 + index, 40000 + index, String.format("Player-%d", index), index % numberOfTeams));
        }

        return players;
    }
}
