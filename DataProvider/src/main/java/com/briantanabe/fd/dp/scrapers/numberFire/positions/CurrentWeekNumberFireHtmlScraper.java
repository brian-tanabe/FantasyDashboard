package com.briantanabe.fd.dp.scrapers.numberFire.positions;

import com.briantanabe.fd.dp.fantasy.player.NumberFireCurrentWeekProjection;
import com.briantanabe.fd.dp.fantasy.player.NumberFireProjection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 9/25/14.
 */
public class CurrentWeekNumberFireHtmlScraper implements NumberFirePositionScraperI {

    @Override
    public void scrape(Document document) {}

    public ArrayList<NumberFireProjection> getPlayerRankings(Document document) {
        ArrayList<NumberFireProjection> playerRankings = new ArrayList<NumberFireProjection>(32);

        Elements teamTrElements = document.select("tbody#projection-data");
        for(Element teamElement : teamTrElements.select("tr")){
            String ranking = teamElement.select("td.sep").get(1).text();
            String name = removePositionAndNflTeamFromName(teamElement.select("a[href^=/nfl/players/]").text());
            String firePoints = teamElement.select("td[class$=col-fp]").text();
            String numberFireIdString = teamElement.select("a[href^=/nfl/players/]").attr("rel");

            NumberFireCurrentWeekProjection numberFireRanking = new NumberFireCurrentWeekProjection(Integer.parseInt(numberFireIdString), name, Integer.parseInt(ranking), Double.parseDouble(firePoints));
            playerRankings.add(numberFireRanking);
        }

        return playerRankings;
    }

    private String removePositionAndNflTeamFromName(String dirtyName){
        return dirtyName.replaceAll("\\(.+", "").trim();
    }


    @Override
    public List getPlayerProjections() {
        return null;
    }
}
