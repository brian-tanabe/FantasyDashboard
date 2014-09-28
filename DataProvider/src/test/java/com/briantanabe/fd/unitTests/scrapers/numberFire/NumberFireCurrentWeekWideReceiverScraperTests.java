package com.briantanabe.fd.unitTests.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireCurrentWeekProjection;
import com.briantanabe.fd.scrapers.numberFire.NumberFirePageScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.CurrentWeekNumberFireHtmlScraper;
import junit.framework.TestCase;
import org.jsoup.nodes.Document;
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
public class NumberFireCurrentWeekWideReceiverScraperTests {
    private static final String PLAYER_NAME = "Brandon Marshall";
    private static final int NUMBER_OF_PLAYERS = 178;
    private static final int PLAYER_RANKING = 27;
    private static final double PLAYER_FIRE_POINTS = 13.64;
    private static final int NUMBER_FIRE_ID = 554;

    private static NumberFirePageScraper currentWeekScraper;
    private static NumberFireCurrentWeekProjection player;

    @BeforeClass
    public static void setup(){
        Document numberFireCurrentWeekProjectionsDocument = getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfCurrentWeekWrProjections.html");
        currentWeekScraper = new NumberFirePageScraper(numberFireCurrentWeekProjectionsDocument, new CurrentWeekNumberFireHtmlScraper());
        currentWeekScraper.scrape();
        player = findPlayerByPlayerName(PLAYER_NAME, (ArrayList<NumberFireCurrentWeekProjection>) currentWeekScraper.getPlayerRankings());
    }

    @Test
    public void shouldBeAbleToFindOneHundredSeventyEightWideReceiversInCurrentWeekProjections(){
        assertEquals(String.format("Did not find %d wide receivers", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, currentWeekScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainBrandonMarshallInCurrentWeekProjections(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }

    @Test
    public void brandonMarshallShouldBeRankedSecondInCurrentWeekProjections(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }


    @Test
    public void brandonMarshallShouldHaveThirteenAndSixtyFourFirePointsInCurrentWeekProjections(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's FirePoints correctly", PLAYER_NAME), PLAYER_FIRE_POINTS, player.getFirePoints());
    }


    @Test
    public void playerShouldHaveTheProperNumberFireId(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's numberFireId correctly", PLAYER_NAME), NUMBER_FIRE_ID, player.getNumberFireId());
    }
}
