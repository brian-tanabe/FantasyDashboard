package com.briantanabe.fd.providers;

import com.briantanabe.fd.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.scrapers.espn.EspnPlayerPageScraper;
import com.briantanabe.fd.web.SecureWebRequest;
import com.briantanabe.fd.web.auth.CredentialProviderI;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static com.briantanabe.fd.web.WebPage.getEspnPlayersPageFromLeagueId;

/**
 * Created by Brian on 9/28/14.
 */
public class EspnLeaguePlayerOwnershipProvider {
    private SecureWebRequest webRequest;
    private ArrayList<EspnNflPlayer> ownershipInfo = new ArrayList<EspnNflPlayer>();

    public EspnLeaguePlayerOwnershipProvider(SecureWebRequest webRequest){
        this.webRequest = webRequest;
    }

    public void login(CredentialProviderI credentials) throws IOException {
        webRequest.login(credentials);
    }

    public void scrapeForOwnershipInfo(int leagueId) throws IOException {
        String nextPageLink = getEspnPlayersPageFromLeagueId(leagueId);
        Document playerPageDocument = null;
        do {
            playerPageDocument = webRequest.getPageAsDocument(nextPageLink);
            ownershipInfo.addAll(getAllPlayerOwnershipInformationOnPage(playerPageDocument, leagueId));
        } while((nextPageLink = getLinkToNextPage(playerPageDocument)) != null);
    }

    private ArrayList<EspnNflPlayer> getAllPlayerOwnershipInformationOnPage(Document document, int leagueId){
        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        return scraper.scrape(leagueId, document);
    }

    private String getLinkToNextPage(Document document){
        String nextPageLink = null;

        Elements nextPageElement = document.select("a[href^=http://games.espn.go.com/ffl/tools/projections?]:contains(NEXT)");
        if(nextPageElement.size() > 0)
            nextPageLink = nextPageElement.attr("href");

        return nextPageLink;
    }

    public ArrayList<EspnNflPlayer> getPlayerOwnershipInfo(){
        return ownershipInfo;
    }
}
