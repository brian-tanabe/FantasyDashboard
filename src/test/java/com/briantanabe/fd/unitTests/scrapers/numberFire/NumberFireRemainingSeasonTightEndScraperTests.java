package com.briantanabe.fd.unitTests.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.RemainingSeasonNumberFireJsonScraper;
import junit.framework.TestCase;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.briantanabe.fd.fixtures.FileDocumentor.getDocumentFromFileHtml;
import static com.briantanabe.fd.unitTests.scrapers.PlayerFinder.findPlayerByPlayerName;
import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by BTanabe on 9/25/2014.
 */
public class NumberFireRemainingSeasonTightEndScraperTests {
    private static final String PLAYER_NAME = "Julius Thomas";
    private static final int NUMBER_OF_PLAYERS = 94;
    private static final int PLAYER_RANKING = 3;
    private static final double PLAYER_FIRE_POINTS = 117.43;
    private static final int ESPN_PLAYER_ID = 14204;
    private static final int NUMBER_FIRE_ID = 4353;

    private static NumberFirePageScraper remainingSeasonScraper;

    @BeforeClass
    public static void setup(){
        Document numberFireRemainingYearProjectionsDocument = getDocumentFromFileHtml("./src/test/resources/WebPages/nfRemainingSeasonTeProjections.html");
        remainingSeasonScraper = new NumberFirePageScraper(numberFireRemainingYearProjectionsDocument, new RemainingSeasonNumberFireJsonScraper());
        remainingSeasonScraper.scrape();
    }

    @Test
    public void shouldBeAbleToFindNinetyFourPlayersInRemainingSeasonProjections(){
        assertEquals(String.format("NumberFireScraper did not find %d players", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, remainingSeasonScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainJuliusThomasInRemainingSeasonProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, remainingSeasonScraper.getPlayerRankings());

        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }

    @Test
    public void juliusThomasShouldBeRankedThirdInRemainingSeasonProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, remainingSeasonScraper.getPlayerRankings());

        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }

    @Test
    public void juliusThomasShouldHaveOneHundredSeventeenAndFourtyThreeFirePointsInRemainingSeasonProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, remainingSeasonScraper.getPlayerRankings());

        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's FirePoints correctly", PLAYER_NAME), PLAYER_FIRE_POINTS, player.getFirePoints());
    }

    @Test
    public void shouldBeAbleToParseEspnPlayerIdProperly(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, remainingSeasonScraper.getPlayerRankings());

        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's ESPN player ID correctly", PLAYER_NAME), ESPN_PLAYER_ID, player.getEspnPlayerId());
    }

    @Test
    public void playerShouldHaveTheProperNumberFireId(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, remainingSeasonScraper.getPlayerRankings());

        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's numberFireId correctly", PLAYER_NAME), NUMBER_FIRE_ID, player.getNumberFireId());
    }
}
