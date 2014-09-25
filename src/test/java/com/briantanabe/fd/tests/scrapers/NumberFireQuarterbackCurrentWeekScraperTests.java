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
public class NumberFireQuarterbackCurrentWeekScraperTests {
    private static NumberFireScraper remainingSeasonScraper;

    @BeforeClass
    public static void setup(){
        try {
            Document numberFireCurrentWeekProjectionsDocument = Jsoup.parse(FileUtils.readFileToString(new File("./src/test/resources/WebPages/nfCurrentWeekQbProjections.html")));
            remainingSeasonScraper = new NumberFireScraper(numberFireCurrentWeekProjectionsDocument, new CurrentWeekNumberFireHtmlScraper());
            remainingSeasonScraper.scrape();
        } catch(Exception ex){
            fail("FAILED to open nfCurrentSeasonQbProjections");
        }
    }

    @Test
    public void shouldBeAbleToFindSeventyFiveQuarterbacksInCurrentWeekProjections(){
        assertEquals("Did not find the right number of quarterbacks", 75, remainingSeasonScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainJayCutlerInCurrentWeekProjections(){
        NumberFireRanking jayCutler = findPlayerByPlayerName("Jay Cutler", remainingSeasonScraper.getPlayerRankings());

        assertNotNull("Failed to find the Jay Cutler", jayCutler);
    }

    @Test
    public void jayCutlerShouldBeRankedFourthInCurrentWeekProjections(){
        NumberFireRanking jayCutler = findPlayerByPlayerName("Jay Cutler", remainingSeasonScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", jayCutler);
        TestCase.assertEquals("Failed to parse the correct ranking", 4, jayCutler.getRanking());
    }


    @Test
    public void jayCutlerShouldHaveNineteenAndTwoFirePointsInCurrentWeekProjections(){
        NumberFireRanking jayCutler = findPlayerByPlayerName("Jay Cutler", remainingSeasonScraper.getPlayerRankings());

        assertNotNull("Failed to find Jay Cutler", jayCutler);
        TestCase.assertEquals("Failed to parse Jay Cutler's FirePoints correctly", 19.02, jayCutler.getFirePoints());
    }
}
