package com.briantanabe.fd.unitTests.providers;

import com.briantanabe.fd.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.fixtures.MockWebRequest;
import com.briantanabe.fd.providers.EspnLeaguePlayerOwnershipProvider;
import com.briantanabe.fd.web.SecureWebRequest;
import com.briantanabe.fd.web.auth.TestableCredentialProvider;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.briantanabe.fd.fixtures.FileDocumentor.getDocumentFromFileHtml;
import static com.briantanabe.fd.unitTests.scrapers.PlayerFinder.findPlayerByPlayerName;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Brian on 9/28/14.
 */
public class EspnLeaguePlayerOwnershipProviderTests {
    private static final int TEST_ESPN_LEAGUE_ID = 84978;

    private static ArrayList<EspnNflPlayer> players;

    @BeforeClass
    public static void setup(){
        try {
            EspnLeaguePlayerOwnershipProvider provider = new EspnLeaguePlayerOwnershipProvider(setupMockWebRequest());
            provider.login(new TestableCredentialProvider());
            provider.scrapeForOwnershipInfo(TEST_ESPN_LEAGUE_ID);
            players = provider.getPlayerOwnershipInfo();
        } catch(Exception ex){
            fail("Failed to scrape for the ESPN league ownership info");
        }
    }

    private static SecureWebRequest setupMockWebRequest() throws IOException {
        Map<String, Document> urlToDocumentMap = new LinkedHashMap<String, Document>();
        for(int index = 1; index < 40; index++){
            String requestUrl = String.format("http://games.espn.go.com/ffl/tools/projections?&leagueId=%d&seasonTotals=true&seasonId=2014&startIndex=%d", TEST_ESPN_LEAGUE_ID, (index - 1) * 40);
            String htmlDocumentPath = String.format("./DataProvider/src/test/resources/WebPages/espnProjectionsPage/espnPlayersSeasonProjectionPage%d.html", index);
            Document testPage = getDocumentFromFileHtml(htmlDocumentPath);
            urlToDocumentMap.put(requestUrl, testPage);
        }

        return MockWebRequest.getMockSecureWebRequest(urlToDocumentMap);
    }

    @Test
    public void shouldBeAbleToFindSevenHundredPlayers(){
        assertEquals("Did not find the proper number of players", 1535, players.size());
    }

    @Test
    public void shouldBeAbleToFindTheLastPlayerListed(){
        final String playerName = "Nic Jacobs";
        final int espnPlayerId = 17356;
        final int owner = -1;

        EspnNflPlayer player = findPlayerByPlayerName(playerName, players);
        assertNotNull(player);
        assertEquals(String.format("Did not scrape %s's ESPN player ID correctly", player.getName()), espnPlayerId, player.getEspnPlayerId());
        assertEquals(String.format("Did not scrape %s's owner info correctly", player.getName()), owner, player.getOwner());
    }

    @Test
    public void shouldBeAbleToFindPeytonManningWhoIsOwned(){
        final String playerName = "Peyton Manning";
        final int espnPlayerId = 1428;
        final int owner = 1;

        EspnNflPlayer player = findPlayerByPlayerName(playerName, players);
        assertNotNull(player);
        assertEquals(String.format("Did not scrape %s's ESPN player ID correctly", player.getName()), espnPlayerId, player.getEspnPlayerId());
        assertEquals(String.format("Did not scrape %s's owner info correctly", player.getName()), owner, player.getOwner());
    }

    @Test
    public void shouldBeAbleToFindPlayersForAllTeams(){
        assertEquals("Peyton Manning should be owned by team 1", 1, findPlayerByPlayerName("Peyton Manning", players).getOwner());
        assertEquals("Philip Rivers should be owned by team 2", 2, findPlayerByPlayerName("Philip Rivers", players).getOwner());
        assertEquals("Julio Jones should be owned by team 3", 3, findPlayerByPlayerName("Julio Jones", players).getOwner());
        assertEquals("Jamaal Charles should be owned by team 4", 4, findPlayerByPlayerName("Jamaal Charles", players).getOwner());
        assertEquals("LeSean McCoy should be owned by team 5", 5, findPlayerByPlayerName("LeSean McCoy", players).getOwner());
        assertEquals("Matt Forte should be owned by team 6", 6, findPlayerByPlayerName("Matt Forte", players).getOwner());
        assertEquals("Adrian Peterson should be owned by team 7", 7, findPlayerByPlayerName("Adrian Peterson", players).getOwner());
        assertEquals("Martellus Bennett should be owned by team 8", 8, findPlayerByPlayerName("Martellus Bennett", players).getOwner());
        assertEquals("Andrew Luck should be owned by team 9", 9, findPlayerByPlayerName("Andrew Luck", players).getOwner());
        assertEquals("Phil Dawson should be owned by team 10", 10, findPlayerByPlayerName("Phil Dawson", players).getOwner());
        assertEquals("Reggie Bush should be owned by team 12", 12, findPlayerByPlayerName("Reggie Bush", players).getOwner());
        assertEquals("Calvin Johnson should be owned by team 13", 13, findPlayerByPlayerName("Calvin Johnson", players).getOwner());
        assertEquals("Kyle Orton shouldn't be owned", -1, findPlayerByPlayerName("Kyle Orton", players).getOwner());
    }
}
