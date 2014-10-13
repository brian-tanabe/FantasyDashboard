package com.briantanabe.fd.ap.tests.spy;

import com.briantanabe.fd.ap.events.RequestAllPlayerIdsEvent;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Brian on 10/12/14.
 */
public class RequestAllPlayerIdsObserver extends Observable implements Observer {

    public void requestAllPlayerIds(){
        setChanged();
        notifyObservers(new RequestAllPlayerIdsEvent());
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
