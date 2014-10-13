package com.briantanabe.fd.du.tests.unit;

import com.briantanabe.fd.dp.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.dp.fantasy.player.NflPlayer;
import com.briantanabe.fd.dp.fantasy.player.NumberFireCurrentWeekProjection;
import com.briantanabe.fd.dp.fantasy.player.NumberFireRemainingSeasonProjection;
import com.briantanabe.fd.du.log.LoggingUtility;
import com.briantanabe.fd.du.tests.fixtures.EspnNflPlayerFixture;
import com.briantanabe.fd.du.tests.fixtures.NflPlayerFixture;
import com.briantanabe.fd.du.tests.fixtures.NumberFireCurrentWeekProjectionFixture;
import com.briantanabe.fd.du.tests.fixtures.NumberFireRemainingSeasonProjectionFixture;
import com.briantanabe.fd.du.updater.DatabaseInterface;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;

import static com.briantanabe.fd.dp.tests.unit.scrapers.PlayerFinder.findPlayerByPlayerName;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by BTanabe on 9/30/2014.
 */
public class DatabaseInterfaceTests {
    private static DatabaseInterface updater;

    @BeforeClass
    @Transactional
    public static void turnLoggingOff(){
        LoggingUtility.turnLoggingOff();
        updater = DatabaseInterface.getInstance();
    }

    @Before
    @Transactional
    public void setup(){
//        updater = DatabaseInterface.getInstance();
    }

    @AfterClass
    @Transactional
    public static void tearDown(){
//        updater.close();
    }



    @Test
    @Transactional
    public void shouldBeAbleToReachDatabase(){
        assertTrue("Failed to isConnected to our database", updater.isConnected());
    }

