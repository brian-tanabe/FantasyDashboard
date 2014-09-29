package com.briantanabe.fd.providers;

import com.briantanabe.fd.fantasy.player.NumberFireCurrentWeekProjection;
import com.briantanabe.fd.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.CurrentWeekNumberFireHtmlScraper;
import com.briantanabe.fd.web.WebRequest;

import java.io.IOException;
import java.util.ArrayList;

import static com.briantanabe.fd.web.WebPage.*;

/**
 * Created by Brian on 9/28/14.
 */
public class NumberFireCurrentWeekProjectionsProvider {
    private ArrayList<NumberFireCurrentWeekProjection> playerProjections = new ArrayList<NumberFireCurrentWeekProjection>();

    public void scrapeForNumberFiresCurrentWeekProjections() throws IOException {
        playerProjections.addAll(getAllPlayerIds(NUMBER_FIRE_CURRENT_WEEK_QUARTERBACK_PROJECTIONS_URL));
        playerProjections.addAll(getAllPlayerIds(NUMBER_FIRE_CURRENT_WEEK_RUNNING_BACK_PROJECTIONS_URL));
        playerProjections.addAll(getAllPlayerIds(NUMBER_FIRE_CURRENT_WEEK_WIDE_RECEIVER_PROJECTIONS_URL));
        playerProjections.addAll(getAllPlayerIds(NUMBER_FIRE_CURRENT_WEEK_TIGHT_END_PROJECTIONS_URL));
        playerProjections.addAll(getAllPlayerIds(NUMBER_FIRE_CURRENT_WEEK_KICKER_PROJECTIONS_URL));
        playerProjections.addAll(getAllPlayerIds(NUMBER_FIRE_CURRENT_WEEK_DEFENSE_PROJECTIONS_URL));
    }

    private ArrayList<NumberFireCurrentWeekProjection> getAllPlayerIds(String numberFireUrl) throws IOException {
        NumberFirePageScraper scraper = new NumberFirePageScraper(new WebRequest().getPageAsDocument(numberFireUrl), new CurrentWeekNumberFireHtmlScraper());
        scraper.scrape();
        return new ArrayList<NumberFireCurrentWeekProjection>(scraper.getPlayerRankings());
    }

    public ArrayList<NumberFireCurrentWeekProjection> getPlayerProjections(){
        return playerProjections;
    }
}
