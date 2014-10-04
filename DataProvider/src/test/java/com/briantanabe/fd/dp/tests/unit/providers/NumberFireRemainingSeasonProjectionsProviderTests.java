package com.briantanabe.fd.dp.tests.unit.providers;

import com.briantanabe.fd.dp.fantasy.player.NumberFireRemainingSeasonProjection;
import com.briantanabe.fd.dp.tests.fixtures.MockWebRequest;
import com.briantanabe.fd.dp.providers.NumberFireRemainingSeasonProjectionsProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.briantanabe.fd.dp.tests.unit.scrapers.PlayerFinder.findPlayerByPlayerName;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Brian on 9/28/14.
 */
public class NumberFireRemainingSeasonProjectionsProviderTests {
    private static ArrayList<NumberFireRemainingSeasonProjection> playerProjections = new ArrayList<NumberFireRemainingSeasonProjection>();

    private static final String quarterbackPlayerName = "Russell Wilson";
    private static final String runningBackPlayerName = "Lesean McCoy";
    private static final String wideReceiverPlayerName = "Alshon Jeffery";
    private static final String tightEndPlayerName = "Greg Olsen";
    private static final String defensePlayerName = "Chicago D/ST";
    private static final String kickerPlayerName = "Robbie Gould";

    private static NumberFireRemainingSeasonProjection quarterbackProjection;
    private static NumberFireRemainingSeasonProjection runningBackProjection;
    private static NumberFireRemainingSeasonProjection wideReceiverProjection;
    private static NumberFireRemainingSeasonProjection tightEndProjection;
    private static NumberFireRemainingSeasonProjection defenseProjection;
    private static NumberFireRemainingSeasonProjection kickerProjection;

    @BeforeClass
    public static void setup(){
        try {
            NumberFireRemainingSeasonProjectionsProvider provider = new NumberFireRemainingSeasonProjectionsProvider();
            provider.scrapeForNumberFiresRemainingSeasonProjections(MockWebRequest.getMockWebRequestForNumberFireRemainingSeasonProjectionsProvider());
            playerProjections = provider.getPlayerProjections();

            quarterbackProjection = findPlayerByPlayerName(quarterbackPlayerName, playerProjections);
            runningBackProjection = findPlayerByPlayerName(runningBackPlayerName, playerProjections);
            wideReceiverProjection = findPlayerByPlayerName(wideReceiverPlayerName, playerProjections);
            tightEndProjection = findPlayerByPlayerName(tightEndPlayerName, playerProjections);
            defenseProjection = findPlayerByPlayerName(defensePlayerName, playerProjections);
            kickerProjection = findPlayerByPlayerName(kickerPlayerName, playerProjections);
        } catch(Exception ex){
            ex.printStackTrace();
            fail("Failed to scrape for all numberFire's quarterbackProjection remaining season projections");
        }
    }

    @Test
    public void shouldBeAbleToFindOneQuarterbackInRemainingSeasonProjections(){
        assertEquals(String.format("Unable to find %s's nF remaining season projections", quarterbackPlayerName), quarterbackPlayerName, quarterbackProjection.getName());
    }

    @Test
    public void shouldBeAbleToFindOneQuarterbackId(){
        assertEquals(String.format("%s's ranking was not scraped correctly", quarterbackPlayerName), 4481, quarterbackProjection.getNumberFireId());
    }

    @Test
    public void shouldBeAbleToFindOneQuarterbackFirePoints(){
        assertEquals(String.format("%s's fire points were not scraped correctly", quarterbackPlayerName), 226.61, quarterbackProjection.getFirePoints());
    }

    @Test
    public void shouldBeAbleToFindOneQuarterbackRanking(){
        assertEquals(String.format("%s's ranking was not scraped correctly", quarterbackPlayerName), 7, quarterbackProjection.getRanking());
    }

    @Test
    public void shouldBeAbleToFindOneRunningBackInRemainingSeasonProjections(){
        assertEquals(String.format("Unable to find %s's nF remaining season projections", runningBackPlayerName), runningBackPlayerName, runningBackProjection.getName());
    }

    @Test
    public void shouldBeAbleToFindOneRunningBackId(){
        assertEquals(String.format("%s's ranking was not scraped correctly", runningBackPlayerName), 237, runningBackProjection.getNumberFireId());
    }

