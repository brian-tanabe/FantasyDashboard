package com.briantanabe.fd.dp.providers;

import com.briantanabe.fd.dp.fantasy.player.NflPlayerPositionAndTeam;
import com.briantanabe.fd.dp.scrapers.espn.EspnPlayerPageScraper;
import com.briantanabe.fd.dp.web.WebRequest;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.briantanabe.fd.dp.web.WebPage.getEspnPlayersPage;

/**
 * Created by btanabe on 10/13/2014.
 */
public class PlayerPositionAndTeamProvider {
    private WebRequest webRequest;
    private List<NflPlayerPositionAndTeam> playerPositionsAndTeams = new ArrayList<>(2000);

    public PlayerPositionAndTeamProvider(WebRequest webRequest){
        this.webRequest = webRequest;
    }

    // TODO mostly copied from EspnLeaguePlayerOwnershipProvider; can be refactored into a base class
    public void scrapeForPlayerPositionsAndTeams() throws IOException {
        String nextPageLink = getEspnPlayersPage();
        Document playerPageDocument = null;
        do {
            playerPageDocument = webRequest.getPageAsDocument(nextPageLink);
            playerPositionsAndTeams.addAll(getAllPlayerPositionsAndNflTeamsOnPage(playerPageDocument));
        } while((nextPageLink = getLinkToNextPage(playerPageDocument)) != null);
    }

    private List<NflPlayerPositionAndTeam> getAllPlayerPositionsAndNflTeamsOnPage(Document document){
        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        return scraper.scrapeForPlayerPositionAndNflTeam(document);
    }

    // TODO test with other league's pages
    // TODO copied from EspnLeaguePlayerOwnershipProvider; can be refactored into a base class
    private String getLinkToNextPage(Document document){
        String nextPageLink = null;

        Elements nextPageElement = document.select("a[href^=http://games.espn.go.com/ffl/tools/projections?]:contains(NEXT)");
        if(nextPageElement.size() > 0)
            nextPageLink = nextPageElement.attr("href");

        return nextPageLink;
    }

    public List<NflPlayerPositionAndTeam> getAllPlayerPositionsAndTeams(){
        return playerPositionsAndTeams;
    }
}
