package com.briantanabe.fd.unitTests.providers;

import com.briantanabe.fd.fantasy.player.NflPlayer;
import com.briantanabe.fd.fixtures.MockWebRequest;
import com.briantanabe.fd.fixtures.NumberFireRemainingSeasonProjectionFixture;
import com.briantanabe.fd.providers.PlayerIdProvider;
import com.briantanabe.fd.web.WebRequest;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.briantanabe.fd.unitTests.scrapers.PlayerFinder.findPlayerByPlayerName;
import static com.briantanabe.fd.web.WebPage.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Brian on 9/28/14.
 */
public class PlayerIdProviderTests {
    private static ArrayList<NflPlayer> players;

    @BeforeClass
    public static void setup(){
        try {
            PlayerIdProvider idProvider = new PlayerIdProvider();
            idProvider.scrapeForPlayerIds(getTestableWebRequest());
            players = idProvider.getAllPlayersAsArrayList();
        } catch (Exception ex){
            ex.printStackTrace();
            fail("Failed to reach numberFire.com's projection page");
        }
    }

    private static WebRequest getTestableWebRequest() throws IOException {
        Map<String, Document> urlToDocumentMap = new LinkedHashMap<String, Document>();
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_QUARTERBACK_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonQuarterbackProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_RUNNING_BACK_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonRunningBackProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_WIDE_RECEIVER_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonWideReceiverProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_TIGHT_END_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonTightEndProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_KICKER_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonKickerProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_DEFENSE_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonDefenseProjectionDocument());

        return MockWebRequest.getMockWebRequest(urlToDocumentMap);
    }

    @Test
    public void testCanFindTheCorrectNumberOfPlayers(){
        assertEquals("Failed to find the correct number of players", 549, players.size());
    }

    @Test
    public void testCanFindDenverDefense(){
        final String playerName = "Denver D/ST";
        NflPlayer player = findPlayerByPlayerName(playerName, players);

        assertEquals(String.format("Failed to find %s", playerName), playerName, player.getName());
        assertEquals(String.format("%s's numberFire player ID is incorrect", playerName), 2901, player.getNumberFireId());
        assertEquals(String.format("%s's ESPN player ID is incorrect", playerName), 60007, player.getEspnPlayerId());
    }

    @Test
    public void testCanFindPeytonManning(){
        final String playerName = "Peyton Manning";
        NflPlayer player = findPlayerByPlayerName(playerName, players);

        assertEquals(String.format("Failed to find %s", playerName), playerName, player.getName());
        assertEquals(String.format("%s's numberFire player ID is incorrect", playerName), 3, player.getNumberFireId());
        assertEquals(String.format("%s's ESPN player ID is incorrect", playerName), 1428, player.getEspnPlayerId());
    }

    @Test
    public void testCanFindMonteeBall(){
        final String playerName = "Montee Ball";
        NflPlayer player = findPlayerByPlayerName(playerName, players);

        assertEquals(String.format("Failed to find %s", playerName), playerName, player.getName());
        assertEquals(String.format("%s's numberFire player ID is incorrect", playerName), 52099, player.getNumberFireId());
        assertEquals(String.format("%s's ESPN player ID is incorrect", playerName), 15823, player.getEspnPlayerId());
    }

    @Test
    public void testCanFindJayCutler(){
        final String playerName = "Jay Cutler";
        NflPlayer player = findPlayerByPlayerName(playerName, players);

        assertEquals(String.format("Failed to find %s", playerName), playerName, player.getName());
        assertEquals(String.format("%s's numberFire player ID is incorrect", playerName), 37, player.getNumberFireId());
        assertEquals(String.format("%s's ESPN player ID is incorrect", playerName), 9597, player.getEspnPlayerId());
    }

    @Test
    public void testCanFindMichaelCrabtree(){
        final String playerName = "Michael Crabtree";
        NflPlayer player = findPlayerByPlayerName(playerName, players);

        assertEquals(String.format("Failed to find %s", playerName), playerName, player.getName());
        assertEquals(String.format("%s's numberFire player ID is incorrect", playerName), 606, player.getNumberFireId());
        assertEquals(String.format("%s's ESPN player ID is incorrect", playerName), 12563, player.getEspnPlayerId());
    }

    @Test
    public void testCanFindJuliusThomas(){
        final String playerName = "Julius Thomas";
        NflPlayer player = findPlayerByPlayerName(playerName, players);

        assertEquals(String.format("Failed to find %s", playerName), playerName, player.getName());
        assertEquals(String.format("%s's numberFire player ID is incorrect", playerName), 4353, player.getNumberFireId());
        assertEquals(String.format("%s's ESPN player ID is incorrect", playerName), 14204, player.getEspnPlayerId());
    }

    @Test
    public void testCanFindRobbieGould(){
        final String playerName = "Robbie Gould";
        NflPlayer player = findPlayerByPlayerName(playerName, players);

        assertEquals(String.format("Failed to find %s", playerName), playerName, player.getName());
        assertEquals(String.format("%s's numberFire player ID is incorrect", playerName), 2820, player.getNumberFireId());
        assertEquals(String.format("%s's ESPN player ID is incorrect", playerName), 9354, player.getEspnPlayerId());
    }
}
