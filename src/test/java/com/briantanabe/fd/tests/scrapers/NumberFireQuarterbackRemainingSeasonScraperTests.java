package com.briantanabe.fd.tests.scrapers;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scrapers.numberFire.NumberFireScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.RemainingSeasonNumberFireJsonDefenseScraper;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
public class NumberFireQuarterbackRemainingSeasonScraperTests {
    private static NumberFireScraper remainingSeasonScraper;

    @BeforeClass
    public static void setup(){
        try {
            Document numberFireRemainingYearProjectionsDocument = Jsoup.parse(FileUtils.readFileToString(new File("./src/test/resources/WebPages/nfRemainingSeasonQbProjections.html")));
            remainingSeasonScraper = new NumberFireScraper(numberFireRemainingYearProjectionsDocument, new RemainingSeasonNumberFireJsonDefenseScraper());
            remainingSeasonScraper.scrape();
        } catch(Exception ex){
            fail("FAILED to open nfRemainingSeasonQbProjections");
        }
    }

    @Test
    public void shouldBeAbleToFindSeventyFivePlayersInRemainingSeasonProjections(){
        assertEquals("NumberFireScraper did not find 75 teams", 75, remainingSeasonScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainTheDenverBroncosInRemainingSeasonProjections(){
        NumberFireRanking broncos = findPlayerByPlayerName("Jay Cutler", remainingSeasonScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
    }


    @Test
    public void broncosShouldBeRankedEighthInRemainingSeasonProjections(){
        NumberFireRanking broncos = findPlayerByPlayerName("Jay Cutler", remainingSeasonScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
        assertEquals("Failed to parse the correct ranking", 8, broncos.getRanking());
    }

    @Test
    public void broncosShouldHaveNinetyThreeAndFiftyFourFirePointsInRemainingSeasonProjections(){
        NumberFireRanking broncos = findPlayerByPlayerName("Jay Cutler", remainingSeasonScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
        assertEquals("Failed to parse the Bronco's FirePoints correctly", 226.04, broncos.getFirePoints());
    }
}
