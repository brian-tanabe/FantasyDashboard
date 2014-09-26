package com.briantanabe.fd.tests.scrapers.espn;

import com.briantanabe.fd.fixtures.EspnFantasyHomePageFixture;
import com.briantanabe.fd.scrapers.espn.EspnTeamPageLinkScraper;
import com.briantanabe.fd.web.SecureWebRequest;
import com.briantanabe.fd.web.auth.EspnCredentialProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.briantanabe.fd.web.WebPage.ESPN_FANTASY_HOME_PAGE_URL;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Brian on 9/25/14.
 */
public class EspnTeamPageLinkScraperTests {
    private static SecureWebRequest webRequest;

    @BeforeClass
    public static void setup(){
        webRequest = mock(SecureWebRequest.class);
    }

    @Test
    public void testCanParseTeamLinks(){
        try {
            when(webRequest.getPageAsDocument(ESPN_FANTASY_HOME_PAGE_URL)).thenReturn(EspnFantasyHomePageFixture.getAuthenticatedEspnFantasyHomePageDocument());
            assertTrue("Did not find the right number of team links!", new EspnTeamPageLinkScraper(webRequest, new EspnCredentialProvider(), ESPN_FANTASY_HOME_PAGE_URL).getAllEspnFantasyTeamHomePageLinks().length == 3);
        } catch(Exception ex) {
            fail("FAILED to parse fantasy team links properly");
        }
    }

    @Test
    public void unauthenticatedRequestsDoNotCrash(){
        try {
            when(webRequest.getPageAsDocument(ESPN_FANTASY_HOME_PAGE_URL)).thenReturn(EspnFantasyHomePageFixture.getUnauthenticatedEspnFantasyHomePageDocument());
            assertTrue("I found links that aren't teams!", new EspnTeamPageLinkScraper(webRequest, new EspnCredentialProvider(), ESPN_FANTASY_HOME_PAGE_URL).getAllEspnFantasyTeamHomePageLinks().length == 0);
        } catch (Exception ex){
            fail("I crashed when parsing an unauthenticated ESPN fantasy home page");
        }
    }
}
