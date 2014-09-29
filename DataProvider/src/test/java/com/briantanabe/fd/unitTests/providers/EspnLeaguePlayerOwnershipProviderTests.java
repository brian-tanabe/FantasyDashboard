package com.briantanabe.fd.unitTests.providers;

import com.briantanabe.fd.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.providers.EspnLeaguePlayerOwnershipProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Brian on 9/28/14.
 */
public class EspnLeaguePlayerOwnershipProviderTests {
    private static ArrayList<EspnNflPlayer> players;

    @BeforeClass
    public static void setup(){
        try {
            EspnLeaguePlayerOwnershipProvider provider = new EspnLeaguePlayerOwnershipProvider();
            provider.scrapeForOwnershipInfo();
            players = provider.getPlayerOwnershipInfo();
        } catch(Exception ex){
            fail("Failed to scrape for the ESPN league ownership info");
        }
    }

    @Test
    public void shouldBeAbleToFindSevenHundredPlayers(){
        assertEquals("Did not find the proper number of players", 700, players.size());
    }
}
