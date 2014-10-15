package com.briantanabe.fd.du.tests.unit;

import com.briantanabe.fd.dp.fantasy.player.NflPlayerPositionAndTeam;
import com.briantanabe.fd.dp.nfl.position.Position;
import com.briantanabe.fd.dp.nfl.team.NflTeam;
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
import static org.junit.Assert.assertTrue;

/**
 * Created by Brian on 10/14/14.
 */
public class PlayerPositionAndTeamDatabaseTests {
    private static DatabaseAccessor accessor = new DatabaseAccessor();
    private static DatabaseUpdater updater = new DatabaseUpdater();

    private static NflPlayerPositionAndTeam playerFromDatabase;
    private static final String PLAYER_NAME = "Andrew Luck";
    private static final int PLAYER_ESPN_ID = 14874;
    private static final NflTeam PLAYER_NFL_TEAM = NflTeam.COLTS;
    private static final Position PLAYER_POSITION = Position.QUARTERBACK;

    @BeforeClass
    public static void setup() {
        try {
            LoggingUtility.turnLoggingOff();

            PlayerPositionAndTeamProvider playerPositionAndTeamProvider = new PlayerPositionAndTeamProvider(MockWebRequest.getMockWebRequestForPlayerPositionAndTeamProvider());
            playerPositionAndTeamProvider.scrapeForPlayerPositionsAndTeams();
            List<NflPlayerPositionAndTeam> playerPositionAndTeamRows = playerPositionAndTeamProvider.getAllPlayerPositionsAndTeams();
            updater.insert(playerPositionAndTeamRows);

            playerFromDatabase = PlayerFinder.findPlayerByPlayerName(PLAYER_NAME, accessor.getAllPlayerPositionAndTeamObjectsFromTheDatabase());
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Failed to create the PlayerPositionAndTeam table");
        }
    }

    @Test
    public void shouldBeAbleToStoreAndRecallPlayersName(){
        assertEquals(String.format("Unable to find %s by name", PLAYER_NAME), PLAYER_NAME, playerFromDatabase.getName());
    }

    @Test
    public void shouldBeAbleToStoreAndRecallPlayersEspnId(){
        assertEquals(String.format("Unable to find %s by name", PLAYER_NAME), PLAYER_ESPN_ID, playerFromDatabase.getEspnPlayerId());
    }

    @Test
    public void shouldBeAbleToStoreAndRecallPlayersNflTeam(){
        assertEquals(String.format("Unable to find %s by name", PLAYER_NAME), PLAYER_NFL_TEAM, playerFromDatabase.getNflTeam());
    }

    @Test
    public void shouldBeAbleToStoreAndRecallPlayersPositionCorrectly(){
        assertTrue(String.format("Unable to find %s by name", PLAYER_NAME), playerFromDatabase.getPositions().contains(PLAYER_POSITION));
    }
}
