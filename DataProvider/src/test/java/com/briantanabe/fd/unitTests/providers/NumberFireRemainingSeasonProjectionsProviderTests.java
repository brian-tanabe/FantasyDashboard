package com.briantanabe.fd.unitTests.providers;

import com.briantanabe.fd.fantasy.player.NumberFireRemainingSeasonProjection;
import com.briantanabe.fd.providers.NumberFireRemainingSeasonProjectionsProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.briantanabe.fd.unitTests.scrapers.PlayerFinder.findPlayerByPlayerName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Brian on 9/28/14.
 */
public class NumberFireRemainingSeasonProjectionsProviderTests {
    private static ArrayList<NumberFireRemainingSeasonProjection> playerProjections = new ArrayList<NumberFireRemainingSeasonProjection>();

    @BeforeClass
    public static void setup(){
        try {
            NumberFireRemainingSeasonProjectionsProvider provider = new NumberFireRemainingSeasonProjectionsProvider();
            provider.scrapeForNumberFiresRemainingSeasonProjections();
            playerProjections = provider.getPlayerProjections();
        } catch(Exception ex){
            ex.printStackTrace();
            fail("Failed to scrape for all numberFire's player remaining season projections");
        }
    }

    @Test
    public void shouldBeAbleToFindRussellWilsonsRemainingSeasonProjections(){
        final String playerName = "Russell Wilson";
        NumberFireRemainingSeasonProjection player = findPlayerByPlayerName(playerName, playerProjections);

        assertEquals(String.format("Unable to find %s's nF remaining season projections", playerName), playerName, player.getName());
    }
}
