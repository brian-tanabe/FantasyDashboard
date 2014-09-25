package com.briantanabe.fd.tests.web;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scraper.NumberFireJsonDefenseScraper;
import com.briantanabe.fd.scraper.NumberFireScraper;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static junit.framework.TestCase.*;

/**
 * Created by Brian on 9/24/14.
 */
public class NumberFireScraperTests {
    private static NumberFireScraper scraper;

    @BeforeClass
    public static void setup(){
        try {
            Document numberFireRemainingYearProjectionsDocument = Jsoup.parse(FileUtils.readFileToString(new File("./src/test/resources/WebPages/nfRemainingSeasonDefProjections.html")));
            scraper = new NumberFireScraper(numberFireRemainingYearProjectionsDocument, new NumberFireJsonDefenseScraper());
            scraper.scrape();
        } catch(Exception ex){
            fail("FAILED to open nfRemainingSeasonDefProjections");
        }
    }

    @Test
    public void shouldBeAbleToFindThirtyTwoTeams(){
        assertEquals("NumberFireScraper did not find 32 teams", 32, scraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainTheDenverBroncos(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", scraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
    }

    @Test
    public void broncosShouldBeRankedTwentyFourth(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", scraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
        assertEquals("Failed to parse the correct ranking", 24, broncos.getRanking());
    }

    @Test
    public void broncosShouldHaveNinetyThreeAndFiftyFourFirePoints(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", scraper.getPlayerRankings());

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
