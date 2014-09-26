package com.briantanabe.fd.unitTests.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scrapers.numberFire.NumberFireScraper;
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
public class NumberFireRemainingSeasonKickerScraperTests {
    private static final String PLAYER_NAME = "Robbie Gould";
    private static final int NUMBER_OF_PLAYERS = 32;
    private static final int PLAYER_RANKING = 4;
    private static final double PLAYER_FIRE_POINTS = 106.68;
    private static final int ESPN_PLAYER_ID = 9354;
    private static final int NUMBER_FIRE_ID = 2820;

    private static NumberFireScraper remainingSeasonScraper;

    @BeforeClass
    public static void setup(){
        Document numberFireProjectionsDocument = getDocumentFromFileHtml("./src/test/resources/WebPages/nfRemainingSeasonKProjections.html");
        remainingSeasonScraper = new NumberFireScraper(numberFireProjectionsDocument, new RemainingSeasonNumberFireJsonScraper());
        remainingSeasonScraper.scrape();
    }

    @Test
    public void shouldBeAbleToFindThirtyTwoPlayersInRemainingSeasonProjections(){
        assertEquals(String.format("NumberFireScraper did not find %d players", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, remainingSeasonScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainRobbieGouldInRemainingSeasonProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, remainingSeasonScraper.getPlayerRankings());

        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }

    @Test
    public void robbieGouldShouldBeRankedFourthInRemainingSeasonProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, remainingSeasonScraper.getPlayerRankings());

        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }

    @Test
    public void robbieGouldShouldHaveOneHundredSixAndSixtyEightFirePointsInRemainingSeasonProjections(){
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
