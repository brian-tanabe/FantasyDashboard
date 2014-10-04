package com.briantanabe.fd.dp.tests.unit.scrapers.numberFire;

import com.briantanabe.fd.dp.fantasy.player.NumberFireRemainingSeasonProjection;
import com.briantanabe.fd.dp.tests.fixtures.NumberFireRemainingSeasonProjectionFixture;
import com.briantanabe.fd.dp.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.dp.scrapers.numberFire.positions.RemainingSeasonNumberFireJsonScraper;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.briantanabe.fd.dp.tests.unit.scrapers.PlayerFinder.findPlayerByPlayerName;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by BTanabe on 9/25/2014.
 */
public class NumberFireRemainingSeasonQuarterbackScraperTests {
    private static final String PLAYER_NAME = "Jay Cutler";
    private static final int NUMBER_OF_PLAYERS = 75;
    private static final int PLAYER_RANKING = 8;
    private static final double PLAYER_FIRE_POINTS = 226.04;
    private static final int ESPN_PLAYER_ID = 9597;
    private static final int NUMBER_FIRE_ID = 37;

    private static NumberFirePageScraper remainingSeasonScraper;
    private static NumberFireRemainingSeasonProjection player;

    @BeforeClass
    public static void setup(){
        remainingSeasonScraper = new NumberFirePageScraper(NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonQuarterbackProjectionDocument(), new RemainingSeasonNumberFireJsonScraper());
        remainingSeasonScraper.scrape();
        player = findPlayerByPlayerName(PLAYER_NAME, (ArrayList<NumberFireRemainingSeasonProjection>) remainingSeasonScraper.getPlayerRankings());
    }

    @Test
    public void shouldBeAbleToFindSeventyFivePlayersInRemainingSeasonProjections(){
        Assert.assertEquals(String.format("Did not find %d quarterbacks", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, remainingSeasonScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainTheDenverBroncosInRemainingSeasonProjections(){
        Assert.assertNotNull(String.format("Failed to find the %s", PLAYER_NAME), player);
    }

    @Test
    public void broncosShouldBeRankedEighthInRemainingSeasonProjections(){
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }

    @Test
    public void broncosShouldHaveNinetyThreeAndFiftyFourFirePointsInRemainingSeasonProjections(){
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
