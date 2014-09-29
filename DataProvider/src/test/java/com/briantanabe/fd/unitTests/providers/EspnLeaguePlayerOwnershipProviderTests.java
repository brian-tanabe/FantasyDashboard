package com.briantanabe.fd.unitTests.providers;

import com.briantanabe.fd.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.providers.EspnLeaguePlayerOwnershipProvider;
import com.briantanabe.fd.web.SecureWebRequest;
import com.briantanabe.fd.web.auth.EspnCredentialProvider;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.briantanabe.fd.fixtures.FileDocumentor.getDocumentFromFileHtml;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Brian on 9/28/14.
 */
public class EspnLeaguePlayerOwnershipProviderTests {
    private static final int TEST_ESPN_LEAGUE_ID = 84978;

    private static ArrayList<EspnNflPlayer> players;

    @BeforeClass
    public static void setup(){
        try {
//            EspnLeaguePlayerOwnershipProvider provider = new EspnLeaguePlayerOwnershipProvider(setupMockWebRequestCalls());
//            provider.login(null);
//            provider.scrapeForOwnershipInfo(TEST_ESPN_LEAGUE_ID);
//            players = provider.getPlayerOwnershipInfo();

            EspnLeaguePlayerOwnershipProvider provider = new EspnLeaguePlayerOwnershipProvider(new SecureWebRequest());
            provider.login(new EspnCredentialProvider());
            provider.scrapeForOwnershipInfo(TEST_ESPN_LEAGUE_ID);
            players = provider.getPlayerOwnershipInfo();

        } catch(Exception ex){
            fail("Failed to scrape for the ESPN league ownership info");
        }
    }

    private static SecureWebRequest setupMockWebRequestCalls() throws IOException {
        SecureWebRequest webRequest = mock(SecureWebRequest.class);

        when(webRequest.login(null)).thenReturn(webRequest);

        for(int index = 1; index < 40; index++){
            String requestUrl = String.format("http://games.espn.go.com/ffl/tools/projections?%sleagueId=%d&seasonTotals=true&seasonId=2014&startIndex=%d", "", TEST_ESPN_LEAGUE_ID, (index - 1) * 40);
            String requestUrlWithAmpersand = String.format("http://games.espn.go.com/ffl/tools/projections?%sleagueId=%d&seasonTotals=true&seasonId=2014&startIndex=%d", "&", TEST_ESPN_LEAGUE_ID, (index - 1) * 40);
            String htmlDocumentPath = String.format("./DataProvider/src/test/resources/WebPages/espnProjectionsPage/espnPlayersSeasonProjectionPage%d.html", index);
            Document testPage = getDocumentFromFileHtml(htmlDocumentPath);

            when(webRequest.getPageAsDocument(requestUrl)).thenReturn(testPage);
            when(webRequest.getPageAsDocument(requestUrlWithAmpersand)).thenReturn(testPage);
        }

        return webRequest;
    }

    @Test
    public void shouldBeAbleToFindSevenHundredPlayers(){
        assertEquals("Did not find the proper number of players", 1535, players.size());
    }
}
