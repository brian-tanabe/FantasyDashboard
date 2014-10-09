package com.briantanabe.fd.du.updater;

import com.briantanabe.fd.dp.providers.PlayerIdProvider;
import com.briantanabe.fd.dp.web.SecureWebRequest;

import java.io.IOException;

/**
 * Created by btanabe on 10/9/2014.
 */
public class DatabaseUpdater {
    private static DatabaseInterface updater = DatabaseInterface.getInstance();
    private SecureWebRequest espnWebRequest;

    public DatabaseUpdater(SecureWebRequest espnWebRequest){
        this.espnWebRequest = espnWebRequest;
    }

    public void createDatabase() throws IOException {
        createPlayerIdTable();
    }

    private void createPlayerIdTable() throws IOException {
        PlayerIdProvider playerIdProvider = new PlayerIdProvider();
        playerIdProvider.scrapeForPlayerIds(espnWebRequest);
        updater.insert(playerIdProvider.getAllPlayersAsArrayList());
    }
}
