package com.briantanabe.fd.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireProjection;
import com.briantanabe.fd.scrapers.numberFire.positions.NumberFirePositionScraperI;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

/**
 * Created by Brian on 9/24/14.
 */
public class NumberFirePageScraper {
    private Document document;
    private NumberFirePositionScraperI scraper;
    private ArrayList<NumberFireProjection> players;

    public NumberFirePageScraper(Document playerProjectionsPageDocument, NumberFirePositionScraperI scraper){
        this.document = playerProjectionsPageDocument;
        this.scraper = scraper;
    }

    public void scrape(){
        players = scraper.getPlayerRankings(document);
    }

    public ArrayList<NumberFireProjection> getPlayerRankings(){
        return players;
    }
}
