package com.briantanabe.fd.unitTests.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireCurrentWeekProjection;
import com.briantanabe.fd.fixtures.NumberFireCurrentWeekProjectionFixture;
import com.briantanabe.fd.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.CurrentWeekNumberFireHtmlScraper;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.briantanabe.fd.unitTests.scrapers.PlayerFinder.findPlayerByPlayerName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by BTanabe on 9/25/2014.
 */
public class NumberFireCurrentWeekQuarterbackScraperTests {
    private static final String PLAYER_NAME = "Jay Cutler";
    private static final int NUMBER_OF_PLAYERS = 75;
    private static final int PLAYER_RANKING = 4;
    private static final double PLAYER_FIRE_POINTS = 19.02;
    private static final int NUMBER_FIRE_ID = 37;

    private static NumberFirePageScraper currentWeekScraper;
    private static NumberFireCurrentWeekProjection player;

    @BeforeClass
    public static void setup(){
        currentWeekScraper = new NumberFirePageScraper(NumberFireCurrentWeekProjectionFixture.getCurrentWeekQuarterbackProjectionDocument(), new CurrentWeekNumberFireHtmlScraper());
        currentWeekScraper.scrape();
        player = findPlayerByPlayerName(PLAYER_NAME, (ArrayList<NumberFireCurrentWeekProjection>) currentWeekScraper.getPlayerRankings());
    }

    @Test
    public void shouldBeAbleToFindSeventyFiveQuarterbacksInCurrentWeekProjections(){
        assertEquals(String.format("Did not find %d quarterbacks", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, currentWeekScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainJayCutlerInCurrentWeekProjections(){
        assertNotNull(String.format("Failed to find the %s", PLAYER_NAME), player);
    }

    @Test
    public void jayCutlerShouldBeRankedFourthInCurrentWeekProjections(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }


    @Test
    public void jayCutlerShouldHaveNineteenAndTwoFirePointsInCurrentWeekProjections(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's FirePoints correctly", PLAYER_NAME), PLAYER_FIRE_POINTS, player.getFirePoints());
    }

    @Test
    public void playerShouldHaveTheProperNumberFireId(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's numberFireId correctly", PLAYER_NAME), NUMBER_FIRE_ID, player.getNumberFireId());
    }
}
