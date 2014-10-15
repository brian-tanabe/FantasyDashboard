package com.briantanabe.fd.dp.providers;

import com.briantanabe.fd.dp.fantasy.player.NflPlayerId;
import com.briantanabe.fd.dp.fantasy.player.NumberFireRemainingSeasonProjection;
import com.briantanabe.fd.dp.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.dp.scrapers.numberFire.positions.RemainingSeasonNumberFireJsonScraper;
import com.briantanabe.fd.dp.web.WebRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.briantanabe.fd.dp.web.WebPage.*;

/**
 * Created by Brian on 9/28/14.
 */
public class PlayerIdProvider {
    private ArrayList<NflPlayerId> players = new ArrayList<>();

    public void scrapeForPlayerIds(WebRequest webRequest) throws IOException {
        List<NumberFireRemainingSeasonProjection> numberFireRemainingSeasonProjectionsList = new ArrayList<>();
        numberFireRemainingSeasonProjectionsList.addAll(getAllPlayerIds(webRequest, NUMBER_FIRE_REMAINING_SEASON_QUARTERBACK_PROJECTIONS_URL));
        numberFireRemainingSeasonProjectionsList.addAll(getAllPlayerIds(webRequest, NUMBER_FIRE_REMAINING_SEASON_RUNNING_BACK_PROJECTIONS_URL));
        numberFireRemainingSeasonProjectionsList.addAll(getAllPlayerIds(webRequest, NUMBER_FIRE_REMAINING_SEASON_WIDE_RECEIVER_PROJECTIONS_URL));
        numberFireRemainingSeasonProjectionsList.addAll(getAllPlayerIds(webRequest, NUMBER_FIRE_REMAINING_SEASON_TIGHT_END_PROJECTIONS_URL));
        numberFireRemainingSeasonProjectionsList.addAll(getAllPlayerIds(webRequest, NUMBER_FIRE_REMAINING_SEASON_KICKER_PROJECTIONS_URL));
        numberFireRemainingSeasonProjectionsList.addAll(getAllPlayerIds(webRequest, NUMBER_FIRE_REMAINING_SEASON_DEFENSE_PROJECTIONS_URL));

        for(NumberFireRemainingSeasonProjection player : numberFireRemainingSeasonProjectionsList){
            players.add(new NflPlayerId(player.getEspnPlayerId(), player.getNumberFireId(), player.getName()));
        }
    }

    private ArrayList<NumberFireRemainingSeasonProjection> getAllPlayerIds(WebRequest webRequest, String numberFireUrl) throws IOException {
        NumberFirePageScraper scraper = new NumberFirePageScraper(webRequest.getPageAsDocument(numberFireUrl), new RemainingSeasonNumberFireJsonScraper());
        scraper.scrape();
        return new ArrayList<NumberFireRemainingSeasonProjection>(scraper.getPlayerRankings());
    }

    public List<NflPlayerId> getAllPlayersAsArrayList() {
        return players;
    }
}
