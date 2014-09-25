package com.briantanabe.fd.tests.scrapers;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scrapers.numberFire.NumberFireScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.CurrentWeekNumberFireHtmlScraper;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static com.briantanabe.fd.tests.scrapers.PlayerFinder.findPlayerByPlayerName;
import static org.junit.Assert.*;

/**
 * Created by BTanabe on 9/25/2014.
 */
public class NumberFireCurrentWeekQuarterbackScraperTests {
    private static final String PLAYER_NAME = "Jay Cutler";
    private static final int NUMBER_OF_PLAYERS = 75;
    private static final int PLAYER_RANKING = 4;
    private static final double PLAYER_FIRE_POINTS = 19.02;

    private static NumberFireScraper currentWeekScraper;

    @BeforeClass
    public static void setup(){
        try {
            Document numberFireCurrentWeekProjectionsDocument = Jsoup.parse(FileUtils.readFileToString(new File("./src/test/resources/WebPages/nfCurrentWeekQbProjections.html")));
            currentWeekScraper = new NumberFireScraper(numberFireCurrentWeekProjectionsDocument, new CurrentWeekNumberFireHtmlScraper());
            currentWeekScraper.scrape();
        } catch(Exception ex){
            fail("FAILED to open nfCurrentSeasonQbProjections");
        }
    }

    @Test
    public void shouldBeAbleToFindSeventyFiveQuarterbacksInCurrentWeekProjections(){
        assertEquals(String.format("Did not find %d quarterbacks", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, currentWeekScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainJayCutlerInCurrentWeekProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, currentWeekScraper.getPlayerRankings());

        assertNotNull(String.format("Failed to find the %s", PLAYER_NAME), player);
    }

    @Test
    public void jayCutlerShouldBeRankedFourthInCurrentWeekProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, currentWeekScraper.getPlayerRankings());

        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }


    @Test
    public void jayCutlerShouldHaveNineteenAndTwoFirePointsInCurrentWeekProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, currentWeekScraper.getPlayerRankings());

        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's FirePoints correctly", PLAYER_NAME), PLAYER_FIRE_POINTS, player.getFirePoints());
    }
}