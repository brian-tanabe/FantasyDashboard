package com.briantanabe.fd.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scrapers.numberFire.positions.CurrentWeekNumberFireHtmlScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.NumberFirePositionScraperI;
import com.briantanabe.fd.scrapers.numberFire.positions.RemainingSeasonNumberFireJsonScraper;
import com.briantanabe.fd.web.WebPage;
import com.briantanabe.fd.web.WebRequest;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class NumberFireScraper {
    private WebRequest webRequest;

    public NumberFireScraper(WebRequest webRequest){
        this.webRequest = webRequest;
    }

    public ArrayList<NumberFireRanking> scrape() throws IOException {
        ArrayList<NumberFireRanking> players = new ArrayList<NumberFireRanking>(2000);

        players.addAll(scrapeDefensePlayerProjections());

        return players;
    }

    private ArrayList<NumberFireRanking> scrapeDefensePlayerProjections() throws IOException {
        ArrayList<NumberFireRanking> currentWeekProjections = getPlayerProjections(webRequest.getPageAsDocument(WebPage.NUMBER_FIRE_CURRENT_WEEK_DEFENSE_PROJECTIONS_URL), new CurrentWeekNumberFireHtmlScraper());
        ArrayList<NumberFireRanking> remainingSeasonProjections = getPlayerProjections(webRequest.getPageAsDocument(WebPage.NUMBER_FIRE_REMAINING_SEASON_DEFENSE_PROJECTIONS_URL), new RemainingSeasonNumberFireJsonScraper());

        return mergeCurrentWeekProjectionsAndRemainingSeasonProjections(currentWeekProjections, remainingSeasonProjections);
    }

    private ArrayList<NumberFireRanking> getPlayerProjections(Document page, NumberFirePositionScraperI positionScraper){
        NumberFirePageScraper scraper = new NumberFirePageScraper(page, positionScraper);
        scraper.scrape();
        return scraper.getPlayerRankings();
    }

    private ArrayList<NumberFireRanking> mergeCurrentWeekProjectionsAndRemainingSeasonProjections(ArrayList<NumberFireRanking> currentWeekProjections, ArrayList<NumberFireRanking> remainingSeasonProjections){
        NumberFireRanking[] currentWeekProjectionsArray = currentWeekProjections.toArray(new NumberFireRanking[currentWeekProjections.size()]);

        // Favor remaining season projections because they contain the ESPN & Yahoo player IDs
        for(NumberFireRanking teamRemainingSeasonProjection : remainingSeasonProjections){
            int index = Arrays.binarySearch(currentWeekProjectionsArray, teamRemainingSeasonProjection.getNumberFireId(), new Comparator<Object>() {
                public int compare(Object lhs, Object rhs) {
                    return ((NumberFireRanking) lhs).getNumberFireId() - ((NumberFireRanking) rhs).getNumberFireId();
                }
            });

            NumberFireRanking currentWeekProjection = currentWeekProjectionsArray[index];
        }

        return null;
    }
}
