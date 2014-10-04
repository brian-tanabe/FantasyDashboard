package com.briantanabe.fd.dp.providers;

import com.briantanabe.fd.dp.fantasy.player.NumberFireCurrentWeekProjection;
import com.briantanabe.fd.dp.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.dp.scrapers.numberFire.positions.CurrentWeekNumberFireHtmlScraper;
import com.briantanabe.fd.dp.web.WebRequest;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

import static com.briantanabe.fd.dp.web.WebPage.*;

/**
 * Created by Brian on 9/28/14.
 */
public class NumberFireCurrentWeekProjectionsProvider {
    private ArrayList<NumberFireCurrentWeekProjection> playerProjections = new ArrayList<NumberFireCurrentWeekProjection>();

    public void scrapeForNumberFiresCurrentWeekProjections(WebRequest webRequest) throws IOException {
        playerProjections.addAll(getAllPlayerIds(webRequest.getPageAsDocument(NUMBER_FIRE_CURRENT_WEEK_QUARTERBACK_PROJECTIONS_URL)));
        playerProjections.addAll(getAllPlayerIds(webRequest.getPageAsDocument(NUMBER_FIRE_CURRENT_WEEK_RUNNING_BACK_PROJECTIONS_URL)));
        playerProjections.addAll(getAllPlayerIds(webRequest.getPageAsDocument(NUMBER_FIRE_CURRENT_WEEK_WIDE_RECEIVER_PROJECTIONS_URL)));
        playerProjections.addAll(getAllPlayerIds(webRequest.getPageAsDocument(NUMBER_FIRE_CURRENT_WEEK_TIGHT_END_PROJECTIONS_URL)));
        playerProjections.addAll(getAllPlayerIds(webRequest.getPageAsDocument(NUMBER_FIRE_CURRENT_WEEK_KICKER_PROJECTIONS_URL)));
        playerProjections.addAll(getAllPlayerIds(webRequest.getPageAsDocument(NUMBER_FIRE_CURRENT_WEEK_DEFENSE_PROJECTIONS_URL)));
    }

    private ArrayList<NumberFireCurrentWeekProjection> getAllPlayerIds(Document document) throws IOException {
        NumberFirePageScraper scraper = new NumberFirePageScraper(document, new CurrentWeekNumberFireHtmlScraper());
        scraper.scrape();
        return new ArrayList<NumberFireCurrentWeekProjection>(scraper.getPlayerRankings());
    }

    public ArrayList<NumberFireCurrentWeekProjection> getPlayerProjections(){
        return playerProjections;
    }
}
