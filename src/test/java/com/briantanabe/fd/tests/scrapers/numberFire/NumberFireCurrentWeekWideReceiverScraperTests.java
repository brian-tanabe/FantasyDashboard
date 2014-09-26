package com.briantanabe.fd.tests.scrapers.numberFire;

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
public class NumberFireCurrentWeekWideReceiverScraperTests {
    private static final String PLAYER_NAME = "Brandon Marshall";
    private static final int NUMBER_OF_PLAYERS = 178;
    private static final int PLAYER_RANKING = 27;
    private static final double PLAYER_FIRE_POINTS = 13.64;

    private static NumberFireScraper currentWeekScraper;

    @BeforeClass
    public static void setup(){
        try {
            Document numberFireCurrentWeekProjectionsDocument = Jsoup.parse(FileUtils.readFileToString(new File("./src/test/resources/WebPages/nfCurrentWeekWrProjections.html")));
            currentWeekScraper = new NumberFireScraper(numberFireCurrentWeekProjectionsDocument, new CurrentWeekNumberFireHtmlScraper());
            currentWeekScraper.scrape();
        } catch(Exception ex){
            fail("FAILED to open nfCurrentSeasonWrProjections");
        }
    }

    @Test
    public void shouldBeAbleToFindOneHundredSeventyEightWideReceiversInCurrentWeekProjections(){
        assertEquals(String.format("Did not find %d wide receivers", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, currentWeekScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainBrandonMarshallInCurrentWeekProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, currentWeekScraper.getPlayerRankings());

        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }

    @Test
    public void brandonMarshallShouldBeRankedSecondInCurrentWeekProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, currentWeekScraper.getPlayerRankings());

        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }


    @Test
    public void brandonMarshallShouldHaveThirteenAndSixtyFourFirePointsInCurrentWeekProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, currentWeekScraper.getPlayerRankings());

        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's FirePoints correctly", PLAYER_NAME), PLAYER_FIRE_POINTS, player.getFirePoints());
    }
}
