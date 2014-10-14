package com.briantanabe.fd.dp.nfl.team;

/**
 * Created by Brian on 10/13/14.
 */
public class NflTeamFactory {
    public static NflTeam whatTeam(String teamName){
        String cleanTeamName = teamName.toLowerCase();

        if(cleanTeamName.contains("buffalo") || cleanTeamName.contains("bills") || cleanTeamName.contains("buf"))
            return NflTeam.BILLS;
        else if(cleanTeamName.contains("miami") || cleanTeamName.contains("dolphins") || cleanTeamName.contains("mia"))
            return NflTeam.DOLPHINS;
        else if(cleanTeamName.contains("new england") || cleanTeamName.contains("patriots") || cleanTeamName.contains("ne"))
            return NflTeam.PATRIOTS;
        else if(cleanTeamName.contains("new york jets") || cleanTeamName.contains("jets") || cleanTeamName.contains("nyj"))
            return NflTeam.JETS;
        else if(cleanTeamName.contains("baltimore") || cleanTeamName.contains("ravens") || cleanTeamName.contains("bal"))
            return NflTeam.RAVENS;
        else if(cleanTeamName.contains("cincinnati") || cleanTeamName.contains("bengals") || cleanTeamName.contains("cin"))
            return NflTeam.BENGALS;
        else if(cleanTeamName.contains("cleveland") || cleanTeamName.contains("browns") || cleanTeamName.contains("cle"))
            return NflTeam.BROWNS;
        else if(cleanTeamName.contains("pittsburgh") || cleanTeamName.contains("steelers") || cleanTeamName.contains("pit"))
            return NflTeam.STEELERS;
        else if(cleanTeamName.contains("houston") || cleanTeamName.contains("texans") || cleanTeamName.contains("hou"))
            return NflTeam.TEXANS;
        else if(cleanTeamName.contains("indianapolis") || cleanTeamName.contains("colts") || cleanTeamName.contains("ind"))
            return NflTeam.COLTS;
        else if(cleanTeamName.contains("jacksonville") || cleanTeamName.contains("jaguars") || cleanTeamName.contains("jax") || cleanTeamName.contains("jag"))
            return NflTeam.JAGUARS;
        else if(cleanTeamName.contains("tennessee") || cleanTeamName.contains("titans") || cleanTeamName.contains("ten"))
            return NflTeam.TITANS;
        else if(cleanTeamName.contains("denver") || cleanTeamName.contains("broncos") || cleanTeamName.contains("den"))
            return NflTeam.BRONCOS;
        else if(cleanTeamName.contains("kansas city") || cleanTeamName.contains("chiefs") || cleanTeamName.contains("kc"))
            return NflTeam.CHIEFS;
        else if(cleanTeamName.contains("oakland") || cleanTeamName.contains("raiders") || cleanTeamName.contains("oak"))
            return NflTeam.RAIDERS;
        else if(cleanTeamName.contains("san diego") || cleanTeamName.contains("chargers") || cleanTeamName.contains("sd"))
            return NflTeam.CHARGERS;
        else if(cleanTeamName.contains("dallas") || cleanTeamName.contains("cowboys") || cleanTeamName.contains("dal"))
            return NflTeam.COWBOYS;
        else if(cleanTeamName.contains("new york giants") || cleanTeamName.contains("giants") || cleanTeamName.contains("nyg"))
            return NflTeam.GIANTS;
        else if(cleanTeamName.contains("philadelphia") || cleanTeamName.contains("eagles") || cleanTeamName.contains("phi"))
            return NflTeam.EAGLES;
        else if(cleanTeamName.contains("washington") || cleanTeamName.contains("redskins") || cleanTeamName.contains("was"))
            return NflTeam.REDSKINS;
        else if(cleanTeamName.contains("chicago") || cleanTeamName.contains("bears") || cleanTeamName.contains("chi"))
            return NflTeam.BEARS;
        else if(cleanTeamName.contains("detroit") || cleanTeamName.contains("lions") || cleanTeamName.contains("det"))
            return NflTeam.LIONS;
        else if(cleanTeamName.contains("green bay") || cleanTeamName.contains("packers") || cleanTeamName.contains("gb"))
            return NflTeam.PACKERS;
        else if(cleanTeamName.contains("minnesota") || cleanTeamName.contains("vikings") || cleanTeamName.contains("min"))
            return NflTeam.VIKINGS;
        else if(cleanTeamName.contains("atlanta") || cleanTeamName.contains("falcons") || cleanTeamName.contains("atl"))
            return NflTeam.FALCONS;
        else if(cleanTeamName.contains("carolina") || cleanTeamName.contains("panthers") || cleanTeamName.contains("car"))
            return NflTeam.PANTHERS;
        else if(cleanTeamName.contains("new orleans") || cleanTeamName.contains("saints") || cleanTeamName.contains("no"))
            return NflTeam.SAINTS;
        else if(cleanTeamName.contains("tampa bay") || cleanTeamName.contains("buccaneers") || cleanTeamName.contains("tb"))
            return NflTeam.BUCCANEERS;
        else if(cleanTeamName.contains("arizona") || cleanTeamName.contains("cardinals") || cleanTeamName.contains("ari"))
            return NflTeam.CARDINALS;
        else if(cleanTeamName.contains("st. louis") || cleanTeamName.contains("st louis") || cleanTeamName.contains("rams") || cleanTeamName.contains("stl"))
            return NflTeam.RAMS;
        else if(cleanTeamName.contains("san francisco") || cleanTeamName.contains("49ers") || cleanTeamName.contains("sf"))
            return NflTeam.FORTYNINERS;
        else if(cleanTeamName.contains("seattle") || cleanTeamName.contains("seahawks") || cleanTeamName.contains("sea"))
            return NflTeam.SEAHAWKS;
        else
            return NflTeam.UNKNOWN;
    }
}
