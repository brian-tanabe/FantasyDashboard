package com.briantanabe.fd.unitTests.scrapers.espn;

import com.briantanabe.fd.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.scrapers.espn.EspnPlayerPageScraper;
import org.junit.Test;

import java.util.ArrayList;

import static com.briantanabe.fd.fixtures.EspnPlayersProjectionsPageFixture.getAllPlayersProjectionPageOneAsDocument;
import static com.briantanabe.fd.fixtures.EspnPlayersProjectionsPageFixture.getAllPlayersProjectionPageTwoAsDocument;
import static com.briantanabe.fd.unitTests.scrapers.PlayerFinder.findPlayerByPlayerName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class EspnPlayerPageScraperTests {
    private static final int TEST_ESPN_LEAGUE_ID = 84978;

    @Test
    public void testCanFindFortyPlayers(){
        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        ArrayList<EspnNflPlayer> players = scraper.scrape(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());

        assertEquals("Did not find 40 players on page one", 40, players.size());
    }

    @Test
    public void testCanFindTheFirstPlayerOnPageOne(){
        String playerToTest = "Adrian Peterson";

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        ArrayList<EspnNflPlayer> players = scraper.scrape(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());

        EspnNflPlayer player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
    }

    @Test
    public void testFirstPlayerHasProperEspnId(){
        String playerToTest = "Adrian Peterson";
        int espnId = 10452;

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        ArrayList<EspnNflPlayer> players = scraper.scrape(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());

        EspnNflPlayer player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's ESPN ID was not parsed correctly", playerToTest), espnId, player.getEspnPlayerId());
    }

    @Test
    public void testFirstPlayerHasProperOwnerId(){
        String playerToTest = "Adrian Peterson";
        int teamId = 7;

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        ArrayList<EspnNflPlayer> players = scraper.scrape(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());

        EspnNflPlayer player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's teamId was not parsed correctly", playerToTest), teamId, player.getOwner());
    }

    @Test
    public void testCanFindTheLastPlayerOnPageOne(){
        String playerToTest = "C.J. Spiller";

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        ArrayList<EspnNflPlayer> players = scraper.scrape(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());

        EspnNflPlayer player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
    }

    @Test
    public void testLastPlayerHasProperEspnId(){
        String playerToTest = "C.J. Spiller";
        int espnId = 13203;

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        ArrayList<EspnNflPlayer> players = scraper.scrape(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());

        EspnNflPlayer player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's ESPN ID was not parsed correctly", playerToTest), espnId, player.getEspnPlayerId());
    }

    @Test
    public void testLastPlayerHasProperOwnerId(){
        String playerToTest = "C.J. Spiller";
        int teamId = 10;

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        ArrayList<EspnNflPlayer> players = scraper.scrape(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());

        EspnNflPlayer player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's teamId was not parsed correctly", playerToTest), teamId, player.getOwner());
    }

    @Test
    public void freeAgentsShouldHaveAnOwnerIdOfNegativeOne(){
        String playerToTest = "Ray Rice";
        int teamId = -1;

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        ArrayList<EspnNflPlayer> players = scraper.scrape(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageTwoAsDocument());

        EspnNflPlayer player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's teamId was not parsed correctly", playerToTest), teamId, player.getOwner());
    }
}
