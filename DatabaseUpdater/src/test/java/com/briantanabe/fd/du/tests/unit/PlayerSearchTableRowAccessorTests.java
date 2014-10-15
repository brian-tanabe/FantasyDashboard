package com.briantanabe.fd.du.tests.unit;

import com.briantanabe.fd.dp.fantasy.player.NflPlayerId;
import com.briantanabe.fd.dp.fantasy.player.NflPlayerPositionAndTeam;
import com.briantanabe.fd.dp.fantasy.player.NumberFireCurrentWeekProjection;
import com.briantanabe.fd.dp.fantasy.player.NumberFireRemainingSeasonProjection;
import com.briantanabe.fd.dp.providers.NumberFireCurrentWeekProjectionsProvider;
import com.briantanabe.fd.dp.providers.NumberFireRemainingSeasonProjectionsProvider;
import com.briantanabe.fd.dp.providers.PlayerIdProvider;
import com.briantanabe.fd.dp.providers.PlayerPositionAndTeamProvider;
import com.briantanabe.fd.dp.tests.fixtures.MockWebRequest;
import com.briantanabe.fd.du.datastore.DatabaseAccessor;
import com.briantanabe.fd.du.datastore.DatabaseUpdater;
import com.briantanabe.fd.du.log.LoggingUtility;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by BTanabe on 10/15/2014.
 */
public class PlayerSearchTableRowAccessorTests {
    private static DatabaseAccessor accessor = new DatabaseAccessor();
    private static DatabaseUpdater updater = new DatabaseUpdater();

    @BeforeClass
    public static void setup(){
        try {
            LoggingUtility.turnLoggingOff();

            PlayerIdProvider playerIdProvider = new PlayerIdProvider();
            playerIdProvider.scrapeForPlayerIds(MockWebRequest.getMockWebRequestForPlayerIdProviderTests());
            List<NflPlayerId> playerIdRows = playerIdProvider.getAllPlayersAsArrayList();
            updater.insert(playerIdRows);

            NumberFireCurrentWeekProjectionsProvider numberFireCurrentWeekProjectionsProvider = new NumberFireCurrentWeekProjectionsProvider();
            numberFireCurrentWeekProjectionsProvider.scrapeForNumberFiresCurrentWeekProjections(MockWebRequest.getMockWebRequestForNumberFireCurrentWeekProjectionsProvider());
            List<NumberFireCurrentWeekProjection> numberFireCurrentWeekProjectionRows = numberFireCurrentWeekProjectionsProvider.getPlayerProjections();
            updater.insert(numberFireCurrentWeekProjectionRows);

            NumberFireRemainingSeasonProjectionsProvider numberFireRemainingSeasonProjectionsProvider = new NumberFireRemainingSeasonProjectionsProvider();
            numberFireRemainingSeasonProjectionsProvider.scrapeForNumberFiresRemainingSeasonProjections(MockWebRequest.getMockWebRequestForNumberFireRemainingSeasonProjectionsProvider());
            List<NumberFireRemainingSeasonProjection> numberFireRemainingSeasonProjectionsRows = numberFireRemainingSeasonProjectionsProvider.getPlayerProjections();
            updater.insert(numberFireRemainingSeasonProjectionsRows);

            PlayerPositionAndTeamProvider playerPositionAndTeamProvider = new PlayerPositionAndTeamProvider(MockWebRequest.getMockWebRequestForPlayerPositionAndTeamProvider());
            playerPositionAndTeamProvider.scrapeForPlayerPositionsAndTeams();
            List<NflPlayerPositionAndTeam> playerPositionAndTeamRows = playerPositionAndTeamProvider.getAllPlayerPositionsAndTeams();
            updater.insert(playerPositionAndTeamRows);
        } catch (Exception ex){
            fail("Failed to create the database environment");
            ex.printStackTrace();
        }
    }

    @Test
    public void shouldBeAbleToRecallAllPlayerSearchTableRows(){
        assertEquals("Did not find the correct number of players", 1535, accessor.getAllPlayerSearchTableRowsFromDatabase().size());
    }
}
