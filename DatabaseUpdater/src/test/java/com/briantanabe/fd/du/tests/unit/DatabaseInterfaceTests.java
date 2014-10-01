package com.briantanabe.fd.du.tests.unit;

import com.briantanabe.fd.du.log.LoggingUtility;
import com.briantanabe.fd.du.test.fixtures.EspnNflPlayerFixture;
import com.briantanabe.fd.du.test.fixtures.NflPlayerFixture;
import com.briantanabe.fd.du.updater.DatabaseInterface;
import com.briantanabe.fd.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.fantasy.player.NflPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.briantanabe.fd.unitTests.scrapers.PlayerFinder.findPlayerByPlayerName;
import static junit.framework.TestCase.*;

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
    public void shouldBeAbleToAddOneNflPlayerToDatabase(){
        ArrayList<NflPlayer> playersToAdd = NflPlayerFixture.getSampleNflPlayers(1);

        updater.insert(playersToAdd);

        ArrayList<NflPlayer> playersFromDatabase = updater.getAllNflPlayersFromDatabase();
        assertEquals("Did not find the correct number of players in the database", playersToAdd.size(), playersFromDatabase.size());

        assertEquals("Player name does not match", playersToAdd.get(0).getName(), findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase).getName());
        assertEquals("Player ESPN Id does not match", playersToAdd.get(0).getEspnPlayerId(), findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase).getEspnPlayerId());
        assertEquals("Player numberFire ID does not match", playersToAdd.get(0).getNumberFireId(), findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase).getNumberFireId());
    }

    @Test
    public void shouldBeAbleToAddMultipleNflPlayersToDatabase(){
        ArrayList<NflPlayer> playersToAdd = NflPlayerFixture.getSampleNflPlayers(3);

        updater.insert(playersToAdd);

        ArrayList<NflPlayer> playersFromDatabase = updater.getAllNflPlayersFromDatabase();
        assertEquals("Did not find the correct number of players in the database", playersToAdd.size(), playersFromDatabase.size());

        assertEquals("PlayerOne's name does not match", playersToAdd.get(0).getName(), findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase).getName());
        assertEquals("PlayerOne's ESPN Id does not match", playersToAdd.get(0).getEspnPlayerId(), findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase).getEspnPlayerId());
        assertEquals("PlayerOne's numberFire ID does not match", playersToAdd.get(0).getNumberFireId(), findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase).getNumberFireId());

        assertEquals("PlayerTwo's name does not match", playersToAdd.get(1).getName(), findPlayerByPlayerName(playersToAdd.get(1).getName(), playersFromDatabase).getName());
        assertEquals("PlayerTwo's ESPN Id does not match", playersToAdd.get(1).getEspnPlayerId(), findPlayerByPlayerName(playersToAdd.get(1).getName(), playersFromDatabase).getEspnPlayerId());
        assertEquals("PlayerTwo's numberFire ID does not match", playersToAdd.get(1).getNumberFireId(), findPlayerByPlayerName(playersToAdd.get(1).getName(), playersFromDatabase).getNumberFireId());

        assertEquals("PlayerThree's name does not match", playersToAdd.get(2).getName(), findPlayerByPlayerName(playersToAdd.get(2).getName(), playersFromDatabase).getName());
        assertEquals("PlayerThree's ESPN Id does not match", playersToAdd.get(2).getEspnPlayerId(), findPlayerByPlayerName(playersToAdd.get(2).getName(), playersFromDatabase).getEspnPlayerId());
        assertEquals("PlayerThree's numberFire ID does not match", playersToAdd.get(2).getNumberFireId(), findPlayerByPlayerName(playersToAdd.get(2).getName(), playersFromDatabase).getNumberFireId());
    }

    @Test
    public void shouldBeAbleToAddOneEspnNflPlayerToDatabase(){
        ArrayList<EspnNflPlayer> playersToAdd = EspnNflPlayerFixture.getSampleEspnNflPlayers(1, 10);

        updater.insert(playersToAdd);

        ArrayList<EspnNflPlayer> playersFromDatabase = updater.getAllEspnNflPlayersFromDatabase();
        assertEquals("Did not find the correct number of EspnNflPlayers in the database", playersToAdd.size(), playersFromDatabase.size());

        assertEquals("PlayerOne's name does not match", playersToAdd.get(0).getName(), findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase).getName());
        assertEquals("PlayerOne's ESPN ID does not match", playersToAdd.get(0).getEspnPlayerId(), findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase).getEspnPlayerId());
        assertEquals("PlayerOne's league ID does not match", playersToAdd.get(0).getLeagueId(), findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase).getLeagueId());
        assertEquals("PlayerOne's owner ID does not match", playersToAdd.get(0).getOwner(), findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase).getOwner());
    }

    @Test
    public void shouldBeAbleToAddMultipleEspnNflPlayersToDatabase(){
        ArrayList<EspnNflPlayer> playersToAdd = EspnNflPlayerFixture.getSampleEspnNflPlayers(3, 10);

        updater.insert(playersToAdd);

        ArrayList<EspnNflPlayer> playersFromDatabase = updater.getAllEspnNflPlayersFromDatabase();
        assertEquals("Did not find the correct number of EspnNflPlayers in the database", playersToAdd.size(), playersFromDatabase.size());

        assertEquals("PlayerOne's name does not match", playersToAdd.get(0).getName(), findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase).getName());
        assertEquals("PlayerOne's ESPN ID does not match", playersToAdd.get(0).getEspnPlayerId(), findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase).getEspnPlayerId());
        assertEquals("PlayerOne's league ID does not match", playersToAdd.get(0).getLeagueId(), findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase).getLeagueId());
        assertEquals("PlayerOne's owner ID does not match", playersToAdd.get(0).getOwner(), findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase).getOwner());

        assertEquals("PlayerTwo's name does not match", playersToAdd.get(1).getName(), findPlayerByPlayerName(playersToAdd.get(1).getName(), playersFromDatabase).getName());
        assertEquals("PlayerTwo's ESPN ID does not match", playersToAdd.get(1).getEspnPlayerId(), findPlayerByPlayerName(playersToAdd.get(1).getName(), playersFromDatabase).getEspnPlayerId());
        assertEquals("PlayerTwo's league ID does not match", playersToAdd.get(1).getLeagueId(), findPlayerByPlayerName(playersToAdd.get(1).getName(), playersFromDatabase).getLeagueId());
        assertEquals("PlayerTwo's owner ID does not match", playersToAdd.get(1).getOwner(), findPlayerByPlayerName(playersToAdd.get(1).getName(), playersFromDatabase).getOwner());

        assertEquals("PlayerThree's name does not match", playersToAdd.get(2).getName(), findPlayerByPlayerName(playersToAdd.get(2).getName(), playersFromDatabase).getName());
        assertEquals("PlayerThree's ESPN ID does not match", playersToAdd.get(2).getEspnPlayerId(), findPlayerByPlayerName(playersToAdd.get(2).getName(), playersFromDatabase).getEspnPlayerId());
        assertEquals("PlayerThree's league ID does not match", playersToAdd.get(2).getLeagueId(), findPlayerByPlayerName(playersToAdd.get(2).getName(), playersFromDatabase).getLeagueId());
        assertEquals("PlayerThree's owner ID does not match", playersToAdd.get(2).getOwner(), findPlayerByPlayerName(playersToAdd.get(2).getName(), playersFromDatabase).getOwner());
    }

    @Test
    public void shouldBeAbleToAddOneNumberFireCurrentWeekProjectionToDatabase(){
        fail("Failed to add one NumberFireCurrentWeekProjection to database");
    }

    @Test
    public void shouldBeAbleToAddMultipleNumberFireCurrentWeekProjectionsToDatabase(){
        fail("Failed to add multiple NumberFireCurrentWeekProjections to database");
    }

    @Test
    public void shouldBeAbleToAddOneNumberFireRemainingSeasonProjectionToDatabase(){
        fail("Failed to add one NumberFireRemainingSeasonProjection to database");
    }

    @Test
    public void shouldBeAbleToAddMultipleNumberFireRemainingSeasonProjectionsToDatabase(){
        fail("Failed to add multiple NumberFireRemainingSeasonProjections to database");
    }
}