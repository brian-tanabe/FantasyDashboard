package com.briantanabe.fd.tests.scrapers;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scrapers.numberFire.NumberFireScraper;
import com.briantanabe.fd.scrapers.numberFire.positions.CurrentWeekNumberFireHtmlDefenseScraper;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by Brian on 9/25/14.
 */
public class NumberFireDefenseCurrentWeekScraperTests {
    private static NumberFireScraper currentWeekScraper;

    @BeforeClass
    public static void setup(){
        try {
            Document numberFireCurrentWeekProjectionsDocument = Jsoup.parse(FileUtils.readFileToString(new File("./src/test/resources/WebPages/nfCurrentWeekDefProjections.html")));
            currentWeekScraper = new NumberFireScraper(numberFireCurrentWeekProjectionsDocument, new CurrentWeekNumberFireHtmlDefenseScraper());
            currentWeekScraper.scrape();
        } catch(Exception ex){
            fail("FAILED to open nfRemainingSeasonDefProjections");
        }
    }

    @Test
    public void shouldBeAbleToFindThirtyTwoTeamsInCurrentWeekProjections(){
        assertEquals("NumberFireScraper did not find 32 teams", 32, currentWeekScraper.getPlayerRankings().size());
    }

    @Test
    public void shouldContainTheDenverBroncosInCurrentWeekProjections(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", currentWeekScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
    }

    @Test
    public void broncosShouldBeRankedTwentySeventhInCurrentWeekProjections(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", currentWeekScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
        Assert.assertEquals("Failed to parse the correct ranking", 27, broncos.getRanking());
    }


    @Test
    public void broncosShouldHaveZeroFirePointsInCurrentWeekProjections(){
        NumberFireRanking broncos = findTeamByTeamName("Denver", currentWeekScraper.getPlayerRankings());

        assertNotNull("Failed to find the Broncos", broncos);
        Assert.assertEquals("Failed to parse the Bronco's FirePoints correctly", 0.0, broncos.getFirePoints());
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
