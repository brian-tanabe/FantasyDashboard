package com.briantanabe.fd.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scrapers.numberFire.positions.NumberFirePositionScraper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Brian on 9/24/14.
 */
public class NumberFireScraper {
    private Document document;
    private NumberFirePositionScraper scraper;
    ArrayList<NumberFireRanking> players;

    public NumberFireScraper(Document playerProjectionsPageDocument, NumberFirePositionScraper scraper){
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
