package com.briantanabe.fd.dp.tests.unit.scrapers.numberFire;

import com.briantanabe.fd.dp.fantasy.player.NumberFireRemainingSeasonProjection;
import com.briantanabe.fd.dp.tests.fixtures.NumberFireRemainingSeasonProjectionFixture;
import com.briantanabe.fd.dp.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.dp.scrapers.numberFire.positions.RemainingSeasonNumberFireJsonScraper;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.briantanabe.fd.dp.tests.unit.scrapers.PlayerFinder.findPlayerByPlayerName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

    private static NumberFirePageScraper remainingSeasonScraper;
    private static NumberFireRemainingSeasonProjection player;

    @BeforeClass
    public static void setup(){
        remainingSeasonScraper = new NumberFirePageScraper(NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonKickerProjectionDocument(), new RemainingSeasonNumberFireJsonScraper());
        remainingSeasonScraper.scrape();
        player = findPlayerByPlayerName(PLAYER_NAME, (ArrayList<NumberFireRemainingSeasonProjection>) remainingSeasonScraper.getPlayerRankings());
    }

    @Test
    public void shouldBeAbleToFindThirtyTwoPlayersInRemainingSeasonProjections(){
        assertEquals(String.format("NumberFireScraper did not find %d players", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, remainingSeasonScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainRobbieGouldInRemainingSeasonProjections(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }

    @Test
    public void robbieGouldShouldBeRankedFourthInRemainingSeasonProjections(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }

    @Test
    public void robbieGouldShouldHaveOneHundredSixAndSixtyEightFirePointsInRemainingSeasonProjections(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's FirePoints correctly", PLAYER_NAME), PLAYER_FIRE_POINTS, player.getFirePoints());
    }

    @Test
    public void shouldBeAbleToParseEspnPlayerIdProperly(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's ESPN player ID correctly", PLAYER_NAME), ESPN_PLAYER_ID, player.getEspnPlayerId());
    }

    @Test
    public void playerShouldHaveTheProperNumberFireId(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's numberFireId correctly", PLAYER_NAME), NUMBER_FIRE_ID, player.getNumberFireId());
    }
}