    @Test
    public void shouldBeAbleToFindOneRunningBackFirePoints(){
        assertEquals(String.format("%s's fire points were not scraped correctly", runningBackPlayerName), 223.55, runningBackProjection.getFirePoints());
    }

    @Test
    public void shouldBeAbleToFindOneRunningBackRanking(){
        assertEquals(String.format("%s's ranking was not scraped correctly", runningBackPlayerName), 1, runningBackProjection.getRanking());
    }

    @Test
    public void shouldBeAbleToFindOneWideReceiverInRemainingSeasonProjections(){
        assertEquals(String.format("Unable to find %s's nF remaining season projections", wideReceiverPlayerName), wideReceiverPlayerName, wideReceiverProjection.getName());
    }

    @Test
    public void shouldBeAbleToFindOneWideReceiverId(){
        assertEquals(String.format("%s's ranking was not scraped correctly", wideReceiverPlayerName), 4471, wideReceiverProjection.getNumberFireId());
    }

    @Test
    public void shouldBeAbleToFindOneWideReceiverFirePoints(){
        assertEquals(String.format("%s's fire points were not scraped correctly", wideReceiverPlayerName), 125.04, wideReceiverProjection.getFirePoints());
    }

    @Test
    public void shouldBeAbleToFindOneWideReceiverRanking(){
        assertEquals(String.format("%s's ranking was not scraped correctly", wideReceiverPlayerName), 11, wideReceiverProjection.getRanking());
    }

    @Test
    public void shouldBeAbleToFindOneTightEndInRemainingSeasonProjections(){
        assertEquals(String.format("Unable to find %s's nF remaining season projections", tightEndPlayerName), tightEndPlayerName, tightEndProjection.getName());
    }

    @Test
    public void shouldBeAbleToFindOneTightEndId(){
        assertEquals(String.format("%s's ranking was not scraped correctly", tightEndPlayerName), 915, tightEndProjection.getNumberFireId());
    }

    @Test
    public void shouldBeAbleToFindOneTightEndFirePoints(){
        assertEquals(String.format("%s's fire points were not scraped correctly", tightEndPlayerName), 96.23, tightEndProjection.getFirePoints());
    }

    @Test
    public void shouldBeAbleToFindOneTightEndRanking(){
        assertEquals(String.format("%s's ranking was not scraped correctly", tightEndPlayerName), 4, tightEndProjection.getRanking());
    }

    @Test
    public void shouldBeAbleToFindOneDefenseInRemainingSeasonProjections(){
        assertEquals(String.format("Unable to find %s's nF remaining season projections", defensePlayerName), defensePlayerName, defenseProjection.getName());
    }

    @Test
    public void shouldBeAbleToFindOneDefenseId(){
        assertEquals(String.format("%s's ranking was not scraped correctly", defensePlayerName), 2897, defenseProjection.getNumberFireId());
    }

    @Test
    public void shouldBeAbleToFindOneDefenseFirePoints(){
        assertEquals(String.format("%s's fire points were not scraped correctly", defensePlayerName), 100.07, defenseProjection.getFirePoints());
    }

    @Test
    public void shouldBeAbleToFindOneDefenseRanking(){
        assertEquals(String.format("%s's ranking was not scraped correctly", defensePlayerName), 20, defenseProjection.getRanking());
    }

    @Test
    public void shouldBeAbleToFindOneKickerInRemainingSeasonProjections(){
        assertEquals(String.format("Unable to find %s's nF remaining season projections", kickerPlayerName), kickerPlayerName, kickerProjection.getName());
    }

    @Test
    public void shouldBeAbleToFindOneKickerId(){
        assertEquals(String.format("%s's ranking was not scraped correctly", kickerPlayerName), 2820, kickerProjection.getNumberFireId());
    }

    @Test
    public void shouldBeAbleToFindOneKickerFirePoints(){
        assertEquals(String.format("%s's fire points were not scraped correctly", kickerPlayerName), 106.68, kickerProjection.getFirePoints());
    }

    @Test
    public void shouldBeAbleToFindOneKickerRanking(){
        assertEquals(String.format("%s's ranking was not scraped correctly", kickerPlayerName), 4, kickerProjection.getRanking());
    }
}
