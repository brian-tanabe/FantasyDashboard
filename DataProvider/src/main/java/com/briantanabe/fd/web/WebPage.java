package com.briantanabe.fd.web;

/**
 * Created by Brian on 9/24/14.
 */
public class WebPage {
    private static final String NUMBER_FIRE_WEEKLY_PROJECTIONS_URL = "https://www.numberfire.com/nfl/fantasy/fantasy-football-projections";
    private static final String NUMBER_FIRE_REMAINING_YEAR_PROJECTIONS_URL = "https://www.numberfire.com/nfl/fantasy/remaining-projections";

    public static final String NUMBER_FIRE_CURRENT_WEEK_QUARTERBACK_PROJECTIONS_URL = String.format("%s/%s", NUMBER_FIRE_WEEKLY_PROJECTIONS_URL, "qb");
    public static final String NUMBER_FIRE_CURRENT_WEEK_RUNNING_BACK_PROJECTIONS_URL = String.format("%s/%s", NUMBER_FIRE_WEEKLY_PROJECTIONS_URL, "rb");
    public static final String NUMBER_FIRE_CURRENT_WEEK_WIDE_RECEIVER_PROJECTIONS_URL = String.format("%s/%s", NUMBER_FIRE_WEEKLY_PROJECTIONS_URL, "wr");
    public static final String NUMBER_FIRE_CURRENT_WEEK_TIGHT_END_PROJECTIONS_URL = String.format("%s/%s", NUMBER_FIRE_WEEKLY_PROJECTIONS_URL, "te");
    public static final String NUMBER_FIRE_CURRENT_WEEK_KICKER_PROJECTIONS_URL = String.format("%s/%s", NUMBER_FIRE_WEEKLY_PROJECTIONS_URL, "k");
    public static final String NUMBER_FIRE_CURRENT_WEEK_DEFENSE_PROJECTIONS_URL = String.format("%s/%s", NUMBER_FIRE_WEEKLY_PROJECTIONS_URL, "d");

    public static final String NUMBER_FIRE_REMAINING_SEASON_QUARTERBACK_PROJECTIONS_URL = String.format("%s/%s", NUMBER_FIRE_REMAINING_YEAR_PROJECTIONS_URL, "qb");
    public static final String NUMBER_FIRE_REMAINING_SEASON_RUNNING_BACK_PROJECTIONS_URL = String.format("%s/%s", NUMBER_FIRE_REMAINING_YEAR_PROJECTIONS_URL, "rb");
    public static final String NUMBER_FIRE_REMAINING_SEASON_WIDE_RECEIVER_PROJECTIONS_URL = String.format("%s/%s", NUMBER_FIRE_REMAINING_YEAR_PROJECTIONS_URL, "wr");
    public static final String NUMBER_FIRE_REMAINING_SEASON_TIGHT_END_PROJECTIONS_URL = String.format("%s/%s", NUMBER_FIRE_REMAINING_YEAR_PROJECTIONS_URL, "te");
    public static final String NUMBER_FIRE_REMAINING_SEASON_KICKER_PROJECTIONS_URL = String.format("%s/%s", NUMBER_FIRE_REMAINING_YEAR_PROJECTIONS_URL, "k");
    public static final String NUMBER_FIRE_REMAINING_SEASON_DEFENSE_PROJECTIONS_URL = String.format("%s/%s", NUMBER_FIRE_REMAINING_YEAR_PROJECTIONS_URL, "d");

    public static final String ESPN_FANTASY_HOME_PAGE_URL = "http://games.espn.go.com/frontpage/football";

    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:32.0) Gecko/20100101 Firefox/32.0";

    public static String getEspnLoginUrl(String username, String password){
        return String.format("https://r.espn.go.com/members/login?count=0&SUBMIT=1&language=en&affiliateName=espn&regFormId=espn&username=%s&password=%s&appRedirect=https://r.espn.go.com/members/index", username, password);
    }

    public static String getEspnPlayersPageFromLeagueId(int leagueId){
        return String.format("http://games.espn.go.com/ffl/tools/projections?&leagueId=%d&seasonTotals=true&seasonId=2014&startIndex=0", leagueId);
    }

    public static String getNextEspnPlayersPageFromJavascriptAction(String javascript){
        return String.format("http://games.espn.go.com/ffl/tools/projections?%s", javascript);
    }
}
