package com.briantanabe.fd.unitTests.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireRemainingSeasonProjection;
import com.briantanabe.fd.fixtures.NumberFireRemainingSeasonProjectionFixture;
import com.briantanabe.fd.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.RemainingSeasonNumberFireJsonScraper;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

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
    private static NumberFireRemainingSeasonProjection player;

    @BeforeClass
    public static void setup(){
        remainingSeasonScraper = new NumberFirePageScraper(NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonTightEndProjectionDocument(), new RemainingSeasonNumberFireJsonScraper());
        remainingSeasonScraper.scrape();
        player = findPlayerByPlayerName(PLAYER_NAME, (ArrayList<NumberFireRemainingSeasonProjection>) remainingSeasonScraper.getPlayerRankings());
    }

    @Test
    public void shouldBeAbleToFindNinetyFourPlayersInRemainingSeasonProjections(){
        assertEquals(String.format("NumberFireScraper did not find %d players", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, remainingSeasonScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainJuliusThomasInRemainingSeasonProjections(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }

    @Test
    public void juliusThomasShouldBeRankedThirdInRemainingSeasonProjections(){
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }

    @Test
    public void juliusThomasShouldHaveOneHundredSeventeenAndFourtyThreeFirePointsInRemainingSeasonProjections(){
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
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's numberFireId correctly", PLAYER_NAME), NUMBER_FIRE_ID, player.getNumberFireId());
    }
}
