package com.briantanabe.fd.dp.providers;

import com.briantanabe.fd.dp.fantasy.player.NflPlayerId;
import com.briantanabe.fd.dp.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.dp.scrapers.numberFire.positions.RemainingSeasonNumberFireJsonScraper;
import com.briantanabe.fd.dp.web.WebRequest;

import java.io.IOException;
import java.util.ArrayList;

import static com.briantanabe.fd.dp.web.WebPage.*;

/**
 * Created by Brian on 9/28/14.
 */
public class PlayerIdProvider {
    private ArrayList<NflPlayerId> players = new ArrayList<>();

    public void scrapeForPlayerIds(WebRequest webRequest) throws IOException {
        players.addAll(getAllPlayerIds(webRequest, NUMBER_FIRE_REMAINING_SEASON_QUARTERBACK_PROJECTIONS_URL));
        players.addAll(getAllPlayerIds(webRequest, NUMBER_FIRE_REMAINING_SEASON_RUNNING_BACK_PROJECTIONS_URL));
        players.addAll(getAllPlayerIds(webRequest, NUMBER_FIRE_REMAINING_SEASON_WIDE_RECEIVER_PROJECTIONS_URL));
        players.addAll(getAllPlayerIds(webRequest, NUMBER_FIRE_REMAINING_SEASON_TIGHT_END_PROJECTIONS_URL));
        players.addAll(getAllPlayerIds(webRequest, NUMBER_FIRE_REMAINING_SEASON_KICKER_PROJECTIONS_URL));
        players.addAll(getAllPlayerIds(webRequest, NUMBER_FIRE_REMAINING_SEASON_DEFENSE_PROJECTIONS_URL));
    }

    private ArrayList<NflPlayerId> getAllPlayerIds(WebRequest webRequest, String numberFireUrl) throws IOException {
        NumberFirePageScraper scraper = new NumberFirePageScraper(webRequest.getPageAsDocument(numberFireUrl), new RemainingSeasonNumberFireJsonScraper());
        scraper.scrape();
        return new ArrayList<NflPlayerId>(scraper.getPlayerRankings());
    }

    public ArrayList<NflPlayerId> getAllPlayersAsArrayList(){
        return players;
    }
}
