package com.briantanabe.fd.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scrapers.numberFire.positions.NumberFirePositionScraperI;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

/**
 * Created by Brian on 9/24/14.
 */
public class NumberFirePageScraper {
    private Document document;
    private NumberFirePositionScraperI scraper;
    private ArrayList<NumberFireRanking> players;

    public NumberFirePageScraper(Document playerProjectionsPageDocument, NumberFirePositionScraperI scraper){
        this.document = playerProjectionsPageDocument;
        this.scraper = scraper;
    }

    public void scrape(){
        players = scraper.getPlayerRankings(document);
    }

    public ArrayList<NumberFireRanking> getPlayerRankings(){
        return players;
    }
}
