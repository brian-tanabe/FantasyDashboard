package com.briantanabe.fd.scraper;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Brian on 9/24/14.
 */
public class NumberFireScraper {
    private Document document;
    private NumberFireJsonPositionScraper scraper;
    ArrayList<NumberFireRanking> players;

    public NumberFireScraper(Document playerProjectionsPageDocument, NumberFireJsonPositionScraper scraper){
        this.document = playerProjectionsPageDocument;
        this.scraper = scraper;
    }

    public void scrape(){
        Elements scriptElements = document.select("script[type=text/javascript]");
        String projectionJavascriptString = findProjectionJavascript(scriptElements);
        players = scraper.getPlayerRankingsFromJsonString(projectionJavascriptString);
    }

    public ArrayList<NumberFireRanking> getPlayerRankings(){
        return players;
    }

    private String findProjectionJavascript(Elements scriptElements){
        for(Element script : scriptElements){
            if(script.html().contains("NF_DATA"))
                return extractJustTheJsonFromJavascript(script.html());
        }

        return "";
    }

    private String extractJustTheJsonFromJavascript(String javascriptJson){
        return javascriptJson.substring(javascriptJson.indexOf("NF_DATA = ") + "NF_DATA = ".length(), javascriptJson.indexOf("};") + "}:".length());
    }
}
