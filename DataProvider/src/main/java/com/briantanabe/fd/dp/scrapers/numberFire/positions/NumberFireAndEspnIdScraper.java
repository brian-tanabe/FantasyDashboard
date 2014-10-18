package com.briantanabe.fd.dp.scrapers.numberFire.positions;

import com.briantanabe.fd.dm.models.PlayerIdsEntity;
import com.briantanabe.fd.dp.fantasy.player.NumberFireProjection;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 10/17/2014.
 */
public class NumberFireAndEspnIdScraper implements NumberFirePositionScraperI {
    private List<PlayerIdsEntity> idToRankingMap = new ArrayList<>();

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

    private void extractNumberFireIdsAndTeamNamesFromJson(JSONObject jsonObject){
        JSONObject projectionsJsonObject = jsonObject.getJSONObject("projections");
        JSONObject playersJsonObject = projectionsJsonObject.getJSONObject("players");
        for(String jsonElementName : playersJsonObject.getNames(playersJsonObject)){
            JSONObject jsonElement = playersJsonObject.getJSONObject(jsonElementName);
            int id = jsonElement.getInt("id");
            String playerName = jsonElement.getString("name");
            int espnPlayerId = jsonElement.getInt("espn_id");

            PlayerIdsEntity ranking = new PlayerIdsEntity();
            ranking.setName(playerName);
            ranking.setEspnId(espnPlayerId);
            ranking.setNumberFireId(id);
            idToRankingMap.add(ranking);
        }
    }

    @Override
    public void scrape(Document document) {
        Elements scriptElements = document.select("script[type=text/javascript]");
        String projectionJavascriptString = findProjectionJavascript(scriptElements);
        JSONObject jsonObject = new JSONObject(projectionJavascriptString);

        extractNumberFireIdsAndTeamNamesFromJson(jsonObject);
    }

    @Override
    public List getPlayerProjections() {
        return idToRankingMap;
    }

    @Deprecated
    @Override
    public <T extends NumberFireProjection> List<T> getPlayerRankings(Document document) {
        return null;
    }
}
