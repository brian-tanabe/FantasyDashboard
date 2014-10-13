package com.briantanabe.fd.ap.tests.spy;

import com.briantanabe.fd.ap.events.AllPlayerIdsEvent;
import com.briantanabe.fd.ap.events.RequestAllPlayerIdsEvent;
import com.briantanabe.fd.dp.fantasy.player.NflPlayer;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Brian on 10/12/14.
 */
public class RequestAllPlayerIdsObserver extends Observable implements Observer {
    private List<NflPlayer> playerIds;

    public void requestAllPlayerIds(){
        setChanged();
        notifyObservers(new RequestAllPlayerIdsEvent());
    }

    public List<NflPlayer> getAllPlayerIds() {
        return playerIds;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof AllPlayerIdsEvent){
            playerIds = ((AllPlayerIdsEvent) arg).getAllNflPlayerIds();
        }
    }
}
