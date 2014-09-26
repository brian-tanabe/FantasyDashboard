package com.briantanabe.fd.tests.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scrapers.numberFire.NumberFireScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.CurrentWeekNumberFireHtmlScraper;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static com.briantanabe.fd.tests.scrapers.PlayerFinder.findPlayerByPlayerName;
import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;

/**
 * Created by BTanabe on 9/25/2014.
 */
public class NumberFireCurrentWeekTightEndScraperTests {
    private static final String PLAYER_NAME = "Delanie Walker";
    private static final int NUMBER_OF_PLAYERS = 94;
    private static final int PLAYER_RANKING = 98;
    private static final double PLAYER_FIRE_POINTS = 6.45;

    private static NumberFireScraper currentWeekScraper;

    @BeforeClass
    public static void setup(){
        try {
            Document numberFireProjectionsDocument = Jsoup.parse(FileUtils.readFileToString(new File("./src/test/resources/WebPages/nfCurrentWeekTeProjections.html")));
            currentWeekScraper = new NumberFireScraper(numberFireProjectionsDocument, new CurrentWeekNumberFireHtmlScraper());
            currentWeekScraper.scrape();
        } catch(Exception ex){
            fail("FAILED to open nfCurrentWeekTeProjections");
        }
    }

    @Test
    public void shouldBeAbleToFindNinetyFourPlayersInCurrentWeekProjections(){
        assertEquals(String.format("NumberFireScraper did not find %d players", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, currentWeekScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainDelanieWalkerInCurrentWeekProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, currentWeekScraper.getPlayerRankings());

        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }


    @Test
    public void delanieWalkerShouldBeRankedNinetyEightInCurrentWeekProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, currentWeekScraper.getPlayerRankings());

        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }

    @Test
    public void delanieWalkerShouldHaveSixAndFortyFiveFirePointsInCurrentWeekProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, currentWeekScraper.getPlayerRankings());

        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's FirePoints correctly", PLAYER_NAME), PLAYER_FIRE_POINTS, player.getFirePoints());
    }
}
