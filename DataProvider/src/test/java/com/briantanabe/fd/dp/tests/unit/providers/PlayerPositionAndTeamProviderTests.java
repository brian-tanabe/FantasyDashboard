package com.briantanabe.fd.dp.tests.unit.providers;

import com.briantanabe.fd.dp.providers.PlayerPositionAndTeamProvider;
import com.briantanabe.fd.dp.tests.fixtures.MockWebRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by btanabe on 10/13/2014.
 */
public class PlayerPositionAndTeamProviderTests {

    @Test
    public void shouldBeAbleToFindOneThousandFourHundredNinetyFivePlayers(){
        try {
            PlayerPositionAndTeamProvider dataProvider = new PlayerPositionAndTeamProvider(MockWebRequest.getMockWebRequestForPlayerPositionAndTeamProvider());
            dataProvider.scrapeForPlayerPositionsAndTeams();
            assertEquals("Did not find the proper number of players", 1495, dataProvider.getAllPlayerPositionsAndTeams().size());
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Failed to scrapeForPlayerIdsAndOwnershipInfo for player positions and teams correctly");
        }
    }

    @Test
    public void shouldBeAbleToProperlyParseQuarterbacks(){
        fail("Unimplemented");
    }

    @Test
    public void shouldBeAbleToProperlyParseRunningBacks(){
        fail("Unimplemented");
    }

    @Test
    public void shouldBeAbleToProperlyParseWideReceivers(){
        fail("Unimplemented");
    }

    @Test
    public void shouldBeAbleToProperlyParseTightEnds(){
        fail("Unimplemented");
    }

    @Test
    public void shouldBeAbleToProperlyParseKickers(){
        fail("Unimplemented");
    }

    @Test
    public void shouldBeAbleToProperlyParseDefenses(){
        fail("Unimplemented");
    }

    @Test
    public void shouldBeAbleToProperlyParsePlayersWithMultiplePositions(){
        fail("Unimplemented");
    }

    @Test
    public void shouldBeAbleToProperlyParseFreeAgents(){
        fail("Unimplemented");
    }

    @Test
    public void shouldBeABleToProperlyParseNflTeams(){
        fail("Unimplemented");
    }
}
