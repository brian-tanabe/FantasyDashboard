package com.briantanabe.fd.unitTests.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireRemainingSeasonProjection;
import com.briantanabe.fd.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.RemainingSeasonNumberFireJsonScraper;
import junit.framework.TestCase;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.briantanabe.fd.fixtures.FileDocumentor.getDocumentFromFileHtml;
import static com.briantanabe.fd.unitTests.scrapers.PlayerFinder.findPlayerByPlayerName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by BTanabe on 9/25/2014.
 */
public class NumberFireRemainingSeasonWideReceiverScraperTests {
    private static final String PLAYER_NAME = "Emmanuel Sanders";
    private static final int NUMBER_OF_PLAYERS = 178;
    private static final int PLAYER_RANKING = 15;
    private static final double PLAYER_FIRE_POINTS = 116.45;
    private static final int ESPN_PLAYER_ID = 13295;
    private static final int NUMBER_FIRE_ID = 2205;

    private static NumberFirePageScraper remainingSeasonScraper;
    private static NumberFireRemainingSeasonProjection player;

    @BeforeClass
    public static void setup(){
        Document numberFireRemainingYearProjectionsDocument = getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfRemainingSeasonWrProjections.html");
        remainingSeasonScraper = new NumberFirePageScraper(numberFireRemainingYearProjectionsDocument, new RemainingSeasonNumberFireJsonScraper());
        remainingSeasonScraper.scrape();
        player = findPlayerByPlayerName(PLAYER_NAME, (ArrayList<NumberFireRemainingSeasonProjection>) remainingSeasonScraper.getPlayerRankings());
    }

    @Test
    public void shouldBeAbleToFindOneHundredSeventyEightPlayersInRemainingSeasonProjections(){
        assertEquals(String.format("Did not find %d wide receivers", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, remainingSeasonScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainEmmanuelSandersInRemainingSeasonProjections(){
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }


    @Test
    public void emmanuelSandersShouldBeRankedFifteenthInRemainingSeasonProjections(){
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }

    @Test
    public void emmanuelSandersShouldHaveOneHundredSixteenAndFourtyFiveFirePointsInRemainingSeasonProjections(){
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's FirePoints correctly", PLAYER_NAME), PLAYER_FIRE_POINTS, player.getFirePoints());
    }

    @Test
    public void shouldBeAbleToParseEspnPlayerIdProperly(){
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's ESPN player ID correctly", PLAYER_NAME), ESPN_PLAYER_ID, player.getEspnPlayerId());
    }

    @Test
    public void playerShouldHaveTheProperNumberFireId(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        assertEquals(String.format("Failed to parse %s's numberFireId correctly", PLAYER_NAME), NUMBER_FIRE_ID, player.getNumberFireId());
    }
}
