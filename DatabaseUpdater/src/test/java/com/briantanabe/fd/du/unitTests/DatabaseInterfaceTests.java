package com.briantanabe.fd.du.unitTests;

import com.briantanabe.fd.du.updater.DatabaseInterface;
import com.briantanabe.fd.fantasy.player.NflPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.briantanabe.fd.unitTests.scrapers.PlayerFinder.findPlayerByPlayerName;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by BTanabe on 9/30/2014.
 */
public class DatabaseInterfaceTests {
    private static DatabaseInterface updater;

    @Before
    public void setup(){
        updater = DatabaseInterface.getInstance();
    }

    @After
    public void tearDown(){
        updater.close();
    }

    @Test
    public void shouldBeAbleToReachDatabase(){
        assertTrue("Failed to isConnected to our database", updater.isConnected());
    }

    @Test
    public void shouldBeAbleToAddOnePlayerIdToDatabase(){
        NflPlayer playerOne = new NflPlayer(1000, 2000, "Brian Tanabe");

        ArrayList<NflPlayer> playersToAdd = new ArrayList<NflPlayer>();
        playersToAdd.add(playerOne);

        updater.insert(playersToAdd);

        ArrayList<NflPlayer> playersFromDatabase = updater.getAllNflPlayersInDatabase();
        assertEquals("Did not find the correct number of players in the database", playersToAdd.size(), playersFromDatabase.size());
        assertEquals("Player name does not match", playerOne.getName(), findPlayerByPlayerName(playerOne.getName(), playersFromDatabase).getName());
    }

    @Test
    public void shouldBeAbleToAddMultiplePlayers(){
        ArrayList<NflPlayer> playersToAdd = new ArrayList<NflPlayer>();
        playersToAdd.add(new NflPlayer(1000, 2000, "Brian Tanabe"));
        playersToAdd.add(new NflPlayer(1001, 2001, "Benjamin Courtney"));
        playersToAdd.add(new NflPlayer(1002, 2002, "Samuel Courtney"));

        updater.insert(playersToAdd);
        assertEquals("Did not find the correct number of players in the database", playersToAdd.size(), updater.getAllNflPlayersInDatabase().size());
    }
}
