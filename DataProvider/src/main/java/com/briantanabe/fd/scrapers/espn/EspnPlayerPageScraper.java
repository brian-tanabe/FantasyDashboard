package com.briantanabe.fd.scrapers.espn;

import com.briantanabe.fd.fantasy.player.EspnNflPlayer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class EspnPlayerPageScraper {
    private int leagueId;

    public EspnPlayerPageScraper(int leagueId){
        this.leagueId = leagueId;
    }

    public ArrayList<EspnNflPlayer> scrape(Document document){
        Elements playerElements = findAllPlayerElementsInDocument(document);

        ArrayList<EspnNflPlayer> players = new ArrayList<EspnNflPlayer>(playerElements.size());
        for(Element playerElement : playerElements){
            Elements playerIdAndNameElement = playerElement.select("td.playertablePlayerName").select("a:not(a:has(img))");
            String playerId = playerIdAndNameElement.attr("playerid");
            String name = playerIdAndNameElement.text();
            int teamId = getOwnersTeamIdFromElement(playerElement.select("td[style$=text-align: center;]").get(0));

            players.add(new EspnNflPlayer(leagueId, Integer.parseInt(playerId), name, teamId));
        }

        return players;
    }

    private Elements findAllPlayerElementsInDocument(Document document){
        return document.select("tr[class^=pncPlayerRow]");
    }

    private int getOwnersTeamIdFromElement(Element ownerElement){
        Matcher matcher = Pattern.compile("(?<=teamId=)\\d+").matcher(ownerElement.select("a").attr("href"));
        String matchedGroup = matcher.find() ? matcher.group() : "-1";

        return Integer.parseInt(matchedGroup);
    }
}
