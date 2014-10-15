package com.briantanabe.fd.du.tests.unit;

import com.briantanabe.fd.dp.fantasy.player.NflPlayerPositionAndTeam;
import com.briantanabe.fd.dp.providers.*;
import com.briantanabe.fd.dp.tests.fixtures.MockWebRequest;
import com.briantanabe.fd.dp.web.auth.TestableCredentialProvider;
import com.briantanabe.fd.du.datastore.DatabaseAccessor;
import com.briantanabe.fd.du.datastore.DatabaseUpdater;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by btanabe on 10/9/2014.
 */
public class DatabaseUpdaterTests {
    private DatabaseUpdater updater = new DatabaseUpdater();
    private DatabaseAccessor accessor = new DatabaseAccessor();

    @BeforeClass
    public static void setupTests() {
//        LoggingUtility.turnLoggingOff();
    }

    @Test
    public void shouldBeAbleToCreatePlayerIdTable() {
        try {
            PlayerIdProvider provider = new PlayerIdProvider();
            provider.scrapeForPlayerIds(MockWebRequest.getMockWebRequestForPlayerIdProviderTests());
            updater.insert(provider.getAllPlayersAsArrayList());

            assertEquals("Did not find the correct number of rows in the PlayerId table", provider.getAllPlayersAsArrayList().size(), accessor.getAllNflPlayersFromThePlayerIdTable().size());
        } catch (Exception ex) {
            fail("Unable to create the playerId table");
            ex.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void shouldBeAbleToCreateEspnPlayerOwnershipTable() {
        try {
            final int TEST_ESPN_LEAGUE_ID = 84978;
            EspnLeaguePlayerOwnershipProvider provider = new EspnLeaguePlayerOwnershipProvider(MockWebRequest.getMockSecureWebRequestForEspnLeaguePlayerOwnershipProviderTests(TEST_ESPN_LEAGUE_ID));
            provider.login(new TestableCredentialProvider());
            provider.scrapeForOwnershipInfo(TEST_ESPN_LEAGUE_ID);
            updater.insert(provider.getPlayerOwnershipInfo());

            assertEquals("Did not find the correct number of rows in the EspnLeagueOwnershipInfo table", provider.getPlayerOwnershipInfo().size(), accessor.getAllEspnPlayerOwnershipInfosFromTheEspnPlayerOwnershipTable().size());
        } catch (Exception ex) {
            fail("Unable to create the EspnLeaguePlayerOwnership table");
            ex.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void shouldBeAbleToCreateNumberFireCurrentWeekProjectionTable() {
        try {
            NumberFireCurrentWeekProjectionsProvider provider = new NumberFireCurrentWeekProjectionsProvider();
            provider.scrapeForNumberFiresCurrentWeekProjections(MockWebRequest.getMockWebRequestForNumberFireCurrentWeekProjectionsProvider());
            updater.insert(provider.getPlayerProjections());

            assertEquals("Did not find the correct number of rows in the NumberFireCurrentWeekProjections table", provider.getPlayerProjections().size(), accessor.getAllNumberFireCurrentWeekProjectionsFromTheDatabase().size());
        } catch (Exception ex) {
            fail("Unable to create NumberFireCurrentWeekProjection table");
            ex.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void shouldBeAbleToCreateNumberFireRemainingSeasonProjectionTable() {
        try {
            NumberFireRemainingSeasonProjectionsProvider provider = new NumberFireRemainingSeasonProjectionsProvider();
            provider.scrapeForNumberFiresRemainingSeasonProjections(MockWebRequest.getMockWebRequestForNumberFireRemainingSeasonProjectionsProvider());
            updater.insert(provider.getPlayerProjections());

            assertEquals("Did not find the correct number of rows in the NumberFireRemainingSeasonProjections table", provider.getPlayerProjections().size(), accessor.getAllNumberFireRemainingWeekProjectionsFromTheDatabase().size());
        } catch (Exception ex) {
            fail("Unable to create the NumberFireCurrentWeekProjection table");
            ex.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void shouldBeAbleToCreateThePlayerPositionAndTeamTable(){
        try {
            PlayerPositionAndTeamProvider provider = new PlayerPositionAndTeamProvider(MockWebRequest.getMockWebRequestForPlayerPositionAndTeamProvider());
            provider.scrapeForPlayerPositionsAndTeams();
            List<NflPlayerPositionAndTeam> playersToAdd = provider.getAllPlayerPositionsAndTeams();
            updater.insert(playersToAdd);

            List<NflPlayerPositionAndTeam> playersFromDatabase = accessor.getAllPlayerPositionAndTeamObjectsFromTheDatabase();

            assertEquals("Did not find the correct number of player rows in the PlayerPositionAndTeam table", playersToAdd.size(), accessor.getAllPlayerPositionAndTeamObjectsFromTheDatabase().size());
        } catch (Exception ex){
            fail("Unable to create the PlayerPositionAndTeam table");
            ex.printStackTrace();
        }
    }
}
