package com.briantanabe.fd.du.tests.unit;

import com.briantanabe.fd.dp.fantasy.player.NflPlayer;
import com.briantanabe.fd.dp.providers.EspnLeaguePlayerOwnershipProvider;
import com.briantanabe.fd.dp.providers.NumberFireCurrentWeekProjectionsProvider;
import com.briantanabe.fd.dp.providers.NumberFireRemainingSeasonProjectionsProvider;
import com.briantanabe.fd.dp.providers.PlayerIdProvider;
import com.briantanabe.fd.dp.tests.fixtures.MockWebRequest;
import com.briantanabe.fd.dp.web.auth.TestableCredentialProvider;
import com.briantanabe.fd.du.log.LoggingUtility;
import com.briantanabe.fd.du.updater.DatabaseAccessor;
import com.briantanabe.fd.du.updater.DatabaseInterface;
import com.briantanabe.fd.du.updater.DatabaseUpdater;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by btanabe on 10/9/2014.
 */
public class DatabaseAccessorTests {
    final static int TEST_ESPN_LEAGUE_ID = 84978;

    private static DatabaseAccessor databaseAccessor;

    @BeforeClass
    public static void createTestClass(){
        LoggingUtility.turnLoggingOff();

        try {
            DatabaseInterface databaseInterface = DatabaseInterface.getInstance();

            PlayerIdProvider playerIdProvider = new PlayerIdProvider();
            playerIdProvider.scrapeForPlayerIds(MockWebRequest.getMockWebRequestForPlayerIdProviderTests());
            DatabaseUpdater.createPlayerIdTable(playerIdProvider);

            EspnLeaguePlayerOwnershipProvider espnLeaguePlayerOwnershipProvider = new EspnLeaguePlayerOwnershipProvider(MockWebRequest.getMockSecureWebRequestForEspnLeaguePlayerOwnershipProviderTests(TEST_ESPN_LEAGUE_ID));
            espnLeaguePlayerOwnershipProvider.login(new TestableCredentialProvider());
            espnLeaguePlayerOwnershipProvider.scrapeForOwnershipInfo(TEST_ESPN_LEAGUE_ID);
            DatabaseUpdater.createEspnLeaguePlayerOwnershipTable(espnLeaguePlayerOwnershipProvider);

            NumberFireCurrentWeekProjectionsProvider numberFireCurrentWeekProjectionsProvider = new NumberFireCurrentWeekProjectionsProvider();
            numberFireCurrentWeekProjectionsProvider.scrapeForNumberFiresCurrentWeekProjections(MockWebRequest.getMockWebRequestForNumberFireCurrentWeekProjectionsProvider());
            DatabaseUpdater.createNumberFireCurrentWeekProjectionsTable(numberFireCurrentWeekProjectionsProvider);

            NumberFireRemainingSeasonProjectionsProvider numberFireRemainingSeasonProjectionsProvider = new NumberFireRemainingSeasonProjectionsProvider();
            numberFireRemainingSeasonProjectionsProvider.scrapeForNumberFiresRemainingSeasonProjections(MockWebRequest.getMockWebRequestForNumberFireRemainingSeasonProjectionsProvider());
            DatabaseUpdater.createNumberFireRemainingSeasonProjectionsTable(numberFireRemainingSeasonProjectionsProvider);

//            databaseInterface.close();
        } catch (Exception ex){
            fail("Failed to create test database environment");
            ex.printStackTrace();
        }
    }

    @Test
    public void shouldHaveCreatedTablesWithTheCorrectNumberOfRows(){
        assertEquals("The PlayerId table has an incorrect number of players", 549, databaseAccessor.getAllNflPlayersFromThePlayerIdTable().size());
        assertEquals("The EspnPlayerOwnershipTable has an incorrect number of rows", 1535, databaseAccessor.getAllEspnPlayerOwnershipInfosFromTheEspnPlayerOwnershipTable().size());
        assertEquals("The NumberFireCurrentWeekProjections table has an incorrect number of rows", 543, databaseAccessor.getAllNumberFireCurrentWeekProjectionsFromTheDatabase().size());
        assertEquals("The NumberFireRemainingSeasonProjections table has an incorrect number of rows", 549, databaseAccessor.getAllNumberFireRemainingWeekProjectionsFromTheDatabase().size());
    }

    @Test
    public void shouldBeAbleToFindANflPlayerObjectByTheirEspnPlayerId(){
        NflPlayer playerFromDatabase = databaseAccessor.getNflPlayerFromThePlayerIdTableByTheirEspnPlayerId(1428);
//        assertEquals(0, databaseInterface.getAllNflPlayersFromDatabase().size());
//        assertEquals("Was not able to find Peyton Manning by his ESPN player ID", playerFromDatabase.getEspnPlayerId(), 1428);

    }
}
