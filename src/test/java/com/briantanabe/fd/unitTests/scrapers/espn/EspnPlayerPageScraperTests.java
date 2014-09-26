package com.briantanabe.fd.unitTests.scrapers.espn;

import com.briantanabe.fd.scrapers.espn.EspnPlayerPageScraper;
import org.junit.Test;

import static com.briantanabe.fd.fixtures.EspnPlayersPageFixture.getAllPlayersPageOneAsDocument;
import static org.junit.Assert.assertEquals;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class EspnPlayerPageScraperTests {

    @Test
    public void testCanFindFiftyPlayers(){
        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper(getAllPlayersPageOneAsDocument());
        scraper.scrape();

        assertEquals("Did not find 50 players on page one", 50, scraper.numberOfPlayers());
    }
}
