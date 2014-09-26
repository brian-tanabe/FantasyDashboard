package com.briantanabe.fd.scrapers.numberFire.positions;

import com.briantanabe.fd.fantasy.player.NumberFireCurrentWeekRanking;
import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Brian on 9/25/14.
 */
public class CurrentWeekNumberFireHtmlScraper extends NumberFirePositionScraper {

    @Override
    public ArrayList<NumberFireRanking> getPlayerRankings(Document document) {
        ArrayList<NumberFireRanking> playerRankings = new ArrayList<NumberFireRanking>(32);

        Elements teamTrElements = document.select("tbody#projection-data");
        for(Element teamElement : teamTrElements.select("tr")){
            String ranking = teamElement.select("td.sep").get(1).text();
            String teamName = teamElement.select("a[href^=/nfl/players/]").text();
            String firePoints = teamElement.select("td[class$=col-fp]").text();
            String numberFireIdString = teamElement.select("a[href^=/nfl/players/]").attr("rel");

            NumberFireCurrentWeekRanking numberFireRanking = new NumberFireCurrentWeekRanking(Integer.parseInt(numberFireIdString), teamName, Integer.parseInt(ranking), Double.parseDouble(firePoints));
            playerRankings.add(numberFireRanking);
        }

        return playerRankings;
    }
}
