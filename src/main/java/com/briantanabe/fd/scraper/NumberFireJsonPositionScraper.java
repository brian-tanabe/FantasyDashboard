package com.briantanabe.fd.scraper;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;

import java.util.ArrayList;

/**
 * Created by Brian on 9/24/14.
 */
public abstract class NumberFireJsonPositionScraper {
    public abstract ArrayList<NumberFireRanking> getPlayerRankingsFromJsonString(String jsonString);
}
