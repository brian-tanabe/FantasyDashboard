package com.briantanabe.fd.du.unitTests;

import com.briantanabe.fd.fixtures.MockWebRequest;
import com.briantanabe.fd.providers.EspnLeaguePlayerOwnershipProvider;
import com.briantanabe.fd.providers.NumberFireCurrentWeekProjectionsProvider;
import com.briantanabe.fd.providers.NumberFireRemainingSeasonProjectionsProvider;
import com.briantanabe.fd.providers.PlayerIdProvider;
import com.briantanabe.fd.web.auth.TestableCredentialProvider;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Created by BTanabe on 9/30/2014.
 */
public class DataAccessTests {

    @Test
    public void shouldBeAbleToUseEspnLeaguePlayerOwnershipProvider(){
        try {
            final int TEST_ESPN_LEAGUE_ID = 84978;
            EspnLeaguePlayerOwnershipProvider provider = new EspnLeaguePlayerOwnershipProvider(MockWebRequest.getMockSecureWebRequestForEspnLeaguePlayerOwnershipProviderTests(TEST_ESPN_LEAGUE_ID));
            provider.login(new TestableCredentialProvider());
            provider.scrapeForOwnershipInfo(TEST_ESPN_LEAGUE_ID);
            assertEquals("Was not able to use EspnLeaguePlayerOwnershipProvider", 1535, provider.getPlayerOwnershipInfo().size());
        } catch(Exception ex){
            ex.printStackTrace();
            fail("Failed to grab EspnLeaguePlayerOwnershipProvider's data");
        }
    }

    @Test
    public void shouldBeAbleToUseNumberFireCurrentWeekProjectionsProvider(){
        try {
            NumberFireCurrentWeekProjectionsProvider provider = new NumberFireCurrentWeekProjectionsProvider();
            provider.scrapeForNumberFiresCurrentWeekProjections(MockWebRequest.getMockWebRequestForNumberFireCurrentWeekProjectionsProvider());
            assertEquals("Was not able to use NumberFireCurrentSeasonProjectionsProvider", 543, provider.getPlayerProjections().size());
        } catch (Exception ex){
            ex.printStackTrace();
            fail("Failed to grab NumberFireCurrentWeekProjectionsProvider's data");
        }
    }

    @Test
    public void shouldBeAbleToUseNumberFireRemainingSeasonProjectionsProvider(){
        try {
            NumberFireRemainingSeasonProjectionsProvider provider = new NumberFireRemainingSeasonProjectionsProvider();
            provider.scrapeForNumberFiresRemainingSeasonProjections(MockWebRequest.getMockWebRequestForNumberFireRemainingSeasonProjectionsProvider());
            assertEquals("Was not able to use NumberFireRemainingSeasonProjectionsProvider", 549, provider.getPlayerProjections().size());
        } catch(Exception ex){
            ex.printStackTrace();
            fail("Failed to grab NumberFireRemainingSeasonProjectionsProvider's data");
        }
    }

    @Test
    public void shouldBeAbleToUseThePlayerIdProvider(){
        try {
            PlayerIdProvider provider = new PlayerIdProvider();
            provider.scrapeForPlayerIds(MockWebRequest.getMockWebRequestForPlayerIdProviderTests());
            assertEquals("Was not able to use PlayerIdProvider", 549, provider.getAllPlayersAsArrayList().size());
        } catch(Exception ex){
            ex.printStackTrace();
            fail("Failed to grab PlayerIdProvider's data");
        }
    }
}
