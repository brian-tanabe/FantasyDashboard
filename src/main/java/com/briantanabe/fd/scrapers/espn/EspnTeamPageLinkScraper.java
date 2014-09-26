package com.briantanabe.fd.scrapers.espn;

import com.briantanabe.fd.web.SecureWebRequest;
import com.briantanabe.fd.web.auth.EspnCredentialProvider;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Brian on 9/25/14.
 */
public class EspnTeamPageLinkScraper {
    private SecureWebRequest webRequest;
    private EspnCredentialProvider credentials;
    private String fantasyPageUrl;

    // TODO THIS IS RIPE FOR IOC:
    public EspnTeamPageLinkScraper(SecureWebRequest webRequest, EspnCredentialProvider credentials, String fantasyPageUrl){
        this.webRequest = webRequest;
        this.credentials = credentials;
        this.fantasyPageUrl = fantasyPageUrl;
    }

    public String[] getAllEspnFantasyTeamHomePageLinks() throws IOException {
        initializeWebRequest(webRequest, credentials);
        Document fantasyHomePage = webRequest.getPageAsDocument(fantasyPageUrl);
        return extractTeamLinksFromDocument(fantasyHomePage);
    }

    private void initializeWebRequest(SecureWebRequest webRequest, EspnCredentialProvider credentials) throws IOException {
        webRequest.login(credentials);
    }

    private String[] extractTeamLinksFromDocument(Document document){
        Elements teamLinks = document.select("a[href^=http://games.espn.go.com/ffl/clubhouse?");

        String[] links = new String[teamLinks.size()];
        for(int index = 0; index < teamLinks.size(); index++){
            links[index] = teamLinks.get(index).attr("href");
        }

        return links;
    }
}
