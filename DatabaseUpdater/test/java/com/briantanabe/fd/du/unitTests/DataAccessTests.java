package com.briantanabe.fd.du.unitTests;

import com.briantanabe.fd.fantasy.player.NflPlayer;
import com.briantanabe.fd.providers.PlayerIdProvider;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by BTanabe on 9/30/2014.
 */
public class DataAccessTests {

    @Test
    public void shouldBeAbleToUseEspnLeaguePlayerOwnershipProvider(){
        fail("NOT YET IMPLEMENTED");
    }

    @Test
    public void shouldBeAbleToUseNumberFireCurrentWeekProjectionsProvider(){
        fail("NOT YET IMPLEMENTED");
    }

    @Test
    public void shouldBeAbleToUseNumberFireRemainingSeasonProjectionsProvider(){
        fail("NOT YET IMPLEMENTED");
    }

    @Test
    public void shouldBeAbleToUseThePlayerIdProvider(){
        PlayerIdProvider playerIdProvider = mock(PlayerIdProvider.class);

        ArrayList<NflPlayer> players = new ArrayList<NflPlayer>();
        players.add(new NflPlayer(1000, 2000, "Brian Tanabe"));
        players.add(new NflPlayer(1001, 2001, "Benjamin Coutrney"));
        players.add(new NflPlayer(1002, 2002, "Samuel Courtney"));
        when(playerIdProvider.getAllPlayersAsArrayList()).thenReturn(players);

        assertEquals("Was not able to use PlayerIdProvider", 3, players.size());
    }
}