    @Test
    @Transactional
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
    @Transactional
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
    @Transactional
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
    @Transactional
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
    @Transactional
    public void shouldBeAbleToAddOneNumberFireCurrentWeekProjectionToDatabase(){
        ArrayList<NumberFireCurrentWeekProjection> playersToAdd = NumberFireCurrentWeekProjectionFixture.getSampleNumberFireCurrentWeekProjections(1);

        updater.insert(playersToAdd);

        ArrayList<NumberFireCurrentWeekProjection> playersFromDatabase = updater.getAllNumberFireCurrentWeekProjectionsFromDatabase();
        assertEquals("Did not find the correct number of NumberFireCurrentWeekProjections in the database", playersToAdd.size(), playersFromDatabase.size());

        NumberFireCurrentWeekProjection playerOne = findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase);
        assertEquals("PlayerOne's player_name does not match", playersToAdd.get(0).getName(), playerOne.getName());
        assertEquals("PlayerOne's number_fire_id does not match", playersToAdd.get(0).getNumberFireId(), playerOne.getNumberFireId());
        assertEquals("PlayerOne's number_fire_ranking does not match", playersToAdd.get(0).getRanking(), playerOne.getRanking());
        assertEquals("PlayerOne's fire_points do not match", playersToAdd.get(0).getFirePoints(), playerOne.getFirePoints());
    }

    @Test
    @Transactional
    public void shouldBeAbleToAddMultipleNumberFireCurrentWeekProjectionsToDatabase(){
        ArrayList<NumberFireCurrentWeekProjection> playersToAdd = NumberFireCurrentWeekProjectionFixture.getSampleNumberFireCurrentWeekProjections(3);

        updater.insert(playersToAdd);

        ArrayList<NumberFireCurrentWeekProjection> playersFromDatabase = updater.getAllNumberFireCurrentWeekProjectionsFromDatabase();
        assertEquals("Did not find the correct number of NumberFireCurrentWeekProjections in the database", playersToAdd.size(), playersFromDatabase.size());

        NumberFireCurrentWeekProjection playerOne = findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase);
        assertEquals("PlayerOne's player_name does not match", playersToAdd.get(0).getName(), playerOne.getName());
        assertEquals("PlayerOne's number_fire_id does not match", playersToAdd.get(0).getNumberFireId(), playerOne.getNumberFireId());
        assertEquals("PlayerOne's number_fire_ranking does not match", playersToAdd.get(0).getRanking(), playerOne.getRanking());
        assertEquals("PlayerOne's fire_points do not match", playersToAdd.get(0).getFirePoints(), playerOne.getFirePoints());

        NumberFireCurrentWeekProjection playerTwo = findPlayerByPlayerName(playersToAdd.get(1).getName(), playersFromDatabase);
        assertEquals("PlayerTwo's player_name does not match", playersToAdd.get(1).getName(), playerTwo.getName());
        assertEquals("PlayerTwo's number_fire_id does not match", playersToAdd.get(1).getNumberFireId(), playerTwo.getNumberFireId());
        assertEquals("PlayerTwo's number_fire_ranking does not match", playersToAdd.get(1).getRanking(), playerTwo.getRanking());
        assertEquals("PlayerTwo's fire_points do not match", playersToAdd.get(1).getFirePoints(), playerTwo.getFirePoints());

        NumberFireCurrentWeekProjection playerThree = findPlayerByPlayerName(playersToAdd.get(2).getName(), playersFromDatabase);
        assertEquals("PlayerThree's player_name does not match", playersToAdd.get(2).getName(), playerThree.getName());
        assertEquals("PlayerThree's number_fire_id does not match", playersToAdd.get(2).getNumberFireId(), playerThree.getNumberFireId());
        assertEquals("PlayerThree's number_fire_ranking does not match", playersToAdd.get(2).getRanking(), playerThree.getRanking());
        assertEquals("PlayerThree's fire_points do not match", playersToAdd.get(2).getFirePoints(), playerThree.getFirePoints());
    }

    @Test
    @Transactional
    public void shouldBeAbleToAddOneNumberFireRemainingSeasonProjectionToDatabase(){
        ArrayList<NumberFireRemainingSeasonProjection> playersToAdd = NumberFireRemainingSeasonProjectionFixture.getSampleNumberFireRemainingSeasonProjections(1);
        updater.insert(playersToAdd);

        ArrayList<NumberFireRemainingSeasonProjection> playersFromDatabase = updater.getAllNumberFireRemainingSeasonProjectionsFromDatabase();
        assertEquals("Did not find the correct number of NumberFireRemainingSeasonProjections in the database", playersToAdd.size(), playersFromDatabase.size());

        NumberFireRemainingSeasonProjection playerOne = findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase);
        assertEquals("PlayerOne's player_name does not match", playersToAdd.get(0).getName(), playerOne.getName());
        assertEquals("PlayerOne's number_fire_id does not match", playersToAdd.get(0).getNumberFireId(), playerOne.getNumberFireId());
        assertEquals("PlayerOne's number_fire_ranking does not match", playersToAdd.get(0).getRanking(), playerOne.getRanking());
        assertEquals("PlayerOne's fire_points do not match", playersToAdd.get(0).getFirePoints(), playerOne.getFirePoints());
    }

    @Test
    @Transactional
    public void shouldBeAbleToAddMultipleNumberFireRemainingSeasonProjectionsToDatabase(){
        ArrayList<NumberFireRemainingSeasonProjection> playersToAdd = NumberFireRemainingSeasonProjectionFixture.getSampleNumberFireRemainingSeasonProjections(3);
        updater.insert(playersToAdd);

        ArrayList<NumberFireRemainingSeasonProjection> playersFromDatabase = updater.getAllNumberFireRemainingSeasonProjectionsFromDatabase();
        assertEquals("Did not find the correct number of NumberFireRemainingSeasonProjections in the database", playersToAdd.size(), playersFromDatabase.size());

        NumberFireRemainingSeasonProjection playerOne = findPlayerByPlayerName(playersToAdd.get(0).getName(), playersFromDatabase);
        assertEquals("PlayerOne's player_name does not match", playersToAdd.get(0).getName(), playerOne.getName());
        assertEquals("PlayerOne's number_fire_id does not match", playersToAdd.get(0).getNumberFireId(), playerOne.getNumberFireId());
        assertEquals("PlayerOne's number_fire_ranking does not match", playersToAdd.get(0).getRanking(), playerOne.getRanking());
        assertEquals("PlayerOne's fire_points do not match", playersToAdd.get(0).getFirePoints(), playerOne.getFirePoints());

        NumberFireRemainingSeasonProjection playerTwo = findPlayerByPlayerName(playersToAdd.get(1).getName(), playersFromDatabase);
        assertEquals("PlayerTwo's player_name does not match", playersToAdd.get(1).getName(), playerTwo.getName());
        assertEquals("PlayerTwo's number_fire_id does not match", playersToAdd.get(1).getNumberFireId(), playerTwo.getNumberFireId());
        assertEquals("PlayerTwo's number_fire_ranking does not match", playersToAdd.get(1).getRanking(), playerTwo.getRanking());
        assertEquals("PlayerTwo's fire_points do not match", playersToAdd.get(1).getFirePoints(), playerTwo.getFirePoints());

        NumberFireRemainingSeasonProjection playerThree = findPlayerByPlayerName(playersToAdd.get(2).getName(), playersFromDatabase);
        assertEquals("PlayerThree's player_name does not match", playersToAdd.get(2).getName(), playerThree.getName());
        assertEquals("PlayerThree's number_fire_id does not match", playersToAdd.get(2).getNumberFireId(), playerThree.getNumberFireId());
        assertEquals("PlayerThree's number_fire_ranking does not match", playersToAdd.get(2).getRanking(), playerThree.getRanking());
        assertEquals("PlayerThree's fire_points do not match", playersToAdd.get(2).getFirePoints(), playerThree.getFirePoints());
    }
}
