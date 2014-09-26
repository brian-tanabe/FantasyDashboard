package com.briantanabe.fd.scrapers.espn;

import com.briantanabe.fd.fantasy.player.EspnNflPlayer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class EspnPlayerPageScraper {

    public EspnNflPlayer[] scrape(Document document){
        Elements playerElements = findAllPlayerElementsInDocument(document);

        ArrayList<EspnNflPlayer> players = new ArrayList<EspnNflPlayer>(playerElements.size());
        for(Element playerElement : playerElements){
            String playerId = playerElement.attr("playerid");
            String owner = playerElement.attr("teamid");
            String name = playerElement.text();

            players.add(new EspnNflPlayer(Integer.parseInt(playerId), name, Integer.parseInt(owner)));
        }

        return players.toArray(new EspnNflPlayer[players.size()]);
    }

    private Elements findAllPlayerElementsInDocument(Document document){
        return document.select("td.playertablePlayerName").select("a:not(a:has(img))");
    }
}
