package com.briantanabe.fd.dp.tests.unit.scrapers.espn;

import com.briantanabe.fd.dm.models.EspnOwnershipEntity;
import com.briantanabe.fd.dp.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.dp.fantasy.player.NflPlayerPositionAndTeam;
import com.briantanabe.fd.dp.nfl.position.Position;
import com.briantanabe.fd.dp.nfl.team.NflTeam;
import com.briantanabe.fd.dp.scrapers.espn.EspnPlayerPageScraper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.briantanabe.fd.dp.tests.fixtures.EspnPlayersProjectionsPageFixture.*;
import static com.briantanabe.fd.dp.tests.unit.scrapers.PlayerFinder.findPlayerByPlayerName;
import static org.junit.Assert.*;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class EspnPlayerPageScraperTests {
    private static final int TEST_ESPN_LEAGUE_ID = 84978;

    @Test
    @Deprecated
    public void testCanFindFortyPlayers(){
        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnNflPlayer> players = scraper.scrapeForPlayerIdsAndOwnershipInfo(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());

        assertEquals("Did not find 40 players on page one", 40, players.size());
    }

    @Test
    public void shouldBeAbleToFindFortyPlayersOnTheFirstPage(){
        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnOwnershipEntity> players = scraper.scrapeForPlayerIdsAndOwnershipInfoUsingEntityObject(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());

        assertEquals("Did not find 40 players on page one", 40, players.size());
    }

    @Test
    @Deprecated
    public void testCanFindTheFirstPlayerOnPageOne(){
        String playerToTest = "Adrian Peterson";

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnNflPlayer> players = scraper.scrapeForPlayerIdsAndOwnershipInfo(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());

        EspnNflPlayer player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
    }

    @Test
    public void shouldBeAbleToFindTheFirstPlayerOnThePage(){
        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnOwnershipEntity> players = scraper.scrapeForPlayerIdsAndOwnershipInfoUsingEntityObject(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());
        EspnOwnershipEntity player = players.get(0);

        assertNotNull("Unable to find the first player", player);
    }

    @Test
    @Deprecated
    public void testFirstPlayerHasProperEspnId(){
        String playerToTest = "Adrian Peterson";
        int espnId = 10452;

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnNflPlayer> players = scraper.scrapeForPlayerIdsAndOwnershipInfo(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());
        EspnNflPlayer player = findPlayerByPlayerName(playerToTest, players);

        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's ESPN ID was not parsed correctly", playerToTest), espnId, player.getEspnPlayerId());
    }

    @Test
    public void shouldBeAbleToScrapeTheFirstPlayersPlayerIdCorrectly(){
        int espnId = 10452;

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnOwnershipEntity> players = scraper.scrapeForPlayerIdsAndOwnershipInfoUsingEntityObject(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());
        EspnOwnershipEntity player = players.get(0);

        assertNotNull("Unable to find the first player", player);
        assertEquals("The first player's ESPN ID was not parsed correctly", espnId, player.getEspnId());
    }

    @Test
    @Deprecated
    public void testFirstPlayerHasProperOwnerId(){
        String playerToTest = "Adrian Peterson";
        int teamId = 7;

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnNflPlayer> players = scraper.scrapeForPlayerIdsAndOwnershipInfo(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());
        EspnNflPlayer player = findPlayerByPlayerName(playerToTest, players);

        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's teamId was not parsed correctly", playerToTest), teamId, player.getOwner());
    }

    @Test
    public void shouldBeAbleToParseTheFirstPlayersOwnerIdCorrectly(){
        int teamId = 7;

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnOwnershipEntity> players = scraper.scrapeForPlayerIdsAndOwnershipInfoUsingEntityObject(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());
        EspnOwnershipEntity player = players.get(0);

        assertNotNull("Unable to find the first player", player);
        assertEquals("The first player's teamId was not parsed correctly", teamId, player.getTeamId());
    }

    @Test
    @Deprecated
    public void testCanFindTheLastPlayerOnPageOne(){
        String playerToTest = "C.J. Spiller";

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnNflPlayer> players = scraper.scrapeForPlayerIdsAndOwnershipInfo(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());
        EspnNflPlayer player = findPlayerByPlayerName(playerToTest, players);

        assertNotNull(String.format("Unable to find %s", playerToTest), player);
    }

    @Test
    @Deprecated
    public void testLastPlayerHasProperEspnId(){
        String playerToTest = "C.J. Spiller";
        int espnId = 13203;

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnNflPlayer> players = scraper.scrapeForPlayerIdsAndOwnershipInfo(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());
        EspnNflPlayer player = findPlayerByPlayerName(playerToTest, players);

        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's ESPN ID was not parsed correctly", playerToTest), espnId, player.getEspnPlayerId());
    }

    @Test
    public void shouldBeAbleToParseTheLastPlayersEspnIdCorrectly(){
        int espnId = 13203;

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnOwnershipEntity> players = scraper.scrapeForPlayerIdsAndOwnershipInfoUsingEntityObject(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());
        EspnOwnershipEntity player = players.get(players.size() - 1);

        assertNotNull("Unable to find the last player", player);
        assertEquals("The last player's ESPN ID was not parsed correctly", espnId, player.getEspnId());
    }

    @Test
    @Deprecated
    public void testLastPlayerHasProperOwnerId(){
        String playerToTest = "C.J. Spiller";
        int teamId = 10;

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnNflPlayer> players = scraper.scrapeForPlayerIdsAndOwnershipInfo(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());
        EspnNflPlayer player = findPlayerByPlayerName(playerToTest, players);

        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's teamId was not parsed correctly", playerToTest), teamId, player.getOwner());
    }

    @Test
    public void shouldBeAbleToParseTheLastPlayersOwnerIdCorrectly(){
        int teamId = 10;

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnOwnershipEntity> players = scraper.scrapeForPlayerIdsAndOwnershipInfoUsingEntityObject(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageOneAsDocument());
        EspnOwnershipEntity player = players.get(players.size() - 1);

        assertNotNull("Unable to find the last player", player);
        assertEquals("The last player's owner ID was not parsed correctly", teamId, player.getTeamId());
    }

    @Test
    @Deprecated
    public void freeAgentsShouldHaveAnOwnerIdOfNegativeOne(){
        String playerToTest = "Ray Rice";
        int teamId = -1;

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnNflPlayer> players = scraper.scrapeForPlayerIdsAndOwnershipInfo(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageTwoAsDocument());
        EspnNflPlayer player = findPlayerByPlayerName(playerToTest, players);

        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's teamId was not parsed correctly", playerToTest), teamId, player.getOwner());
    }

    @Test
    public void shouldBeAbleToIdentifyFreeAgentsByTheirOwnerNegativeOwnerId(){
        int teamId = -1;
        
        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<EspnOwnershipEntity> players = scraper.scrapeForPlayerIdsAndOwnershipInfoUsingEntityObject(TEST_ESPN_LEAGUE_ID, getAllPlayersProjectionPageTwoAsDocument());
        EspnOwnershipEntity player = players.get(11);

        assertNotNull("Did not find at least 12 players on the scraped page", player);
        assertEquals("The 12th player was not Ray Rice (FA)", teamId, player.getTeamId());
    }

    @Test
    public void shouldBeAbleToParseQuarterbacksProperly(){
        String playerToTest = "Peyton Manning";
        int espnId = 1428;
        NflTeam team = NflTeam.BRONCOS;
        ArrayList<Position> positionList = new ArrayList<>();
        positionList.add(Position.QUARTERBACK);

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<NflPlayerPositionAndTeam> players = scraper.scrapeForPlayerPositionAndNflTeam(getAllPlayersProjectionPageOneAsDocument());

        NflPlayerPositionAndTeam player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's name was not parsed correctly", playerToTest), playerToTest, player.getName());
        assertEquals(String.format("%s's espnId was parsed incorrectly", playerToTest), espnId, player.getEspnPlayerId());
        assertEquals(String.format("%s's NFL team was parsed incorrectly", playerToTest), team, player.getNflTeam());
        assertTrue(String.format("%s's position was parsed incorrectly", playerToTest), positionList.containsAll(player.getPositionEligibility()) && positionList.size() == player.getPositionEligibility().size());
    }

    @Test
    public void shouldBeAbleToParseRunningBacksProperly(){
        String playerToTest = "Arian Foster";
        int espnId = 12497;
        NflTeam team = NflTeam.TEXANS;
        ArrayList<Position> positionList = new ArrayList<>();
        positionList.add(Position.RUNNING_BACK);

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<NflPlayerPositionAndTeam> players = scraper.scrapeForPlayerPositionAndNflTeam(getAllPlayersProjectionPageOneAsDocument());

        NflPlayerPositionAndTeam player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's name was not parsed correctly", playerToTest), playerToTest, player.getName());
        assertEquals(String.format("%s's espnId was parsed incorrectly", playerToTest), espnId, player.getEspnPlayerId());
        assertEquals(String.format("%s's NFL team was parsed incorrectly", playerToTest), team, player.getNflTeam());
        assertTrue(String.format("%s's position was parsed incorrectly", playerToTest), positionList.containsAll(player.getPositionEligibility()) && positionList.size() == player.getPositionEligibility().size());
    }

    @Test
    public void shouldBeAbleToParseWideReceiverProperly(){
        String playerToTest = "Dez Bryant";
        int espnId = 13215;
        NflTeam team = NflTeam.COWBOYS;
        ArrayList<Position> positionList = new ArrayList<>();
        positionList.add(Position.WIDE_RECEIVER);

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<NflPlayerPositionAndTeam> players = scraper.scrapeForPlayerPositionAndNflTeam(getAllPlayersProjectionPageOneAsDocument());

        NflPlayerPositionAndTeam player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's name was not parsed correctly", playerToTest), playerToTest, player.getName());
        assertEquals(String.format("%s's espnId was parsed incorrectly", playerToTest), espnId, player.getEspnPlayerId());
        assertEquals(String.format("%s's NFL team was parsed incorrectly", playerToTest), team, player.getNflTeam());
        assertTrue(String.format("%s's position was parsed incorrectly", playerToTest), positionList.containsAll(player.getPositionEligibility()) && positionList.size() == player.getPositionEligibility().size());
    }

    @Test
    public void shouldBeAbleToParseTightEndsProperly(){
        String playerToTest = "Jimmy Graham";
        int espnId = 13232;
        NflTeam team = NflTeam.SAINTS;
        ArrayList<Position> positionList = new ArrayList<>();
        positionList.add(Position.TIGHT_END);

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<NflPlayerPositionAndTeam> players = scraper.scrapeForPlayerPositionAndNflTeam(getAllPlayersProjectionPageOneAsDocument());

        NflPlayerPositionAndTeam player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's name was not parsed correctly", playerToTest), playerToTest, player.getName());
        assertEquals(String.format("%s's espnId was parsed incorrectly", playerToTest), espnId, player.getEspnPlayerId());
        assertEquals(String.format("%s's NFL team was parsed incorrectly", playerToTest), team, player.getNflTeam());
        assertTrue(String.format("%s's position was parsed incorrectly", playerToTest), positionList.containsAll(player.getPositionEligibility()) && positionList.size() == player.getPositionEligibility().size());
    }

    @Test
    public void shouldBeAbleToParseKickersProperly(){
        String playerToTest = "Nick Novak";
        int espnId = 9329;
        NflTeam team = NflTeam.CHARGERS;
        ArrayList<Position> positionList = new ArrayList<>();
        positionList.add(Position.KICKER);

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<NflPlayerPositionAndTeam> players = scraper.scrapeForPlayerPositionAndNflTeam(getAllPlayersProjectionPageFourAsDocument());

        NflPlayerPositionAndTeam player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's name was not parsed correctly", playerToTest), playerToTest, player.getName());
        assertEquals(String.format("%s's espnId was parsed incorrectly", playerToTest), espnId, player.getEspnPlayerId());
        assertEquals(String.format("%s's NFL team was parsed incorrectly", playerToTest), team, player.getNflTeam());
        assertTrue(String.format("%s's position was parsed incorrectly", playerToTest), positionList.containsAll(player.getPositionEligibility()) && positionList.size() == player.getPositionEligibility().size());
    }

    @Test
    public void shouldBeAbleToParseDefensesProperly(){
        String playerToTest = "Panthers D/ST";
        int espnId = 60029;
        NflTeam team = NflTeam.PANTHERS;
        ArrayList<Position> positionList = new ArrayList<>();
        positionList.add(Position.DEFENSE);

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<NflPlayerPositionAndTeam> players = scraper.scrapeForPlayerPositionAndNflTeam(getAllPlayersProjectionPageFourAsDocument());

        NflPlayerPositionAndTeam player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's name was not parsed correctly", playerToTest), playerToTest, player.getName());
        assertEquals(String.format("%s's espnId was parsed incorrectly", playerToTest), espnId, player.getEspnPlayerId());
        assertEquals(String.format("%s's NFL team was parsed incorrectly", playerToTest), team, player.getNflTeam());
        assertTrue(String.format("%s's position was parsed incorrectly", playerToTest), positionList.containsAll(player.getPositionEligibility()) && positionList.size() == player.getPositionEligibility().size());
    }

    @Test
    public void shouldBeAbleToParsePlayersWithMultiplePositionsProperly(){
        String playerToTest = "Dexter McCluster";
        int espnId = 13207;
        NflTeam team = NflTeam.TITANS;
        Set<Position> positionSet = new HashSet(2);
        positionSet.add(Position.WIDE_RECEIVER);
        positionSet.add(Position.RUNNING_BACK);

        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        List<NflPlayerPositionAndTeam> players = scraper.scrapeForPlayerPositionAndNflTeam(getAllPlayersProjectionPageFiveAsDocument());

        NflPlayerPositionAndTeam player = findPlayerByPlayerName(playerToTest, players);
        assertNotNull(String.format("Unable to find %s", playerToTest), player);
        assertEquals(String.format("%s's name was not parsed correctly", playerToTest), playerToTest, player.getName());
        assertEquals(String.format("%s's espnId was parsed incorrectly", playerToTest), espnId, player.getEspnPlayerId());
        assertEquals(String.format("%s's NFL team was parsed incorrectly", playerToTest), team, player.getNflTeam());
        assertTrue(String.format("%s's position was parsed incorrectly", playerToTest), positionSet.containsAll(player.getPositionEligibility()) && positionSet.size() == player.getPositionEligibility().size());
    }
}
