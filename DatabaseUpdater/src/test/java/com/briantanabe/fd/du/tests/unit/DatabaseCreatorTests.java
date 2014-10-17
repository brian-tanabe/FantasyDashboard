package com.briantanabe.fd.du.tests.unit;

import com.briantanabe.fd.dp.providers.PlayerIdProvider;
import com.briantanabe.fd.dp.tests.fixtures.MockWebRequest;
import com.briantanabe.fd.du.datastore.DatabaseCreator;
import com.briantanabe.fd.du.log.LoggingUtility;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by Brian on 10/16/2014.
 */
public class DatabaseCreatorTests {

    @BeforeClass
    public static void setup(){
        LoggingUtility.turnLoggingOff();
    }

    @Test
    public void shouldBeAbleToCreatePlayerIdTable(){
        try {
            PlayerIdProvider provider = new PlayerIdProvider();
            provider.scrapeForPlayerIds(MockWebRequest.getMockWebRequestForPlayerIdProviderTests());
            DatabaseCreator.insertAllPlayers(provider.getAllPlayersAsArrayList());

            assertEquals("Did not find the correct number of rows in the PlayerId table", provider.getAllPlayersAsArrayList().size(), 0);
        } catch (Exception ex) {
            fail("Unable to create the playerId table");
            ex.printStackTrace();
        }
    }
}
