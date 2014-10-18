package com.briantanabe.fd.dp.scrapers.numberFire;

import com.briantanabe.fd.dp.fantasy.player.NumberFireProjection;
import com.briantanabe.fd.dp.scrapers.numberFire.positions.NumberFirePositionScraperI;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by Brian on 9/24/14.
 */
public class NumberFirePageScraper <T extends NumberFireProjection> {
    private Document document;
    private NumberFirePositionScraperI scraper;
    private List<T> players;

    public NumberFirePageScraper(Document playerProjectionsPageDocument, NumberFirePositionScraperI scraper){
        this.document = playerProjectionsPageDocument;
        this.scraper = scraper;
    }

    public void scrape(){
        players = scraper.getPlayerRankings(document);
    }

    public List<T> getPlayerRankings(){
        return players;
    }
}
