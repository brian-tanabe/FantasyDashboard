package com.briantanabe.fd.providers;

import com.briantanabe.fd.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.scrapers.espn.EspnPlayerPageScraper;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Brian on 9/28/14.
 */
public class EspnLeaguePlayerOwnershipProvider {
    private ArrayList<EspnNflPlayer> ownershipInfo = new ArrayList<EspnNflPlayer>();

    public void scrapeForOwnershipInfo(int leagueId) throws IOException {

    }

    private ArrayList<EspnNflPlayer> getAllPlayerOwnershipInformationOnPage(Document document, int leagueId){
        EspnPlayerPageScraper scraper = new EspnPlayerPageScraper();
        return scraper.scrape(leagueId, document);
    }

    public ArrayList<EspnNflPlayer> getPlayerOwnershipInfo(){
        return ownershipInfo;
    }
}
