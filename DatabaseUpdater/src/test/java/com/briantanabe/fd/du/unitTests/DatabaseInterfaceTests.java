package com.briantanabe.fd.du.unitTests;

import com.briantanabe.fd.du.log.LoggingUtility;
import com.briantanabe.fd.du.updater.DatabaseInterface;
import com.briantanabe.fd.fantasy.player.NflPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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

    @BeforeClass
    public static void turnLoggingOff(){
        LoggingUtility.turnLoggingOff();
    }

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
        assertEquals("Player ESPN Id does not match", playerOne.getEspnPlayerId(), findPlayerByPlayerName(playerOne.getName(), playersFromDatabase).getEspnPlayerId());
        assertEquals("Player numberFire ID does not match", playerOne.getNumberFireId(), findPlayerByPlayerName(playerOne.getName(), playersFromDatabase).getNumberFireId());
    }

    @Test
    public void shouldBeAbleToAddMultiplePlayers(){
        NflPlayer playerOne = new NflPlayer(1000, 2000, "Brian Tanabe");
        NflPlayer playerTwo = new NflPlayer(1001, 2001, "Benjamin Courtney");
        NflPlayer playerThree = new NflPlayer(1002, 2002, "Samuel Courtney");

        ArrayList<NflPlayer> playersToAdd = new ArrayList<NflPlayer>();
        playersToAdd.add(playerOne);
        playersToAdd.add(playerTwo);
        playersToAdd.add(playerThree);

        updater.insert(playersToAdd);

        ArrayList<NflPlayer> playersFromDatabase = updater.getAllNflPlayersInDatabase();
        assertEquals("Did not find the correct number of players in the database", playersToAdd.size(), playersFromDatabase.size());

        assertEquals("PlayerOne's name does not match", playerOne.getName(), findPlayerByPlayerName(playerOne.getName(), playersFromDatabase).getName());
        assertEquals("PlayerOne's ESPN Id does not match", playerOne.getEspnPlayerId(), findPlayerByPlayerName(playerOne.getName(), playersFromDatabase).getEspnPlayerId());
        assertEquals("PlayerOne's numberFire ID does not match", playerOne.getNumberFireId(), findPlayerByPlayerName(playerOne.getName(), playersFromDatabase).getNumberFireId());

        assertEquals("PlayerTwo's name does not match", playerTwo.getName(), findPlayerByPlayerName(playerTwo.getName(), playersFromDatabase).getName());
        assertEquals("PlayerTwo's ESPN Id does not match", playerTwo.getEspnPlayerId(), findPlayerByPlayerName(playerTwo.getName(), playersFromDatabase).getEspnPlayerId());
        assertEquals("PlayerTwo's numberFire ID does not match", playerTwo.getNumberFireId(), findPlayerByPlayerName(playerTwo.getName(), playersFromDatabase).getNumberFireId());

        assertEquals("PlayerThree's name does not match", playerThree.getName(), findPlayerByPlayerName(playerThree.getName(), playersFromDatabase).getName());
        assertEquals("PlayerThree's ESPN Id does not match", playerThree.getEspnPlayerId(), findPlayerByPlayerName(playerThree.getName(), playersFromDatabase).getEspnPlayerId());
        assertEquals("PlayerThree's numberFire ID does not match", playerThree.getNumberFireId(), findPlayerByPlayerName(playerThree.getName(), playersFromDatabase).getNumberFireId());
    }
}
