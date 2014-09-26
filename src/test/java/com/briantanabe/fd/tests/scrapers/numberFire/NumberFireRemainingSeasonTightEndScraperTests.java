package com.briantanabe.fd.tests.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scrapers.numberFire.NumberFireScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.RemainingSeasonNumberFireJsonScraper;
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
public class NumberFireRemainingSeasonTightEndScraperTests {
    private static final String PLAYER_NAME = "Julius Thomas";
    private static final int NUMBER_OF_PLAYERS = 94;
    private static final int PLAYER_RANKING = 3;
    private static final double PLAYER_FIRE_POINTS = 117.43;

    private static NumberFireScraper remainingSeasonScraper;

    @BeforeClass
    public static void setup(){
        try {
            Document numberFireRemainingYearProjectionsDocument = Jsoup.parse(FileUtils.readFileToString(new File("./src/test/resources/WebPages/nfRemainingSeasonTeProjections.html")));
            remainingSeasonScraper = new NumberFireScraper(numberFireRemainingYearProjectionsDocument, new RemainingSeasonNumberFireJsonScraper());
            remainingSeasonScraper.scrape();
        } catch(Exception ex){
            fail("FAILED to open nfRemainingSeasonTeProjections");
        }
    }

    @Test
    public void shouldBeAbleToFindNinetyFourPlayersInRemainingSeasonProjections(){
        assertEquals(String.format("NumberFireScraper did not find %d players", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, remainingSeasonScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainJuliusThomasInRemainingSeasonProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, remainingSeasonScraper.getPlayerRankings());

        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }


    @Test
    public void juliusThomasShouldBeRankedThirdInRemainingSeasonProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, remainingSeasonScraper.getPlayerRankings());

        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals("Failed to parse the correct ranking", PLAYER_RANKING, player.getRanking());
    }

    @Test
    public void juliusThomasShouldHaveOneHundredSeventeenAndFourtyThreeFirePointsInRemainingSeasonProjections(){
        NumberFireRanking player = findPlayerByPlayerName(PLAYER_NAME, remainingSeasonScraper.getPlayerRankings());

        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's FirePoints correctly", PLAYER_NAME), PLAYER_FIRE_POINTS, player.getFirePoints());
    }
}
