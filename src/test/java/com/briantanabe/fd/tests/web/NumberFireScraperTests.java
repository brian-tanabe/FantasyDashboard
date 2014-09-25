package com.briantanabe.fd.tests.web;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scrapers.numberFire.positions.CurrentWeekNumberFireHtmlDefenseScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.RemainingSeasonNumberFireJsonDefenseScraper;
import com.briantanabe.fd.scrapers.numberFire.NumberFireScraper;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.*;

/**
 * Created by Brian on 9/24/14.
 */
public class NumberFireScraperTests {
    private static NumberFireScraper remainingSeasonScraper;
    private static NumberFireScraper currentWeekScraper;

    @BeforeClass
    public static void setup(){
        try {
            Document numberFireRemainingYearProjectionsDocument = Jsoup.parse(FileUtils.readFileToString(new File("./src/test/resources/WebPages/nfRemainingSeasonDefProjections.html")));
            remainingSeasonScraper = new NumberFireScraper(numberFireRemainingYearProjectionsDocument, new RemainingSeasonNumberFireJsonDefenseScraper());
            remainingSeasonScraper.scrape();

            Document numberFireCurrentWeekProjectionsDocument = Jsoup.parse(FileUtils.readFileToString(new File("./src/test/resources/WebPages/nfCurrentWeekDefProjections.html")));
            currentWeekScraper = new NumberFireScraper(numberFireCurrentWeekProjectionsDocument, new CurrentWeekNumberFireHtmlDefenseScraper());
            currentWeekScraper.scrape();
        } catch(Exception ex){
            fail("FAILED to open nfRemainingSeasonDefProjections");
        }
    }

    @Test
    public void shouldBeAbleToFindThirtyTwoTeamsInRemainingSeasonProjections(){
        assertEquals("NumberFireScraper did not find 32 teams", 32, remainingSeasonScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldBeAbleToFindThirtyTwoTeamsInCurrentWeekProjections(){
        assertEquals("NumberFireScraper did not find 32 teams", 32, currentWeekScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainTheDenverBroncosInRemainingSeasonProjections(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", remainingSeasonScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
    }

    @Test
    public void shouldContainTheDenverBroncosInCurrentWeekProjections(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", currentWeekScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
    }

    @Test
    public void broncosShouldBeRankedTwentyFourthInRemainingSeasonProjections(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", remainingSeasonScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
        assertEquals("Failed to parse the correct ranking", 24, broncos.getRanking());
    }

    @Test
    public void broncosShouldBeRankedTwentySeventhInCurrentWeekProjections(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", currentWeekScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
        assertEquals("Failed to parse the correct ranking", 27, broncos.getRanking());
    }

    @Test
    public void broncosShouldHaveNinetyThreeAndFiftyFourFirePointsInRemainingSeasonProjections(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", remainingSeasonScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
        assertEquals("Failed to parse the Bronco's FirePoints correctly", 93.54, broncos.getFirePoints());
    }

    @Test
    public void broncosShouldHaveZeroFirePointsInCurrentWeekProjections(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", currentWeekScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
        assertEquals("Failed to parse the Bronco's FirePoints correctly", 0.0, broncos.getFirePoints());
    }

    private NumberFireRanking findTeamByTeamName(String teamName, ArrayList<NumberFireRanking> teams){
        NumberFireRanking teamToFind = null;
        for(NumberFireRanking defense : teams){
            if(defense.getName().contains(teamName)){
                teamToFind = defense;
            }
        }

        return teamToFind;
    }
}
