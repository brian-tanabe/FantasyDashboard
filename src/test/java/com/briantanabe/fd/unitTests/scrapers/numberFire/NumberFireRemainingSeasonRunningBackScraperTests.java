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

/**
 * Created by BTanabe on 9/25/2014.
 */
public class NumberFireRemainingSeasonRunningBackScraperTests {
    private static final String PLAYER_NAME = "Jamaal Charles";
    private static final int NUMBER_OF_PLAYERS = 138;
    private static final int PLAYER_RANKING = 6;
    private static final double PLAYER_FIRE_POINTS = 178.15;
    private static final int ESPN_PLAYER_ID = 11307;

    private static NumberFireScraper remainingSeasonScraper;

    @BeforeClass
    public static void setup(){
        Document numberFireRemainingYearProjectionsDocument = getDocumentFromFileHtml("./src/test/resources/WebPages/nfRemainingSeasonRbProjections.html");
        remainingSeasonScraper = new NumberFireScraper(numberFireRemainingYearProjectionsDocument, new RemainingSeasonNumberFireJsonScraper());
        remainingSeasonScraper.scrape();
    }

    @Test
    public void shouldBeAbleToOneHundredThirtyEightPlayersInRemainingSeasonProjections(){
        Assert.assertEquals(String.format("Did not find %d running backs", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, remainingSeasonScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainTheDenverBroncosInRemainingSeasonProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, remainingSeasonScraper.getPlayerRankings());

        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }


    @Test
    public void broncosShouldBeRankedThirtyFourthInRemainingSeasonProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, remainingSeasonScraper.getPlayerRankings());

        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }

    @Test
    public void broncosShouldHaveOneHundredSeventyEightAndFifteenFirePointsInRemainingSeasonProjections(){
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
}
