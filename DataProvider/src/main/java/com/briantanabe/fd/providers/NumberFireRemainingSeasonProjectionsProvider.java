package com.briantanabe.fd.providers;

import com.briantanabe.fd.fantasy.player.NumberFireRemainingSeasonProjection;
import com.briantanabe.fd.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.RemainingSeasonNumberFireJsonScraper;
import com.briantanabe.fd.web.WebRequest;

import java.io.IOException;
import java.util.ArrayList;

import static com.briantanabe.fd.web.WebPage.*;

/**
 * Created by Brian on 9/28/14.
 */
public class NumberFireRemainingSeasonProjectionsProvider {
    private ArrayList<NumberFireRemainingSeasonProjection> playerProjections = new ArrayList<NumberFireRemainingSeasonProjection>();

    public void scrapeForNumberFiresRemainingSeasonProjections() throws IOException {
        playerProjections.addAll(getAllPlayerIds(NUMBER_FIRE_REMAINING_SEASON_QUARTERBACK_PROJECTIONS_URL));
        playerProjections.addAll(getAllPlayerIds(NUMBER_FIRE_REMAINING_SEASON_RUNNING_BACK_PROJECTIONS_URL));
        playerProjections.addAll(getAllPlayerIds(NUMBER_FIRE_REMAINING_SEASON_WIDE_RECEIVER_PROJECTIONS_URL));
        playerProjections.addAll(getAllPlayerIds(NUMBER_FIRE_REMAINING_SEASON_TIGHT_END_PROJECTIONS_URL));
        playerProjections.addAll(getAllPlayerIds(NUMBER_FIRE_REMAINING_SEASON_KICKER_PROJECTIONS_URL));
        playerProjections.addAll(getAllPlayerIds(NUMBER_FIRE_REMAINING_SEASON_DEFENSE_PROJECTIONS_URL));
    }

    private ArrayList<NumberFireRemainingSeasonProjection> getAllPlayerIds(String numberFireUrl) throws IOException {
        NumberFirePageScraper scraper = new NumberFirePageScraper(new WebRequest().getPageAsDocument(numberFireUrl), new RemainingSeasonNumberFireJsonScraper());
        scraper.scrape();
        return new ArrayList<NumberFireRemainingSeasonProjection>(scraper.getPlayerRankings());
    }

    public ArrayList<NumberFireRemainingSeasonProjection> getPlayerProjections(){
        return playerProjections;
    }
}
