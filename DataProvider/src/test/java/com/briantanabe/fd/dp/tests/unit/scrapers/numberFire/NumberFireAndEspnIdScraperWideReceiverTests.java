package com.briantanabe.fd.dp.tests.unit.scrapers.numberFire;

import com.briantanabe.fd.dm.models.PlayerIdsEntity;
import com.briantanabe.fd.dp.scrapers.numberFire.positions.NumberFireAndEspnIdScraper;
import com.briantanabe.fd.dp.tests.fixtures.NumberFireRemainingSeasonProjectionFixture;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.briantanabe.fd.dp.tests.unit.scrapers.PlayerFinder.findPlayerIdEntityByPlayerName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Brian on 10/17/2014.
 */
public class NumberFireAndEspnIdScraperWideReceiverTests {
    private static final String PLAYER_NAME = "Emmanuel Sanders";
    private static final int NUMBER_OF_PLAYERS = 178;
    private static final int ESPN_PLAYER_ID = 13295;
    private static final int NUMBER_FIRE_ID = 2205;

    private static NumberFireAndEspnIdScraper scraper;
    private static PlayerIdsEntity player;

    @BeforeClass
    public static void setup(){
        scraper = new NumberFireAndEspnIdScraper();
        scraper.scrape(NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonWideReceiverProjectionDocument());
        player = findPlayerIdEntityByPlayerName(PLAYER_NAME, scraper.getPlayerProjections());
    }

    @Test
    public void shouldBeAbleToFindOneHundredSeventyEightPlayersInRemainingSeasonProjections(){
        assertEquals(String.format("Did not find %d wide receivers", NUMBER_OF_PLAYERS), NUMBER_OF_PLAYERS, scraper.getPlayerProjections().size());
    }

    @Test
    public void shouldContainEmmanuelSandersInRemainingSeasonProjections(){
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
    }

    @Test
    public void shouldBeAbleToParseEspnPlayerIdProperly(){
        Assert.assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        TestCase.assertEquals(String.format("Failed to parse %s's ESPN player ID correctly", PLAYER_NAME), ESPN_PLAYER_ID, player.getEspnId());
    }

    @Test
    public void playerShouldHaveTheProperNumberFireId(){
        assertNotNull(String.format("Failed to find %s", PLAYER_NAME), player);
        assertEquals(String.format("Failed to parse %s's numberFireId correctly", PLAYER_NAME), NUMBER_FIRE_ID, player.getNumberFireId());
    }
}
