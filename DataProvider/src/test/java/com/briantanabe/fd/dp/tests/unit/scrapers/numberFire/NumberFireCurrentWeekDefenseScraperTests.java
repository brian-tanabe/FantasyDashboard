package com.briantanabe.fd.dp.tests.unit.scrapers.numberFire;

import com.briantanabe.fd.dp.fantasy.player.NumberFireCurrentWeekProjection;
import com.briantanabe.fd.dp.tests.fixtures.NumberFireCurrentWeekProjectionFixture;
import com.briantanabe.fd.dp.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.dp.scrapers.numberFire.positions.CurrentWeekNumberFireHtmlScraper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.briantanabe.fd.dp.tests.unit.scrapers.PlayerFinder.findPlayerByPlayerName;
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
    private static NumberFireCurrentWeekProjection player;

    @BeforeClass
    public static void setup(){
        currentWeekScraper = new NumberFirePageScraper(NumberFireCurrentWeekProjectionFixture.getCurrentWeekDefenseProjectionDocument(), new CurrentWeekNumberFireHtmlScraper());
        currentWeekScraper.scrape();
        player = findPlayerByPlayerName(PLAYER_NAME, (ArrayList<NumberFireCurrentWeekProjection>) currentWeekScraper.getPlayerRankings());
    }

    @Test
    public void shouldBeAbleToFindThirtyTwoTeamsInCurrentWeekProjections(){
        assertEquals(String.format("NumberFireScraper did not find %d teams", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, currentWeekScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainTheDenverBroncosInCurrentWeekProjections(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }

    @Test
    public void broncosShouldBeRankedTwentySeventhInCurrentWeekProjections(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }


    @Test
    public void broncosShouldHaveZeroFirePointsInCurrentWeekProjections(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        assertEquals(String.format("Failed to parse %s's FirePoints correctly", PLAYER_NAME), PLAYER_FIRE_POINTS, player.getFirePoints());
    }

    @Test
    public void playerShouldHaveTheProperNumberFireId(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        assertEquals(String.format("Failed to parse %s's numberFireId correctly", PLAYER_NAME), NUMBER_FIRE_ID, player.getNumberFireId());
    }
}
