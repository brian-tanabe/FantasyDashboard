package com.briantanabe.fd.unitTests.providers;

import com.briantanabe.fd.fantasy.player.NumberFireCurrentWeekProjection;
import com.briantanabe.fd.providers.NumberFireCurrentWeekProjectionsProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.briantanabe.fd.unitTests.scrapers.PlayerFinder.findPlayerByPlayerName;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Brian on 9/28/14.
 */
public class NumberFireCurrentWeekProjectionsProviderTests {
    private static ArrayList<NumberFireCurrentWeekProjection> playerProjections = new ArrayList<NumberFireCurrentWeekProjection>();

    private static final String quarterbackPlayerName = "Tony Romo";
    private static final String runningBackPlayerName = "Donald Brown";
    private static final String wideReceiverPlayerName = "Alshon Jeffery";
    private static final String tightEndPlayerName = "Greg Olsen";
    private static final String defensePlayerName = "Chicago D/ST";
    private static final String kickerPlayerName = "Robbie Gould";

    private static NumberFireCurrentWeekProjection quarterbackProjection;
    private static NumberFireCurrentWeekProjection runningBackProjection;
    private static NumberFireCurrentWeekProjection wideReceiverProjection;
    private static NumberFireCurrentWeekProjection tightEndProjection;
    private static NumberFireCurrentWeekProjection defenseProjection;
    private static NumberFireCurrentWeekProjection kickerProjection;

    @BeforeClass
    public static void setup(){
        try {
            NumberFireCurrentWeekProjectionsProvider provider = new NumberFireCurrentWeekProjectionsProvider();
            provider.scrapeForNumberFiresCurrentWeekProjections();
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
        assertEquals(String.format("%s's ranking was not scraped correctly", quarterbackPlayerName), 8, quarterbackProjection.getNumberFireId());
    }

    @Test
    public void shouldBeAbleToFindOneQuarterbackFirePoints(){
        assertEquals(String.format("%s's fire points were not scraped correctly", quarterbackPlayerName), 16.83, quarterbackProjection.getFirePoints());
    }

    @Test
    public void shouldBeAbleToFindOneQuarterbackRanking(){
        assertEquals(String.format("%s's ranking was not scraped correctly", quarterbackPlayerName), 11, quarterbackProjection.getRanking());
    }

    @Test
    public void shouldBeAbleToFindOneRunningBackInRemainingSeasonProjections(){
        assertEquals(String.format("Unable to find %s's nF remaining season projections", runningBackPlayerName), runningBackPlayerName, runningBackProjection.getName());
    }

    @Test
    public void shouldBeAbleToFindOneRunningBackId(){
        assertEquals(String.format("%s's ranking was not scraped correctly", runningBackPlayerName), 1081, runningBackProjection.getNumberFireId());
    }

    @Test
    public void shouldBeAbleToFindOneRunningBackFirePoints(){
        assertEquals(String.format("%s's fire points were not scraped correctly", runningBackPlayerName), 9.13, runningBackProjection.getFirePoints());
    }

    @Test
    public void shouldBeAbleToFindOneRunningBackRanking(){
        assertEquals(String.format("%s's ranking was not scraped correctly", runningBackPlayerName), 56, runningBackProjection.getRanking());
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
        assertEquals(String.format("%s's fire points were not scraped correctly", wideReceiverPlayerName), 9.49, wideReceiverProjection.getFirePoints());
    }

    @Test
    public void shouldBeAbleToFindOneWideReceiverRanking(){
        assertEquals(String.format("%s's ranking was not scraped correctly", wideReceiverPlayerName), 50, wideReceiverProjection.getRanking());
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
        assertEquals(String.format("%s's fire points were not scraped correctly", tightEndPlayerName), 7.66, tightEndProjection.getFirePoints());
    }

    @Test
    public void shouldBeAbleToFindOneTightEndRanking(){
        assertEquals(String.format("%s's ranking was not scraped correctly", tightEndPlayerName), 79, tightEndProjection.getRanking());
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
        assertEquals(String.format("%s's fire points were not scraped correctly", defensePlayerName), 7.97, defenseProjection.getFirePoints());
    }

    @Test
    public void shouldBeAbleToFindOneDefenseRanking(){
        assertEquals(String.format("%s's ranking was not scraped correctly", defensePlayerName), 16, defenseProjection.getRanking());
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
        assertEquals(String.format("%s's fire points were not scraped correctly", kickerPlayerName), 9.47, kickerProjection.getFirePoints());
    }

    @Test
    public void shouldBeAbleToFindOneKickerRanking(){
        assertEquals(String.format("%s's ranking was not scraped correctly", kickerPlayerName), 1, kickerProjection.getRanking());
    }
}
