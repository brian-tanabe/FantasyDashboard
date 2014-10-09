package com.briantanabe.fd.du.tests.unit;

import com.briantanabe.fd.dp.providers.EspnLeaguePlayerOwnershipProvider;
import com.briantanabe.fd.dp.providers.NumberFireCurrentWeekProjectionsProvider;
import com.briantanabe.fd.dp.providers.NumberFireRemainingSeasonProjectionsProvider;
import com.briantanabe.fd.dp.providers.PlayerIdProvider;
import com.briantanabe.fd.dp.tests.fixtures.MockWebRequest;
import com.briantanabe.fd.dp.web.auth.TestableCredentialProvider;
import com.briantanabe.fd.du.log.LoggingUtility;
import com.briantanabe.fd.du.updater.DatabaseInterface;
import com.briantanabe.fd.du.updater.DatabaseUpdater;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by btanabe on 10/9/2014.
 */
public class DatabaseUpdaterTests {
    private DatabaseInterface databaseInterface;

    @BeforeClass
    public static void setupTests(){
        LoggingUtility.turnLoggingOff();
    }

    @Before
    public void setup(){
        databaseInterface = DatabaseInterface.getInstance();
    }

    @After
    public void tearDown(){
        databaseInterface.close();
    }


    @Test
    public void shouldBeAbleToCreatePlayerIdTable(){
        try {
            PlayerIdProvider provider = new PlayerIdProvider();
            provider.scrapeForPlayerIds(MockWebRequest.getMockWebRequestForPlayerIdProviderTests());
            DatabaseUpdater.createPlayerIdTable(provider);

            assertEquals("Did not find the correct number of rows in the PlayerId table", provider.getAllPlayersAsArrayList().size(), databaseInterface.getAllNflPlayersFromDatabase().size());
        } catch(Exception ex){
            fail("Unable to create the playerId table");
            ex.printStackTrace();
        }
    }

    @Test
    public void shouldBeAbleToCreateEspnPlayerOwnershipTable(){
        try {
            final int TEST_ESPN_LEAGUE_ID = 84978;
            EspnLeaguePlayerOwnershipProvider provider = new EspnLeaguePlayerOwnershipProvider(MockWebRequest.getMockSecureWebRequestForEspnLeaguePlayerOwnershipProviderTests(TEST_ESPN_LEAGUE_ID));
            provider.login(new TestableCredentialProvider());
            provider.scrapeForOwnershipInfo(TEST_ESPN_LEAGUE_ID);
            DatabaseUpdater.createEspnLeaguePlayerOwnershipTable(provider);

            assertEquals("Did not find the correct number of rows in the EspnLeagueOwnershipInfo table", provider.getPlayerOwnershipInfo().size(), databaseInterface.getAllEspnNflPlayersFromDatabase().size());
        } catch(Exception ex){
            fail("Unable to create the EspnLeaguePlayerOwnership table");
            ex.printStackTrace();
        }
    }

    @Test
    public void shouldBeAbleToCreateNumberFireCurrentWeekProjectionTable(){
        try {
            NumberFireCurrentWeekProjectionsProvider provider = new NumberFireCurrentWeekProjectionsProvider();
            provider.scrapeForNumberFiresCurrentWeekProjections(MockWebRequest.getMockWebRequestForNumberFireCurrentWeekProjectionsProvider());
            DatabaseUpdater.createNumberFireCurrentWeekProjectionsTable(provider);

            assertEquals("Did not find the correct number of rows in the NumberFireCurrentWeekProjections table", provider.getPlayerProjections().size(), databaseInterface.getAllNumberFireCurrentWeekProjectionsFromDatabase().size());
        } catch(Exception ex){
            fail("Unable to create NumberFireCurrentWeekProjection table");
            ex.printStackTrace();
        }
    }

    @Test
    public void shouldBeAbleToCreateNumberFireRemainingSeasonProjectionTable(){
        try {
            NumberFireRemainingSeasonProjectionsProvider provider = new NumberFireRemainingSeasonProjectionsProvider();
            provider.scrapeForNumberFiresRemainingSeasonProjections(MockWebRequest.getMockWebRequestForNumberFireRemainingSeasonProjectionsProvider());
            DatabaseUpdater.createNumberFireRemainingSeasonProjectionsTable(provider);

            assertEquals("Did not find the correct number of rows in the NumberFireRemainingSeasonProjections table", provider.getPlayerProjections().size(), databaseInterface.getAllNumberFireRemainingSeasonProjectionsFromDatabase().size());
        } catch (Exception ex){
            fail("Unable to create the NumberFireCurrentWeekProjection table");
            ex.printStackTrace();
        }
    }
}
