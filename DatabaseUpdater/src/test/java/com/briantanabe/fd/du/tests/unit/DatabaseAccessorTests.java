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

import javax.transaction.Transactional;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by btanabe on 10/9/2014.
 */
public class DatabaseAccessorTests {
    final static int TEST_ESPN_LEAGUE_ID = 84978;

    private static DatabaseAccessor accessor = new DatabaseAccessor();
    private static DatabaseUpdater updater = new DatabaseUpdater();


    @BeforeClass
    @Transactional
    public static void createTestClass(){
        LoggingUtility.turnLoggingOff();

        try {
            PlayerIdProvider playerIdProvider = new PlayerIdProvider();
            playerIdProvider.scrapeForPlayerIds(MockWebRequest.getMockWebRequestForPlayerIdProviderTests());
            updater.insert(playerIdProvider.getAllPlayersAsArrayList());

            EspnLeaguePlayerOwnershipProvider espnLeaguePlayerOwnershipProvider = new EspnLeaguePlayerOwnershipProvider(MockWebRequest.getMockSecureWebRequestForEspnLeaguePlayerOwnershipProviderTests(TEST_ESPN_LEAGUE_ID));
            espnLeaguePlayerOwnershipProvider.login(new TestableCredentialProvider());
            espnLeaguePlayerOwnershipProvider.scrapeForOwnershipInfo(TEST_ESPN_LEAGUE_ID);
            updater.insert(espnLeaguePlayerOwnershipProvider.getPlayerOwnershipInfo());

            NumberFireCurrentWeekProjectionsProvider numberFireCurrentWeekProjectionsProvider = new NumberFireCurrentWeekProjectionsProvider();
            numberFireCurrentWeekProjectionsProvider.scrapeForNumberFiresCurrentWeekProjections(MockWebRequest.getMockWebRequestForNumberFireCurrentWeekProjectionsProvider());
            updater.insert(numberFireCurrentWeekProjectionsProvider.getPlayerProjections());

            NumberFireRemainingSeasonProjectionsProvider numberFireRemainingSeasonProjectionsProvider = new NumberFireRemainingSeasonProjectionsProvider();
            numberFireRemainingSeasonProjectionsProvider.scrapeForNumberFiresRemainingSeasonProjections(MockWebRequest.getMockWebRequestForNumberFireRemainingSeasonProjectionsProvider());
            updater.insert(numberFireRemainingSeasonProjectionsProvider.getPlayerProjections());

        } catch (Exception ex){
            fail("Failed to create test database environment");
            ex.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void shouldHaveCreatedTablesWithTheCorrectNumberOfRows(){
        assertEquals("The EspnPlayerOwnershipTable has an incorrect number of rows", 1535, accessor.getAllEspnPlayerOwnershipInfosFromTheEspnPlayerOwnershipTable().size());
        assertEquals("The NumberFireCurrentWeekProjections table has an incorrect number of rows", 543, accessor.getAllNumberFireCurrentWeekProjectionsFromTheDatabase().size());
        assertEquals("The NumberFireRemainingSeasonProjections table has an incorrect number of rows", 549, accessor.getAllNumberFireRemainingWeekProjectionsFromTheDatabase().size());
        assertEquals("The PlayerId table has an incorrect number of players", 549, accessor.getAllNflPlayersFromThePlayerIdTable().size());
    }

    @Test
    @Transactional
    public void shouldBeAbleToFindANflPlayerObjectByTheirEspnPlayerId(){
        NflPlayer playerFromDatabase = accessor.getNflPlayerFromThePlayerIdTableByTheirEspnPlayerId(1428);
//        assertEquals(0, databaseInterface.getAllNflPlayersFromDatabase().size());
//        assertEquals("Was not able to find Peyton Manning by his ESPN player ID", playerFromDatabase.getEspnPlayerId(), 1428);

    }
}
