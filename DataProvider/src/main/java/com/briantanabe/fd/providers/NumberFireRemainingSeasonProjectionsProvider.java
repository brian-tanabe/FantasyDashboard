package com.briantanabe.fd.providers;

import com.briantanabe.fd.fantasy.player.NumberFireRemainingSeasonProjection;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Brian on 9/28/14.
 */
public class NumberFireRemainingSeasonProjectionsProvider {
    private ArrayList<NumberFireRemainingSeasonProjection> playerProjections = new ArrayList<NumberFireRemainingSeasonProjection>();

    public void scrapeForNumberFiresRemainingSeasonProjections() throws IOException {

    }

    public ArrayList<NumberFireRemainingSeasonProjection> getPlayerProjections(){
        return playerProjections;
    }
}
