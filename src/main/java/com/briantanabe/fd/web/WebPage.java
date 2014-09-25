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
}
