package com.briantanabe.fd.dp.tests.unit.scrapers.numberFire;

import com.briantanabe.fd.dm.models.PlayerIdsEntity;
import com.briantanabe.fd.dp.scrapers.numberFire.positions.NumberFireAndEspnIdScraper;
import com.briantanabe.fd.dp.tests.fixtures.NumberFireRemainingSeasonProjectionFixture;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.briantanabe.fd.dp.tests.unit.scrapers.PlayerFinder.findPlayerIdEntityByPlayerName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Brian on 10/17/2014.
 */
public class NumberFireAndEspnIdScraperDefenseTests {
    private static final String PLAYER_NAME = "Denver D/ST";
    private static final int NUMBER_OF_PLAYERS = 32;
    private static final int NUMBER_FIRE_ID = 2901;
    private static final int ESPN_PLAYER_ID = 60007;

    private static NumberFireAndEspnIdScraper scraper;
    private static PlayerIdsEntity player;

    private static NumberFireAndEspnIdScraper myScraper = new NumberFireAndEspnIdScraper();

    @BeforeClass
    public static void setup(){
        scraper = new NumberFireAndEspnIdScraper();
        myScraper.scrape(NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonDefenseProjectionDocument());
        player = findPlayerIdEntityByPlayerName(PLAYER_NAME, myScraper.getPlayerProjections());
    }

    @Test
    public void shouldBeAbleToFindThirtyTwoTeamsInCurrentWeekProjections(){
        assertEquals(String.format("Did not find %d teams", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, myScraper.getPlayerProjections().size());
    }

    @Test
    public void shouldBeAbleToFindRandomPlayer(){
        assertNotNull(String.format("Failed to find the %s", PLAYER_NAME), player);
    }

    @Test
    public void shouldBeAbleToParseEspnPlayerIdProperly(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's ESPN player ID correctly", PLAYER_NAME), ESPN_PLAYER_ID, player.getEspnId());
    }

    @Test
    public void playerShouldHaveTheProperNumberFireId(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's numberFireId correctly", PLAYER_NAME), NUMBER_FIRE_ID, player.getNumberFireId());
    }
}
