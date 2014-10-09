package com.briantanabe.fd.du.updater;

import com.briantanabe.fd.dp.providers.EspnLeaguePlayerOwnershipProvider;
import com.briantanabe.fd.dp.providers.NumberFireCurrentWeekProjectionsProvider;
import com.briantanabe.fd.dp.providers.NumberFireRemainingSeasonProjectionsProvider;
import com.briantanabe.fd.dp.providers.PlayerIdProvider;

import java.io.IOException;

/**
 * Created by btanabe on 10/9/2014.
 */
public class DatabaseUpdater {
    private static DatabaseInterface updater = DatabaseInterface.getInstance();

    public static void createPlayerIdTable(PlayerIdProvider provider) throws IOException {
        updater.insert(provider.getAllPlayersAsArrayList());
    }

    public static void createEspnLeaguePlayerOwnershipTable(EspnLeaguePlayerOwnershipProvider provider) throws IOException {
        updater.insert(provider.getPlayerOwnershipInfo());
    }

    public static void createNumberFireCurrentWeekProjectionsTable(NumberFireCurrentWeekProjectionsProvider provider) throws IOException {
        updater.insert(provider.getPlayerProjections());
    }

    public static void createNumberFireRemainingSeasonProjectionsTable(NumberFireRemainingSeasonProjectionsProvider provider) throws IOException {
        updater.insert(provider.getPlayerProjections());
    }
}
