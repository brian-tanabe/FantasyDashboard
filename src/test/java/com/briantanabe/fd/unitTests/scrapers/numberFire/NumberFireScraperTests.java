package com.briantanabe.fd.unitTests.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scrapers.numberFire.NumberFireScraper;
import com.briantanabe.fd.web.WebRequest;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class NumberFireScraperTests {

    @Test
    public void shouldBeAbleToFindTwoThousandPlayers(){
        NumberFireScraper scraper = new NumberFireScraper(new WebRequest());
        ArrayList<NumberFireRanking> players = scraper.scrape();

        assertEquals("Did not find the correct number of players", 2000, players.size());
    }
}
