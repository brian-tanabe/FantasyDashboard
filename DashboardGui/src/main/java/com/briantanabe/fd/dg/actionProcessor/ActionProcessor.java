package com.briantanabe.fd.dg.actionProcessor;

import com.briantanabe.fd.du.updater.DatabaseInterface;

/**
 * Created by Brian on 10/3/2014.
 */
public class ActionProcessor implements Runnable {
    private DatabaseInterface databaseInterface = DatabaseInterface.getInstance();

    public ActionProcessor(){
        System.out.println("ActionProcessor started");
    }

    public void createPlayerDatabase(){
        System.out.println("creating player database");

        databaseInterface.getAllEspnNflPlayersFromDatabase();
    }

    @Override
    public void run() {
        createPlayerDatabase();
    }
}
