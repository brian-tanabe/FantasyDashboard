package com.briantanabe.fd.tests.scrapers;

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
public class NumberFireDefenseRemainingSeasonScraperTests {
    private static NumberFireScraper remainingSeasonScraper;

    @BeforeClass
    public static void setup(){
        try {
            Document numberFireRemainingYearProjectionsDocument = Jsoup.parse(FileUtils.readFileToString(new File("./src/test/resources/WebPages/nfRemainingSeasonDefProjections.html")));
            remainingSeasonScraper = new NumberFireScraper(numberFireRemainingYearProjectionsDocument, new RemainingSeasonNumberFireJsonDefenseScraper());
            remainingSeasonScraper.scrape();
        } catch(Exception ex){
            fail("FAILED to open nfRemainingSeasonDefProjections");
        }
    }

    @Test
    public void shouldBeAbleToFindThirtyTwoTeamsInRemainingSeasonProjections(){
        assertEquals("NumberFireScraper did not find 32 teams", 32, remainingSeasonScraper.getPlayerRankings().size());
    }


    public void shouldContainTheDenverBroncosInRemainingSeasonProjections(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", remainingSeasonScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
    }


    @Test
    public void broncosShouldBeRankedTwentyFourthInRemainingSeasonProjections(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", remainingSeasonScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
        assertEquals("Failed to parse the correct ranking", 24, broncos.getRanking());
    }

    @Test
    public void broncosShouldHaveNinetyThreeAndFiftyFourFirePointsInRemainingSeasonProjections(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", remainingSeasonScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
        assertEquals("Failed to parse the Bronco's FirePoints correctly", 93.54, broncos.getFirePoints());
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
