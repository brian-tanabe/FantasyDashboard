package com.briantanabe.fd.dp.tests.unit.scrapers.numberFire;

import com.briantanabe.fd.dm.models.PlayerIdsEntity;
import com.briantanabe.fd.dp.scrapers.numberFire.positions.NumberFireAndEspnIdScraper;
import com.briantanabe.fd.dp.tests.fixtures.NumberFireRemainingSeasonProjectionFixture;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.briantanabe.fd.dp.tests.unit.scrapers.PlayerFinder.findPlayerIdEntityByPlayerName;
import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Brian on 10/17/2014.
 */
public class NumberFireAndEspnIdScraperTightEndTests {
    private static final String PLAYER_NAME = "Julius Thomas";
    private static final int NUMBER_OF_PLAYERS = 94;
    private static final int ESPN_PLAYER_ID = 14204;
    private static final int NUMBER_FIRE_ID = 4353;

    private static NumberFireAndEspnIdScraper scraper;
    private static PlayerIdsEntity player;

    @BeforeClass
    public static void setup(){
        scraper = new NumberFireAndEspnIdScraper();
        scraper.scrape(NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonTightEndProjectionDocument());
        player = findPlayerIdEntityByPlayerName(PLAYER_NAME, scraper.getPlayerProjections());
    }

    @Test
    public void shouldBeAbleToFindNinetyFourPlayersInRemainingSeasonProjections(){
        assertEquals(String.format("NumberFireScraper did not find %d players", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, scraper.getPlayerProjections().size());
    }

    @Test
    public void shouldContainJuliusThomasInRemainingSeasonProjections(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }

    @Test
    public void shouldBeAbleToParseEspnPlayerIdProperly(){
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's ESPN player ID correctly", PLAYER_NAME), ESPN_PLAYER_ID, player.getEspnId());
    }

    @Test
    public void playerShouldHaveTheProperNumberFireId(){
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's numberFireId correctly", PLAYER_NAME), NUMBER_FIRE_ID, player.getNumberFireId());
    }
}
