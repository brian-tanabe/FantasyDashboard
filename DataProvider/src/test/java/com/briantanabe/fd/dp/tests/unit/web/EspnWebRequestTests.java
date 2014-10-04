package com.briantanabe.fd.dp.tests.unit.web;

import com.briantanabe.fd.dp.web.SecureWebRequest;
import com.briantanabe.fd.dp.web.auth.EspnCredentialProvider;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import static com.briantanabe.fd.dp.web.WebPage.ESPN_FANTASY_HOME_PAGE_URL;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by BTanabe on 9/25/2014.
 */
public class EspnWebRequestTests {

    @Test
    public void shouldBeAbleToAuthenticateWithEspnAndGetLoginCookie(){
        try {
            new SecureWebRequest().login(new EspnCredentialProvider());
        } catch(Exception ex){
            fail("Failed to log into ESPN");
        }
    }

    @Test
    public void shouldBeAbleToFindFantasyTeamLinksIfLoggedInToEspn(){
        try {
            SecureWebRequest secureWebRequest = new SecureWebRequest().login(new EspnCredentialProvider());
            Document espnHomePage = secureWebRequest.getPageAsDocument(ESPN_FANTASY_HOME_PAGE_URL);
            Elements teamLinks = espnHomePage.select("a.clubhouse-link");

            assertTrue("Did not find any links", teamLinks.size() > 0);
            assertTrue("Did not find any links to team homepages", teamLinks.select("a[href^=http://games.espn.go.com/ffl/clubhouse?").size() > 0);
        } catch(Exception ex){
            fail("Failed to log into ESPN");
        }
    }
}
