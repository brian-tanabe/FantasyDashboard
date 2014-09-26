package com.briantanabe.fd.unitTests.scrapers.espn;

import com.briantanabe.fd.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.scrapers.espn.EspnPlayerPageScraper;
import org.junit.Test;

import static com.briantanabe.fd.fixtures.EspnPlayersProjectionsPageFixture.getAllPlayersProjectionPageOneAsDocument;
import static org.junit.Assert.assertEquals;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class EspnPlayerPageScraperTests {

    @Test
    public void testCanFindFortyPlayers(){
        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        EspnNflPlayer[] players = scraper.scrape(getAllPlayersProjectionPageOneAsDocument());

        assertEquals("Did not find 50 players on page one", 40, players.length);
    }
}
