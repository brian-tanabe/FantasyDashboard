package com.briantanabe.fd.scrapers.numberFire.positions;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.fantasy.player.NumberFireRemainingSeasonRanking;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Brian on 9/24/14.
 */
public class RemainingSeasonNumberFireJsonScraper implements NumberFirePositionScraperI {
    private LinkedHashMap<Integer, NumberFireRemainingSeasonRanking> idToRankingMap = new LinkedHashMap<Integer, NumberFireRemainingSeasonRanking>();

    @Override
    public ArrayList<NumberFireRanking> getPlayerRankings(Document document) {
        Elements scriptElements = document.select("script[type=text/javascript]");
        String projectionJavascriptString = findProjectionJavascript(scriptElements);
        JSONObject jsonObject = new JSONObject(projectionJavascriptString);

        extractNumberFireIdsAndTeamNamesFromJson(jsonObject);
        extractRankingAndFirePointsFromJson(jsonObject);


        return new ArrayList<NumberFireRanking>(idToRankingMap.values());
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

    private void extractNumberFireIdsAndTeamNamesFromJson(JSONObject jsonObject){
        JSONObject projectionsJsonObject = jsonObject.getJSONObject("projections");
        JSONObject playersJsonObject = projectionsJsonObject.getJSONObject("players");
        for(String jsonElementName : playersJsonObject.getNames(playersJsonObject)){
            JSONObject jsonElement = playersJsonObject.getJSONObject(jsonElementName);
            int id = jsonElement.getInt("id");
            String playerName = jsonElement.getString("name");
            int espnPlayerId = jsonElement.getInt("espn_id");

            NumberFireRemainingSeasonRanking ranking = new NumberFireRemainingSeasonRanking(id, espnPlayerId, playerName);
            idToRankingMap.put(id, ranking);
        }
    }

    private void extractRankingAndFirePointsFromJson(JSONObject jsonObject){
        JSONObject projectionsJsonObject = jsonObject.getJSONObject("projections");
        JSONArray projectionsJsonArray = projectionsJsonObject.getJSONArray("projections");
        for(int index = 0; index < projectionsJsonArray.length(); index++){
            JSONObject teamJsonObject = projectionsJsonArray.getJSONObject(index);
            int id = teamJsonObject.getInt("player_id");
            int rank = teamJsonObject.getInt("pos_rank");
            double firePoints = teamJsonObject.getDouble("fp");

            NumberFireRanking ranking = idToRankingMap.get(id);
            ranking.setRanking(rank);
            ranking.setFirePoints(firePoints);
        }
    }
}
