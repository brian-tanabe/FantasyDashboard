package com.briantanabe.fd.dp.scrapers.espn;

import com.briantanabe.fd.dp.fantasy.player.EspnNflPlayer;
import com.briantanabe.fd.dp.fantasy.player.NflPlayerPositionAndTeam;
import com.briantanabe.fd.dp.nfl.position.Position;
import com.briantanabe.fd.dp.nfl.position.PositionFactory;
import com.briantanabe.fd.dp.nfl.team.NflTeam;
import com.briantanabe.fd.dp.nfl.team.NflTeamFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class EspnPlayerPageScraper {

    public List<EspnNflPlayer> scrapeForPlayerIdsAndOwnershipInfo(int leagueId, Document document){
        Elements playerElements = findAllPlayerElementsInDocument(document);

        ArrayList<EspnNflPlayer> players = new ArrayList<EspnNflPlayer>(playerElements.size());
        for(Element playerElement : playerElements){
            Elements playerIdAndNameElement = extractPlayerIdAndNameElement(playerElement);
            String playerId = playerIdAndNameElement.attr("playerid");
            String name = playerIdAndNameElement.text();
            int teamId = getOwnersTeamIdFromElement(playerElement.select("td[style$=text-align: center;]").get(0));

            players.add(new EspnNflPlayer(leagueId, Integer.parseInt(playerId), name, teamId));
        }

        return players;
    }

    public List<NflPlayerPositionAndTeam> scrapeForPlayerPositionAndNflTeam(Document document){
        Elements playerElements = findAllPlayerElementsInDocument(document);
        ArrayList<NflPlayerPositionAndTeam> players = new ArrayList<NflPlayerPositionAndTeam>(playerElements.size());
        for(Element playerElement : playerElements){
            Elements playerIdAndNameElement = extractPlayerIdAndNameElement(playerElement);
            String playerId = playerIdAndNameElement.attr("playerid");
            String name = playerIdAndNameElement.text();
            String positionAndTeamString = playerElement.select("td.playertablePlayerName").text().replace(name, "").replace(",", "").replace("\u00a0"," ").replaceAll("[^a-zA-Z /]", "").trim();
            List<Position> positions = getAllEligiblePositionsFromTeamAndPositionString(positionAndTeamString);
            NflTeam team = PositionFactory.whatPosition(positionAndTeamString) == Position.DEFENSE ? NflTeamFactory.whatTeam(name) : NflTeamFactory.whatTeam(positionAndTeamString);

            players.add(new NflPlayerPositionAndTeam(name, Integer.parseInt(playerId), team, positions));
        }

        return players;
    }

    private Elements findAllPlayerElementsInDocument(Document document){
        return document.select("tr[class^=pncPlayerRow]");
    }

    private Elements extractPlayerIdAndNameElement(Element playerElement){
        return playerElement.select("td.playertablePlayerName").select("a:not(a:has(img))");
    }

    private List<Position> getAllEligiblePositionsFromTeamAndPositionString(String teamAndPositionString){
        String[] positionString = teamAndPositionString.toUpperCase().split(" ");
        ArrayList<Position> positions = new ArrayList<>();
        for(int index = 1; index < positionString.length; index++){
            if(PositionFactory.whatPosition(positionString[index]) != Position.UNKNOWN)
                positions.add(PositionFactory.whatPosition(positionString[index]));
        }

        if(positions.size() == 0)
            positions.add(PositionFactory.whatPosition(teamAndPositionString));

        return positions;
    }

    private int getOwnersTeamIdFromElement(Element ownerElement){
        Matcher matcher = Pattern.compile("(?<=teamId=)\\d+").matcher(ownerElement.select("a").attr("href"));
        String matchedGroup = matcher.find() ? matcher.group() : "-1";

        return Integer.parseInt(matchedGroup);
    }
}
