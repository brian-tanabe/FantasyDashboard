package com.briantanabe.fd.du.tests.fixtures;

import com.briantanabe.fd.dp.fantasy.player.NumberFireCurrentWeekProjection;

import java.util.ArrayList;

/**
 * Created by BTanabe on 10/1/2014.
 */
public class NumberFireCurrentWeekProjectionFixture {

    public static ArrayList<NumberFireCurrentWeekProjection> getSampleNumberFireCurrentWeekProjections(int numberOfPlayer){
        ArrayList<NumberFireCurrentWeekProjection> players = new ArrayList<>(numberOfPlayer);

        for(int index = 0; index < numberOfPlayer; index++){
            players.add(new NumberFireCurrentWeekProjection(1000 + index, String.format("Player-%d", index), index, 0.75 * (index + 1)));
        }

        return players;
    }
}
