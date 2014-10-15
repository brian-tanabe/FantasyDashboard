package com.briantanabe.fd.du.tests.unit;

import com.briantanabe.fd.dp.fantasy.player.NflPlayerPositionAndTeam;
import com.briantanabe.fd.dp.providers.PlayerPositionAndTeamProvider;
import com.briantanabe.fd.dp.tests.fixtures.MockWebRequest;
import com.briantanabe.fd.dp.tests.unit.scrapers.PlayerFinder;
import com.briantanabe.fd.du.datastore.DatabaseAccessor;
import com.briantanabe.fd.du.datastore.DatabaseUpdater;
import com.briantanabe.fd.du.log.LoggingUtility;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by Brian on 10/14/14.
 */
public class PlayerPositionAndTeamDatabaseTests {
    private static DatabaseAccessor accessor = new DatabaseAccessor();
    private static DatabaseUpdater updater = new DatabaseUpdater();

    @BeforeClass
    public static void setup() {
        try {
            LoggingUtility.turnLoggingOff();

            PlayerPositionAndTeamProvider playerPositionAndTeamProvider = new PlayerPositionAndTeamProvider(MockWebRequest.getMockWebRequestForPlayerPositionAndTeamProvider());
            playerPositionAndTeamProvider.scrapeForPlayerPositionsAndTeams();
            List<NflPlayerPositionAndTeam> playerPositionAndTeamRows = playerPositionAndTeamProvider.getAllPlayerPositionsAndTeams();
            updater.insert(playerPositionAndTeamRows);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Failed to create the PlayerPositionAndTeam table");
        }
    }

    @Test
    public void shouldBeAbleToStoreAndRecallPlayersName(){
        final String playerName = "Andrew Luck";
        List<NflPlayerPositionAndTeam> playerPositionAndTeams = accessor.getAllPlayerPositionAndTeamObjectsFromTheDatabase();
        NflPlayerPositionAndTeam playerFromDatabase = PlayerFinder.findPlayerByPlayerName(playerName, playerPositionAndTeams);
        assertEquals("Unable to find Peyton Manning by name", playerName, playerFromDatabase.getName());
    }
}
