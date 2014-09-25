package com.briantanabe.fd.scraper;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Brian on 9/24/14.
 */
public class NumberFireJsonDefenseScraper extends NumberFireJsonPositionScraper {
    private LinkedHashMap<Integer, NumberFireRanking> idToRankingMap = new LinkedHashMap<Integer, NumberFireRanking>(32);

    @Override
    public ArrayList<NumberFireRanking> getPlayerRankingsFromJsonString(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);

        extractNumberFireIdsAndTeamNamesFromJson(jsonObject);
        extractRankingAndFirePointsFromJson(jsonObject);


        return new ArrayList<NumberFireRanking>(idToRankingMap.values());
    }

    private void extractNumberFireIdsAndTeamNamesFromJson(JSONObject jsonObject){
        JSONObject projectionsJsonObject = jsonObject.getJSONObject("projections");
        JSONObject playersJsonObject = projectionsJsonObject.getJSONObject("players");
        for(String jsonElementName : playersJsonObject.getNames(playersJsonObject)){
            JSONObject jsonElement = playersJsonObject.getJSONObject(jsonElementName);
            int id = jsonElement.getInt("id");
            String teamName = jsonElement.getString("name");

            NumberFireRanking ranking = new NumberFireRanking();
            ranking.setName(teamName);
            idToRankingMap.put(id, ranking);
        }
    }

    private void extractRankingAndFirePointsFromJson(JSONObject jsonObject){

    }
}
