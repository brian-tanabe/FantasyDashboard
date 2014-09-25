package com.briantanabe.fd.tests.web;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.scraper.NumberFireJsonDefenseScraper;
import com.briantanabe.fd.scraper.NumberFireScraper;
import static com.briantanabe.fd.web.WebPage.*;
import com.briantanabe.fd.web.WebRequest;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Consumer;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;

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
        try {
            assertEquals("NumberFireScraper did not find 32 teams", 32, scraper.getPlayerRankings().size());
        } catch(Exception ex){
            fail("FAILED to download NumberFire's HTML");
        }
    }

    @Test
    public void shouldContainTheDenverBroncos(){
        NumberFireRanking broncos = null;
        for(NumberFireRanking defense : scraper.getPlayerRankings()){
            if(defense.getName().contains("Denver")){
                broncos = defense;
            }
        }

        assertNotNull("Failed to find the Broncos", broncos);
    }

    @Test
    public void broncosShouldBeRankedTwentyFourth(){
        NumberFireRanking broncos = null;
        for(NumberFireRanking defense : scraper.getPlayerRankings()){
            if(defense.getName().contains("Denver")){
                broncos = defense;
            }
        }

        assertNotNull("Failed to find the Broncos", broncos);
        assertEquals("Failed to parse the correct ranking", 24, broncos.getRanking());
    }
}
