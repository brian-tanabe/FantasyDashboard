package com.briantanabe.fd.unitTests.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireRemainingSeasonProjection;
import com.briantanabe.fd.fixtures.NumberFireRemainingSeasonProjectionFixture;
import com.briantanabe.fd.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.RemainingSeasonNumberFireJsonScraper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.briantanabe.fd.unitTests.scrapers.PlayerFinder.findPlayerByPlayerName;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Brian on 9/24/14.
 */
public class NumberFireRemainingSeasonDefenseScraperTests {
    private static final String PLAYER_NAME = "Denver";
    private static final int NUMBER_OF_PLAYERS = 32;
    private static final int PLAYER_RANKING = 24;
    private static final double PLAYER_FIRE_POINTS = 93.54;
    private static final int ESPN_PLAYER_ID = 60007;
    private static final int NUMBER_FIRE_ID = 2901;

    private static NumberFirePageScraper remainingSeasonScraper;
    private static NumberFireRemainingSeasonProjection player;

    @BeforeClass
    public static void setup(){
        remainingSeasonScraper = new NumberFirePageScraper(NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonDefenseProjectionDocument(), new RemainingSeasonNumberFireJsonScraper());
        remainingSeasonScraper.scrape();
        player = findPlayerByPlayerName(PLAYER_NAME, (ArrayList<NumberFireRemainingSeasonProjection>) remainingSeasonScraper.getPlayerRankings());
    }

    @Test
    public void shouldBeAbleToFindThirtyTwoTeamsInRemainingSeasonProjections(){
        assertEquals(String.format("NumberFireScraper did not find %d teams", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, remainingSeasonScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainTheDenverBroncosInRemainingSeasonProjections(){
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }

    @Test
    public void broncosShouldBeRankedTwentyFourthInRemainingSeasonProjections(){
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }

    @Test
    public void broncosShouldHaveNinetyThreeAndFiftyFourFirePointsInRemainingSeasonProjections(){
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        assertEquals(String.format("Failed to parse %s's FirePoints correctly", PLAYER_NAME), PLAYER_FIRE_POINTS, player.getFirePoints());
    }

    @Test
    public void shouldBeAbleToParseEspnPlayerIdProperly(){
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        assertEquals(String.format("Failed to parse %s's ESPN player ID correctly", PLAYER_NAME), ESPN_PLAYER_ID, player.getEspnPlayerId());
    }

    @Test
    public void playerShouldHaveTheProperNumberFireId(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        assertEquals(String.format("Failed to parse %s's numberFireId correctly", PLAYER_NAME), NUMBER_FIRE_ID, player.getNumberFireId());
    }
}
