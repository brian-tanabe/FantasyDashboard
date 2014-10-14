package com.briantanabe.fd.dp.providers;

import com.briantanabe.fd.dp.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.dp.scrapers.espn.EspnPlayerPageScraper;
import com.briantanabe.fd.dp.web.SecureWebRequest;
import com.briantanabe.fd.dp.web.auth.CredentialProviderI;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.briantanabe.fd.dp.web.WebPage.getEspnPlayersPageFromLeagueId;

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

    // TODO mostly copied from PlayerPositionAndTeamProvider; can be refactored into a base class
    public void scrapeForOwnershipInfo(int leagueId) throws IOException {
        String nextPageLink = getEspnPlayersPageFromLeagueId(leagueId);
        Document playerPageDocument = null;
        do {
            playerPageDocument = webRequest.getPageAsDocument(nextPageLink);
            ownershipInfo.addAll(getAllPlayerOwnershipInformationOnPage(playerPageDocument, leagueId));
        } while((nextPageLink = getLinkToNextPage(playerPageDocument)) != null);
    }

    private List<EspnNflPlayer> getAllPlayerOwnershipInformationOnPage(Document document, int leagueId){
        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        return scraper.scrapeForPlayerIdsAndOwnershipInfo(leagueId, document);
    }

    // TODO test with other league's pages
    // TODO copied from PlayerPositionAndTeamProvider; can be refactored into a base class
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
