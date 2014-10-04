package com.briantanabe.fd.dp.tests.unit.providers;

import com.briantanabe.fd.dp.fantasy.player.NflPlayer;
import com.briantanabe.fd.dp.tests.fixtures.MockWebRequest;
import com.briantanabe.fd.dp.providers.PlayerIdProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.briantanabe.fd.dp.tests.unit.scrapers.PlayerFinder.findPlayerByPlayerName;
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
            idProvider.scrapeForPlayerIds(MockWebRequest.getMockWebRequestForPlayerIdProviderTests());
            players = idProvider.getAllPlayersAsArrayList();
        } catch (Exception ex){
            ex.printStackTrace();
            fail("Failed to reach numberFire.com's projection page");
        }
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
