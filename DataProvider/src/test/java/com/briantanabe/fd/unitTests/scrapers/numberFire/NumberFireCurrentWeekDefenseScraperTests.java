package com.briantanabe.fd.unitTests.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.CurrentWeekNumberFireHtmlScraper;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.briantanabe.fd.fixtures.FileDocumentor.getDocumentFromFileHtml;
import static com.briantanabe.fd.unitTests.scrapers.PlayerFinder.findPlayerByPlayerName;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Brian on 9/25/14.
 */
public class NumberFireCurrentWeekDefenseScraperTests {
    private static final String PLAYER_NAME = "Denver";
    private static final int NUMBER_OF_PLAYERS = 32;
    private static final int PLAYER_RANKING = 27;
    private static final double PLAYER_FIRE_POINTS = 0.0;
    private static final int NUMBER_FIRE_ID = 2901;

    private static NumberFirePageScraper currentWeekScraper;

    @BeforeClass
    public static void setup(){
        Document numberFireCurrentWeekProjectionsDocument = getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfCurrentWeekDefProjections.html");
        currentWeekScraper = new NumberFirePageScraper(numberFireCurrentWeekProjectionsDocument, new CurrentWeekNumberFireHtmlScraper());
        currentWeekScraper.scrape();
    }

    @Test
    public void shouldBeAbleToFindThirtyTwoTeamsInCurrentWeekProjections(){
        assertEquals(String.format("NumberFireScraper did not find %d teams", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, currentWeekScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainTheDenverBroncosInCurrentWeekProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, currentWeekScraper.getPlayerRankings());

        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }

    @Test
    public void broncosShouldBeRankedTwentySeventhInCurrentWeekProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, currentWeekScraper.getPlayerRankings());

        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }


    @Test
    public void broncosShouldHaveZeroFirePointsInCurrentWeekProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, currentWeekScraper.getPlayerRankings());

        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        assertEquals(String.format("Failed to parse %s's FirePoints correctly", PLAYER_NAME), PLAYER_FIRE_POINTS, player.getFirePoints());
    }

    @Test
    public void playerShouldHaveTheProperNumberFireId(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, currentWeekScraper.getPlayerRankings());

        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        assertEquals(String.format("Failed to parse %s's numberFireId correctly", PLAYER_NAME), NUMBER_FIRE_ID, player.getNumberFireId());
    }
}
