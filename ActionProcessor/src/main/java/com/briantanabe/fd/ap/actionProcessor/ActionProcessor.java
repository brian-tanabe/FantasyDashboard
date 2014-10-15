package com.briantanabe.fd.ap.actionProcessor;

import com.briantanabe.fd.ap.events.AllPlayerIdsEvent;
import com.briantanabe.fd.ap.events.DatabaseUpdateCompleteEvent;
import com.briantanabe.fd.ap.events.RequestAllPlayerIdsEvent;
import com.briantanabe.fd.dp.providers.PlayerIdProvider;
import com.briantanabe.fd.dp.web.WebRequest;
import com.briantanabe.fd.du.datastore.DatabaseAccessor;
import com.briantanabe.fd.du.datastore.DatabaseUpdater;
import com.briantanabe.fd.du.log.LoggingUtility;

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
        LoggingUtility.turnLoggingOff();
    }

    public void buildDatabase() throws IOException {
        // PlayerId table:
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

    private void triggerDatabaseUpdateCompleteEvent(){
        setChanged();
        notifyObservers(new DatabaseUpdateCompleteEvent());
    }

    private void processRequestAllPlayerIdsEvent() {
        AllPlayerIdsEvent allPlayerIds = new AllPlayerIdsEvent();
        allPlayerIds.setAllNflPlayerIds(databaseAccessor.getAllNflPlayersFromThePlayerIdTable());
        setChanged();
        notifyObservers(allPlayerIds);
    }

    @Override
    public void run() {
        try {
            buildDatabase();
            triggerDatabaseUpdateCompleteEvent();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
