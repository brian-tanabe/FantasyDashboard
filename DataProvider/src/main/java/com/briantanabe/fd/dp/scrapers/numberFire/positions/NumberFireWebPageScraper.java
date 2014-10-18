package com.briantanabe.fd.dp.scrapers.numberFire.positions;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by Brian on 10/17/2014.
 */
public class NumberFireWebPageScraper <T> {
    private Document document;
    private NumberFirePositionScraperI scraper;
    private List<T> players;

    public NumberFireWebPageScraper(Document document, NumberFirePositionScraperI scraper) {
        this.document = document;
        this.scraper = scraper;
    }

    public void scrape(){
        scraper.scrape(document);
        players = scraper.getPlayerProjections();
    }

    public List<T> getPlayers(){
        return players;
    }
}
