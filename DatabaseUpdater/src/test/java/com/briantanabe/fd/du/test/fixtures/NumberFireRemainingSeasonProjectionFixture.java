package com.briantanabe.fd.du.test.fixtures;

import com.briantanabe.fd.dp.fantasy.player.NumberFireRemainingSeasonProjection;

import java.util.ArrayList;

/**
 * Created by BTanabe on 10/1/2014.
 */
public class NumberFireRemainingSeasonProjectionFixture {

    public static ArrayList<NumberFireRemainingSeasonProjection> getSampleNumberFireRemainingSeasonProjections(int numberOfPlayer){
        ArrayList<NumberFireRemainingSeasonProjection> players = new ArrayList<>(numberOfPlayer);

        for(int index = 0; index < numberOfPlayer; index++){
            players.add(new NumberFireRemainingSeasonProjection(1000 + index, 5000 + index, String.format("Player-%d", index), index, 0.75 * (index + 1)));
        }

        return players;
    }
}
