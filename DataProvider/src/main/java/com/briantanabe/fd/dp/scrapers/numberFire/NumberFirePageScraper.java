package com.briantanabe.fd.dp.scrapers.numberFire;

import com.briantanabe.fd.dp.fantasy.player.NumberFireProjection;
import com.briantanabe.fd.dp.scrapers.numberFire.positions.NumberFirePositionScraperI;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

/**
 * Created by Brian on 9/24/14.
 */
public class NumberFirePageScraper <T extends NumberFireProjection> {
    private Document document;
    private NumberFirePositionScraperI scraper;
    private ArrayList<T> players;

    public NumberFirePageScraper(Document playerProjectionsPageDocument, NumberFirePositionScraperI scraper){
        this.document = playerProjectionsPageDocument;
        this.scraper = scraper;
    }

    public void scrape(){
        players = scraper.getPlayerRankings(document);
    }

    public ArrayList<T> getPlayerRankings(){
        return players;
    }
}
