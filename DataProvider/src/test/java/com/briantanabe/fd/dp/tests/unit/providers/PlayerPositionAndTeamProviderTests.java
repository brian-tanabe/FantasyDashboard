package com.briantanabe.fd.dp.tests.unit.providers;

import com.briantanabe.fd.dp.fantasy.player.NflPlayerPositionAndTeam;
import com.briantanabe.fd.dp.nfl.position.Position;
import com.briantanabe.fd.dp.nfl.team.NflTeam;
import com.briantanabe.fd.dp.providers.PlayerPositionAndTeamProvider;
import com.briantanabe.fd.dp.tests.fixtures.MockWebRequest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.briantanabe.fd.dp.tests.unit.scrapers.PlayerFinder.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by btanabe on 10/13/2014.
 */
public class PlayerPositionAndTeamProviderTests {
    private static PlayerPositionAndTeamProvider dataProvider;

    @BeforeClass
    public static void setup(){
        try {
            dataProvider = new PlayerPositionAndTeamProvider(MockWebRequest.getMockWebRequestForPlayerPositionAndTeamProvider());
            dataProvider.scrapeForPlayerPositionsAndTeams();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Failed to scrape and provide player position and team data");
        }
    }

    @Test
    public void shouldBeAbleToFindOneThousandFourHundredNinetyFivePlayers(){
        assertEquals("Did not find the proper number of players", 1495, dataProvider.getAllPlayerPositionsAndTeams().size());
    }

    @Test
    public void shouldBeAbleToProperlyParseQuarterbacks(){
        assertEquals("Did not find the proper number of quarterbacks", 193, findAllPlayersByPosition(Position.QUARTERBACK, dataProvider.getAllPlayerPositionsAndTeams()).size());
    }

    @Test
    public void shouldBeAbleToProperlyParseRunningBacks(){
        assertEquals("Did not find the proper number of running backs", 390, findAllPlayersByPosition(Position.RUNNING_BACK, dataProvider.getAllPlayerPositionsAndTeams()).size());
    }

    @Test
    public void shouldBeAbleToProperlyParseWideReceivers(){
        assertEquals("Did not find the proper number of wide receivers", 518, findAllPlayersByPosition(Position.WIDE_RECEIVER, dataProvider.getAllPlayerPositionsAndTeams()).size());
    }

    @Test
    public void shouldBeAbleToProperlyParseTightEnds(){
        assertEquals("Did not find the proper number of tight ends", 276, findAllPlayersByPosition(Position.TIGHT_END, dataProvider.getAllPlayerPositionsAndTeams()).size());
    }

    @Test
    public void shouldBeAbleToProperlyParseKickers(){
        assertEquals("Did not find the proper number of kickers", 88, findAllPlayersByPosition(Position.KICKER, dataProvider.getAllPlayerPositionsAndTeams()).size());
    }

    @Test
    public void shouldBeAbleToProperlyParseDefenses(){
        assertEquals("Did not find the proper number of defenses", 32, findAllPlayersByPosition(Position.DEFENSE, dataProvider.getAllPlayerPositionsAndTeams()).size());
    }

    @Test
    public void shouldBeAbleToProperlyParsePlayersWithMultiplePositions(){
        ArrayList<NflPlayerPositionAndTeam> superAthletes = new ArrayList<>();
        for(NflPlayerPositionAndTeam player : dataProvider.getAllPlayerPositionsAndTeams()){
            if(player.getAllEligiblePositions().size() > 1){
                superAthletes.add(player);
            }
        }

        assertEquals("Did not find the right number of players with multiple positions", 2, superAthletes.size());
    }

    @Test
    public void shouldBeAbleToProperlyParseFreeAgents(){
        assertEquals("Did not find the proper number of free agents", 550, findAllPlayersByNflTeam(NflTeam.UNKNOWN, dataProvider.getAllPlayerPositionsAndTeams()).size());
    }

    @Test
    public void shouldBeABleToProperlyParseNflTeams(){
        assertEquals("Did not find players from all 32 teams", 32, findAllUniqueNflTeamsInListOfPlayers(dataProvider.getAllPlayerPositionsAndTeams()).size());
    }
}
