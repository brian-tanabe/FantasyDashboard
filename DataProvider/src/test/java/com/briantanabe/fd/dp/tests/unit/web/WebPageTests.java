package com.briantanabe.fd.dp.tests.unit.web;

import com.briantanabe.fd.dp.web.SecureWebRequest;
import com.briantanabe.fd.dp.web.WebRequest;
import com.briantanabe.fd.dp.web.auth.EspnCredentialProvider;
import org.junit.Test;

import static com.briantanabe.fd.dp.web.WebPage.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * Created by Brian on 9/24/14.
 */
public class WebPageTests {
    private WebRequest webRequest = new WebRequest();

    @Test
    public void shouldBeAbleToReachNumberFireCurrentSeasonQuarterBackProjectionsPage(){
        try {
            assertEquals("Incorrect response code for current week nF QB page", 200, webRequest.getPageResponseCode(NUMBER_FIRE_CURRENT_WEEK_QUARTERBACK_PROJECTIONS_URL));
        } catch(Exception ex){
            fail("FAILED to reach current week nF QB page");
        }
    }

    @Test
    public void shouldBeAbleToReachNumberFireCurrentSeasonRunningBackProjectionsPage(){
        try {
            assertEquals("Incorrect response code for current week nF RB page", 200, webRequest.getPageResponseCode(NUMBER_FIRE_CURRENT_WEEK_RUNNING_BACK_PROJECTIONS_URL));
        } catch(Exception ex){
            fail("FAILED to reach current week nF RB page");
        }
    }

    @Test
    public void shouldBeAbleToReachNumberFireCurrentSeasonWideReceiverProjectionsPage(){
        try {
            assertEquals("Incorrect response code for current week nF WR page", 200, webRequest.getPageResponseCode(NUMBER_FIRE_CURRENT_WEEK_WIDE_RECEIVER_PROJECTIONS_URL));
        } catch(Exception ex){
            fail("FAILED to reach current week nF WR page");
        }
    }

    @Test
    public void shouldBeAbleToReachNumberFireCurrentSeasonTightEndProjectionsPage(){
        try {
            assertEquals("Incorrect response code for current week nF TE page", 200, webRequest.getPageResponseCode(NUMBER_FIRE_CURRENT_WEEK_TIGHT_END_PROJECTIONS_URL));
        } catch(Exception ex){
            fail("FAILED to reach current week nF TE page");
        }
    }

    @Test
    public void shouldBeAbleToReachNumberFireCurrentSeasonKickerProjectionsPage(){
        try {
            assertEquals("Incorrect response code for current week nF K page", 200, webRequest.getPageResponseCode(NUMBER_FIRE_CURRENT_WEEK_KICKER_PROJECTIONS_URL));
        } catch(Exception ex){
            fail("FAILED to reach current week nF K page");
        }
    }

    @Test
    public void shouldBeAbleToReachNumberFireCurrentSeasonDefenseProjectionsPage(){
        try {
            assertEquals("Incorrect response code for current week nF DEF page", 200, webRequest.getPageResponseCode(NUMBER_FIRE_CURRENT_WEEK_DEFENSE_PROJECTIONS_URL));
        } catch(Exception ex){
            fail("FAILED to reach current week nF DEF page");
        }
    }

    @Test
    public void shouldBeAbleToReachNumberFireRemainingSeasonQuarterBackProjectionsPage(){
        try {
            assertEquals("Incorrect response code for remaining season nF QB page", 200, webRequest.getPageResponseCode(NUMBER_FIRE_REMAINING_SEASON_QUARTERBACK_PROJECTIONS_URL));
        } catch (Exception ex){
            fail("FAILED to reach remaining season nF QB page");
        }
    }

    @Test
    public void shouldBeAbleToReachNumberFireRemainingSeasonRunningBackProjectionsPage(){
        try {
            assertEquals("Incorrect response code for remaining season nF RB page", 200, webRequest.getPageResponseCode(NUMBER_FIRE_REMAINING_SEASON_RUNNING_BACK_PROJECTIONS_URL));
        } catch (Exception ex){
            fail("FAILED to reach remaining season nF RB page");
        }
    }

    @Test
    public void shouldBeAbleToReachNumberFireRemainingSeasonWideReceiverProjectionsPage(){
        try {
            assertEquals("Incorrect response code for remaining season nF WR page", 200, webRequest.getPageResponseCode(NUMBER_FIRE_REMAINING_SEASON_WIDE_RECEIVER_PROJECTIONS_URL));
        } catch (Exception ex){
            fail("FAILED to reach remaining season nF WR page");
        }
    }

    @Test
    public void shouldBeAbleToReachNumberFireRemainingSeasonTightEndProjectionsPage(){
        try {
            assertEquals("Incorrect response code for remaining season nF TE page", 200, webRequest.getPageResponseCode(NUMBER_FIRE_REMAINING_SEASON_TIGHT_END_PROJECTIONS_URL));
        } catch (Exception ex){
            fail("FAILED to reach remaining season nF TE page");
        }
    }

    @Test
    public void shouldBeAbleToReachNumberFireRemainingSeasonKickerProjectionsPage(){
        try {
            assertEquals("Incorrect response code for remaining season nF K page", 200, webRequest.getPageResponseCode(NUMBER_FIRE_REMAINING_SEASON_KICKER_PROJECTIONS_URL));
        } catch (Exception ex){
            fail("FAILED to reach remaining season nF K page");
        }
    }

    @Test
    public void shouldBeAbleToReachNumberFireRemainingSeasonDefenseProjectionsPage(){
        try {
            assertEquals("Incorrect response code for remaining season nF DEF page", 200, webRequest.getPageResponseCode(NUMBER_FIRE_REMAINING_SEASON_DEFENSE_PROJECTIONS_URL));
        } catch (Exception ex){
            fail("FAILED to reach remaining season nF DEF page");
        }
    }

    @Test
    public void shouldBeABleToReachEspnFantasyHomePage(){
        try {
            assertEquals("Incorrect response code for ESPN fantasy home page", 200, webRequest.getPageResponseCode(ESPN_FANTASY_HOME_PAGE_URL));
        } catch(Exception ex){
            fail("FAILED to reach ESPN's fantasy home page");
        }
    }

    @Test
    public void shouldBeAbleToReachEspnPlayersPageFromLakeForestLeague(){
        try {
            SecureWebRequest secureWebRequest = new SecureWebRequest();
            secureWebRequest.login(new EspnCredentialProvider());
            assertEquals("Incorrect response code for ESPN players page for leagueId=693999", 200, secureWebRequest.getPageResponseCode(getEspnPlayersPageFromLeagueId(693999)));
        } catch(Exception ex){
            fail("FAILED to reach ESPN's player's page");
        }
    }
}
