package com.briantanabe.fd.dg.actionProcessor;

/**
 * Created by Brian on 10/3/2014.
 */
public class ActionProcessor implements Runnable {
    public ActionProcessor(){
        System.out.println("ActionProcessor started");
    }

    public void doStuff(){
        System.out.println("doStuff()");
    }

    @Override
    public void run() {

    }
}
