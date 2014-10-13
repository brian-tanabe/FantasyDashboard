package com.briantanabe.fd.ap.actionProcessor;

import com.briantanabe.fd.ap.events.RequestAllPlayerIdsEvent;
import com.briantanabe.fd.dp.providers.PlayerIdProvider;
import com.briantanabe.fd.dp.web.WebRequest;
import com.briantanabe.fd.du.updater.DatabaseAccessor;
import com.briantanabe.fd.du.updater.DatabaseUpdater;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Brian on 10/12/14.
 */
public class ActionProcessor extends Observable implements Runnable, Observer {
    private WebRequest espnWebRequest;
    private DatabaseUpdater databaseUpdater = new DatabaseUpdater();
    private DatabaseAccessor databaseAccessor = new DatabaseAccessor();

    public ActionProcessor(WebRequest espnWebRequest){
        this.espnWebRequest = espnWebRequest;
    }

    public void buildDatabase() throws IOException {
        PlayerIdProvider playerIdProvider = new PlayerIdProvider();
        playerIdProvider.scrapeForPlayerIds(espnWebRequest);
        databaseUpdater.insert(playerIdProvider.getAllPlayersAsArrayList());
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof RequestAllPlayerIdsEvent){
            processRequestAllPlayerIdsEvent();
        }
    }

    private void processRequestAllPlayerIdsEvent() {
        System.out.println("Received a RequestAllPlayerIdsEvent");
    }

    @Override
    public void run() {
        try {
            buildDatabase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
